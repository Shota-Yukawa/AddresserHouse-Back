package addresser.addresserService.entity.table;

import addresser.addresserService.entity.parts.RegistUpdateAddressInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "addresser")
public class AddresserEntity extends RegistUpdateAddressInfo {

    @Id
    @Column(name = "addresser_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "addresser_addresser_id_seq")
    @SequenceGenerator(name = "addresser_addresser_id_seq",
            sequenceName = "addresser_addresser_id_seq",
            allocationSize = 1)
    private Integer addresserId;

    @Column(name = "addresser_name", nullable = false)
    private String addresserName;

    @Column(name = "addresser_name_kana")
    private String addresserNameKana;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_name")
    private String phoneNumber;

    @Column(name = "logo")
    private String logo;

    @Column(name = "max_user", nullable = false)
    private int maxUser;

    @Column(name = "post_code")
    private String postCode;


    //    紐付けのリレーションマッピング
    @OneToMany(mappedBy = "addresserEntity")
    private List<AddresserGroupEntity> addresserGroups;

    @OneToMany(mappedBy = "addresserEntity")
    private List<AddresserUserEntity> addresserUsers;

    @OneToMany(mappedBy = "addresserEntity")
    private List<AddresserNewsEntity> addresserNews;




}
