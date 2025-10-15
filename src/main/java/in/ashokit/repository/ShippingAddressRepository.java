package in.ashokit.repository;

import in.ashokit.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddressEntity, Long> {
}
