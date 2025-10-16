package in.ashokit.mapper;

import in.ashokit.dto.CustomerDto;
import in.ashokit.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    @Autowired
   private static ModelMapper mapper;

    public static CustomerDto convertToDto(CustomerEntity customerEntity){
        return mapper.map(customerEntity, CustomerDto.class);
    }

    public static CustomerEntity convertToEntity(CustomerDto customerDto){
        return mapper.map(customerDto, CustomerEntity.class);
    }
}
