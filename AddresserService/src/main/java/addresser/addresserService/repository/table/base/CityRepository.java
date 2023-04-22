package addresser.addresserService.repository.table.base;

import addresser.addresserService.entity.table.base.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
}
