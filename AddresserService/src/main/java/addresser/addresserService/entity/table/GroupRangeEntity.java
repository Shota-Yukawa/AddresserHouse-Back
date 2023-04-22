package addresser.addresserService.entity.table;

import addresser.addresserService.entity.parts.AddressInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "group_range")
public class GroupRangeEntity extends AddressInfo {

    @Id
    @Column(name = "group_range_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "group_range_group_range_id_seq")
    @SequenceGenerator(name = "group_range_group_range_id_seq",
            sequenceName = "group_range_group_range_id_seq", allocationSize = 1)
    private Integer groupRangeId;

    @ManyToOne
    @JoinColumn(name = "addresser_group_id", referencedColumnName = "addresser_group_id")
    private AddresserGroupEntity addresserGroupEntity;


}
