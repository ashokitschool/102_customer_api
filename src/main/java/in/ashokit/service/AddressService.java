package in.ashokit.service;

import in.ashokit.dto.ShippingAddrDto;

import java.util.List;

public interface AddressService {

    public ShippingAddrDto saveAddress(ShippingAddrDto addrDto, Integer customerId);

    public ShippingAddrDto getAddress(Integer addId);

    public ShippingAddrDto updateAddress(ShippingAddrDto addrDto);

    public List<ShippingAddrDto> getCustomerAddresses(Integer customerId);

    public boolean deleteAddress(Integer addId);
}
