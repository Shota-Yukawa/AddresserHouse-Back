package addresser.addresserService.repository.table;

import addresser.addresserService.entity.table.GroupRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRangeRepository extends JpaRepository<GroupRangeEntity, Integer> {
}
