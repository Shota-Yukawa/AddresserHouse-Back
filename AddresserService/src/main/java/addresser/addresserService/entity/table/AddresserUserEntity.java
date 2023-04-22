package addresser.addresserService.entity.table;

import addresser.addresserService.entity.parts.RegistUpdateAt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "addresser_user")
public class AddresserUserEntity extends RegistUpdateAt {

    @Id
    @Column(name = "addresser_user_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "addresser_user_addresser_user_id")
    @SequenceGenerator(
            name = "addresser_user_addresser_user_id",
            sequenceName = "addresser_user_addresser_user_id",
            allocationSize = 1)
    private Integer addresserUserId;

    @ManyToOne
    @JoinColumn(name = "addresser_id", nullable = false)
    private AddresserEntity addresserEntity;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_kind", nullable = false)
    private char userKind;

    @Column(name = "user_name")
    private String userName;
}


