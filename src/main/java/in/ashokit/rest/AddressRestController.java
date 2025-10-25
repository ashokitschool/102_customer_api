package in.ashokit.rest;

import in.ashokit.dto.ShippingAddrDto;
import in.ashokit.response.ApiResponse;
import in.ashokit.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AddressRestController {

    private AddressService addressService;

    @PostMapping("/address/{customerId}")
    public ResponseEntity<ApiResponse<ShippingAddrDto>> saveAddress(@RequestBody ShippingAddrDto addrDto, @PathVariable Integer customerId) {

        ApiResponse<ShippingAddrDto> response = new ApiResponse<>();
        ShippingAddrDto shippingAddrDto = addressService.saveAddress(addrDto, customerId);

        if (shippingAddrDto != null) {
            response.setStatus(201);
            response.setMsg("Address Saved");
            response.setData(shippingAddrDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setStatus(500);
            response.setMsg("Address Not Saved");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/address/{addrId}")
    public ResponseEntity<ApiResponse<ShippingAddrDto>> getAddress(@PathVariable Integer addrId) {

        ApiResponse<ShippingAddrDto> response = new ApiResponse<>();
        ShippingAddrDto shippingAddrDto = addressService.getAddress(addrId);

        if (shippingAddrDto != null) {
            response.setStatus(200);
            response.setMsg("Address Fetched");
            response.setData(shippingAddrDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMsg("Address Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/address/{customerId}")
    public ResponseEntity<ApiResponse<ShippingAddrDto>> updateAddress(@RequestBody ShippingAddrDto addrDto, @PathVariable Integer customerId) {

        ApiResponse<ShippingAddrDto> response = new ApiResponse<>();
        ShippingAddrDto shippingAddrDto = addressService.saveAddress(addrDto, customerId);

        if (shippingAddrDto != null) {
            response.setStatus(200);
            response.setMsg("Address Updated");
            response.setData(shippingAddrDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMsg("Address Not Updated");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer-address/{customerId}")
    public ResponseEntity<ApiResponse<List<ShippingAddrDto>>> getCustomerAddresses(@PathVariable Integer customerId) {

        ApiResponse<List<ShippingAddrDto>> response = new ApiResponse<>();
        List<ShippingAddrDto> customerAddresses = addressService.getCustomerAddresses(customerId);

        if (!customerAddresses.isEmpty()) {
            response.setStatus(200);
            response.setMsg("Customer Address Fetched");
            response.setData(customerAddresses);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMsg("Addresses Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/address/{addrId}")
    public ResponseEntity<ApiResponse<ShippingAddrDto>> deleteAddress(@PathVariable Integer addrId) {

        ApiResponse<ShippingAddrDto> response = new ApiResponse<>();
        boolean status = addressService.deleteAddress(addrId);

        if (status) {
            response.setStatus(200);
            response.setMsg("Address Deleted");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMsg("Address Not Deleted");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
