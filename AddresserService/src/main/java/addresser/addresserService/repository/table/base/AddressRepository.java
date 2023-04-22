package addresser.addresserService.repository.table.base;

import addresser.addresserService.entity.table.base.AddressEntity;
import org.springframework.data .jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
