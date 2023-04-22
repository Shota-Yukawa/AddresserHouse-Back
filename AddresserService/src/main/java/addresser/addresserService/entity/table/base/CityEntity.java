package addresser.addresserService.entity.table.base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CityEntity {

    @Id
    @Column(name = "city_code")
    private Integer cityCode;

    @Column(name = "name")
    private String Name;
}
