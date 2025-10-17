package in.ashokit.service;

import in.ashokit.dto.ShippingAddrDto;
import in.ashokit.entity.CustomerEntity;
import in.ashokit.entity.ShippingAddressEntity;
import in.ashokit.mapper.ShippingAddrMapper;
import in.ashokit.repository.CustomerRepository;
import in.ashokit.repository.ShippingAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ShippingAddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ShippingAddrDto saveAddress(ShippingAddrDto addrDto, Integer customerId) {

        CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow();

        ShippingAddressEntity addressEntity = ShippingAddrMapper.toEntity(addrDto);
        addressEntity.setCustomer(customerEntity);
        addressEntity.setDeleteSw("N");

        ShippingAddressEntity savedAddr = addressRepository.save(addressEntity);

        return ShippingAddrMapper.convertToDto(savedAddr);
    }

    @Override
    public ShippingAddrDto getAddress(Integer addrId) {

        Optional<ShippingAddressEntity> addressEntity = addressRepository.findById(addrId);

        if (addressEntity.isPresent()) {
            return ShippingAddrMapper.convertToDto(addressEntity.get());
        }

        return null;
    }

    @Override
    public List<ShippingAddrDto> getCustomerAddresses(Integer customerId) {

        List<ShippingAddressEntity> addressEntityList = addressRepository.findByCustomerCustomerIdAndDeleteSw(customerId, "N");

        return addressEntityList.stream()
                .map(ShippingAddrMapper::convertToDto)
                .toList();
    }

    @Override
    public boolean deleteAddress(Integer addrId) {

       /* if(addressRepository.existsById(addrId)){
            addressRepository.deleteById(addrId);
            return true;
        }*/

        Optional<ShippingAddressEntity> byId = addressRepository.findById(addrId);
        if (byId.isPresent()) {
            ShippingAddressEntity addressEntity = byId.get();
            addressEntity.setDeleteSw("Y");
            addressRepository.save(addressEntity);
            return true;
        }
        return false;
    }
}
