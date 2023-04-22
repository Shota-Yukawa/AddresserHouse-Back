package addresser.addresserService.entity.table.base;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PrefecturesEntity {

    @Id
    @Column(name = "prefecture_code")
    private Integer prefectureCode;

    @Column(name = "name")
    private String name;
}
