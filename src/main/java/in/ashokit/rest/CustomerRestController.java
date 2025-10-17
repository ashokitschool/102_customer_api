package in.ashokit.rest;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.response.ApiResponse;
import in.ashokit.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<ApiResponse<CustomerDto>> saveCustomer(@RequestBody CustomerDto customerDto) {
        ApiResponse<CustomerDto> response = new ApiResponse<>();
        CustomerDto savedCustomer = customerService.saveCustomer(customerDto);
        if (savedCustomer != null) {
            response.setStatus(201);
            response.setMsg("Customer Saved");
            response.setData(savedCustomer);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setStatus(500);
            response.setMsg("Failed to save customer");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<CustomerDto>> login(@RequestBody CustomerDto customerDto) {
        ApiResponse<CustomerDto> response = new ApiResponse<>();
        CustomerDto customer = customerService.login(customerDto.getEmail(), customerDto.getPassword());
        if (customer != null) {
            response.setStatus(200);
            response.setMsg("Login Success");
            response.setData(customer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(400);
            response.setMsg("Invalid Credentials");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/reset-pwd")
    public ResponseEntity<ApiResponse<String>> resetPwd(@RequestBody ResetPwdDto resetPwdDto) {
        ApiResponse<String> response = new ApiResponse<>();
        boolean isResetCompleted = customerService.resetPwd(resetPwdDto);
        if (isResetCompleted) {
            response.setStatus(200);
            response.setMsg("Pwd Updated");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMsg("Reset Pwd Failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{email}")
    public ResponseEntity<ApiResponse<CustomerDto>> getCustomer(@PathVariable String email) {
        ApiResponse<CustomerDto> response = new ApiResponse<>();
        CustomerDto customer = customerService.getCustomerByEmail(email);
        if (customer != null) {
            response.setStatus(200);
            response.setMsg("Customer Fetched");
            response.setData(customer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMsg("Failed To Fetch Customer");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customer")
    public ResponseEntity<ApiResponse<CustomerDto>> updateCustomer(@RequestBody CustomerDto customerDto) {
        ApiResponse<CustomerDto> response = new ApiResponse<>();
        CustomerDto updatedCustomer = customerService.updateCustomer(customerDto);
        if (updatedCustomer != null) {
            response.setStatus(200);
            response.setMsg("Customer Updated");
            response.setData(updatedCustomer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMsg("Failed to update customer");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}