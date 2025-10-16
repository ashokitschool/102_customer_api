package in.ashokit.service;

import in.ashokit.dto.ShippingAddrDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Override
    public ShippingAddrDto saveAddress(ShippingAddrDto addrDto, Integer customerId) {
        return null;
    }

    @Override
    public ShippingAddrDto getAddress(Integer addId) {
        return null;
    }

    @Override
    public ShippingAddrDto updateAddress(ShippingAddrDto addrDto) {
        return null;
    }

    @Override
    public List<ShippingAddrDto> getCustomerAddresses(Integer customerId) {
        return List.of();
    }

    @Override
    public boolean deleteAddress(Integer addId) {
        return false;
    }
}
