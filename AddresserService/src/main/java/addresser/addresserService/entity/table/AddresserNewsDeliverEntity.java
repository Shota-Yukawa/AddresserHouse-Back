package addresser.addresserService.entity.table;

import addresser.addresserService.entity.parts.AddressInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "addresser_news_deliver")
public class AddresserNewsDeliverEntity extends AddressInfo {

    @Id
    @Column(name = "addresser_news_deliver_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "addresser_news_deliver_addresser_news_deliver_id")
    @SequenceGenerator(
            name = "addresser_news_deliver_addresser_news_deliver_id",
            sequenceName = "addresser_news_deliver_addresser_news_deliver_id",
            allocationSize = 1)
    private Integer addresserNewsDeliverId;

    @ManyToOne
    @JoinColumn(name = "addresser_news_id", nullable = false)
    private AddresserNewsEntity addresserNewsEntity;

    @Column(name = "is_group", nullable = false)
    private boolean isGroup;

    @ManyToOne
    @JoinColumn(name = "addresser_group_id")
    private AddresserGroupEntity addresserGroupEntity;

}
