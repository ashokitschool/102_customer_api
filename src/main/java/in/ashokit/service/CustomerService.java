package in.ashokit.service;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.ResetPwdDto;

public interface CustomerService {

    public CustomerDto saveCustomer(CustomerDto customerDto);

    public CustomerDto login(String email, String pwd);

    public boolean resetPwd(ResetPwdDto resetPwdDto);

    public CustomerDto getCustomerByEmail(String email);

    public CustomerDto updateCustomer(CustomerDto customerDto);
}
