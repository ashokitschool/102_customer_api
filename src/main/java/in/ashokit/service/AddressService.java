package in.ashokit.service;

import in.ashokit.dto.ShippingAddrDto;

import java.util.List;

public interface AddressService {

    public ShippingAddrDto saveAddress(ShippingAddrDto addrDto, Integer customerId);

    public ShippingAddrDto getAddress(Integer addrId);

    public List<ShippingAddrDto> getCustomerAddresses(Integer customerId);

    public boolean deleteAddress(Integer addrId);
}
