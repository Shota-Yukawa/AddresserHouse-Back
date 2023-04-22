package addresser.addresserService.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "addresser_group")
public class AddresserGroupEntity {

    @Id
    @Column(name = "addresser_group_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "addresser_group_addresser_group_id_seq")
    @SequenceGenerator(name = "addresser_group_addresser_group_id_seq",
            sequenceName = "addresser_group_addresser_group_id_seq", allocationSize = 1)
    private Integer addresserGroupId;


    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private AddresserEntity addresserEntity;

    @Column(name = "is_fixed")
    private boolean isFixed;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "regist_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registAt;


    //リレーションマッピング
    @OneToMany(mappedBy = "addresserGroupEntity")
    private List<GroupRangeEntity> groupRangeEntityList;



}
