package addresser.addresserService.repository.table;

import addresser.addresserService.entity.table.AddresserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresserRepository extends JpaRepository<AddresserEntity, Integer> {
}
