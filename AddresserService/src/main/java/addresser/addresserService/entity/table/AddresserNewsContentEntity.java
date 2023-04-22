package addresser.addresserService.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "addresser_news_content")
public class AddresserNewsContentEntity {

    @Id
    @Column(name = "addresser_news_content_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "addresser_news_content_addresser_news_content_id")
    @SequenceGenerator(
            name = "addresser_news_content_addresser_news_content_id",
            sequenceName = "addresser_news_content_addresser_news_content_id",
            allocationSize = 1)
    private Integer addresserNewsContentId;


    @Column(name = "news_file")
    private String newsFile;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    //紐付きのリレーションマッピング

    @OneToOne(mappedBy = "addresserNewsContentEntity")
    private AddresserNewsEntity addresserNews;

}
