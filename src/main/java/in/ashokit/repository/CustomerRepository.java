package in.ashokit.repository;

import in.ashokit.entity.CustomerEntity;
import in.ashokit.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
