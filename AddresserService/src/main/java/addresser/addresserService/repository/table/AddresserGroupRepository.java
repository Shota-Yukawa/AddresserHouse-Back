package addresser.addresserService.repository.table;

import addresser.addresserService.entity.table.AddresserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresserGroupRepository extends JpaRepository<AddresserGroupEntity, Integer> {
}
