package addresser.addresserService.entity.table;

import addresser.addresserService.entity.parts.RegistUpdateAt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "addresser_news")
public class AddresserNewsEntity extends RegistUpdateAt {

    @Id
    @Column(name = "addresser_news_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "addresser_news_addresser_news_id")
    @SequenceGenerator(
            name = "addresser_news_addresser_news_id",
            sequenceName = "addresser_news_addresser_news_id",
            allocationSize = 1)
    private Integer addresserNewsId;

    @ManyToOne
    @JoinColumn(name = "addresser_id", nullable = false)
    private AddresserEntity addresserEntity;

    @OneToOne
    @JoinColumn(name = "addresser_news_content_id", nullable = false)
    private AddresserNewsContentEntity addresserNewsContentEntity;

    @Column(name = "is_published", nullable = false)
    private boolean isPublished = false;

    @Column(name = "tag")
    private char ta = 0;

    @Column(name = "published_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishedAt;


}
