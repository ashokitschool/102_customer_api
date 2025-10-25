package in.ashokit.service;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.entity.CustomerEntity;
import in.ashokit.mapper.CustomerMapper;
import in.ashokit.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private EmailService emailService;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomerMapper.convertToEntity(customerDto);

        String tempPwd = generateRandomPwd(6);
        customerEntity.setPassword(tempPwd);
        customerEntity.setPwdUpdated("NO");

        CustomerEntity savedCustomer = customerRepo.save(customerEntity);

        String subject = "Your Account Created";
        String body = "Your Temporary Pwd To Login : " + tempPwd;

        boolean status = emailService.sendEmail(subject, body, customerDto.getEmail());

        if (status) {
            return CustomerMapper.convertToDto(savedCustomer);
        }

        return null;
    }

    @Override
    public CustomerDto login(String email, String pwd) {
        CustomerEntity customerEntity = customerRepo.findByEmailAndPassword(email, pwd);

        if (customerEntity != null) {
            return CustomerMapper.convertToDto(customerEntity);
        }

        return null;
    }

    @Override
    public boolean resetPwd(ResetPwdDto resetPwdDto) {
        CustomerEntity customerEntity = customerRepo.findByEmail(resetPwdDto.getEmail());
        if (customerEntity != null) {
            customerEntity.setPassword(resetPwdDto.getNewPwd());
            customerEntity.setPwdUpdated("YES");
            CustomerEntity savedCustomer = customerRepo.save(customerEntity);
            return true;
        }
        return false;
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        CustomerEntity customerEntity = customerRepo.findByEmail(email);
        if (customerEntity != null) {
            return CustomerMapper.convertToDto(customerEntity);
        }
        return null;
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {

        CustomerEntity customerEntity = CustomerMapper.convertToEntity(customerDto);

        CustomerEntity savedCustomer = customerRepo.save(customerEntity);

        return CustomerMapper.convertToDto(savedCustomer);
    }

    private String generateRandomPwd(int pwdLength) {
        Random random = new Random();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        StringBuilder buffer = new StringBuilder(pwdLength);
        for (int i = 0; i < pwdLength; i++) {
            int randomIndex = random.nextInt(chars.length());
            char ch = chars.charAt(randomIndex);
            buffer.append(ch);
        }
        return buffer.toString();
    }
}
