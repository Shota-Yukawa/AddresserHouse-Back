package addresser.addresserService.entity.parts;

import addresser.addresserService.entity.table.base.AddressEntity;
import addresser.addresserService.entity.table.base.CityEntity;
import addresser.addresserService.entity.table.base.PrefecturesEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * 基本情報として、住所情報・登録更新日時を共通化している。
 * 住所情報；prefecture_code, city_codeはあくまで　address_idの補足情報にすぎない。すべてnullは許容しない。
 */
@MappedSuperclass
@Setter
@Getter
public abstract class RegistUpdateAddressInfo {

//住所情報
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private AddressEntity addressEntity;

    @ManyToOne
    @JoinColumn(name =  "prefecture_code", referencedColumnName = "prefecture_code", nullable = false)
    private PrefecturesEntity prefecturesEntity;

    @ManyToOne
    @JoinColumn(name = "city_code", referencedColumnName = "city_code", nullable = false)
    private CityEntity cityEntity;

    @Column(name = "after_street")
    private String afterStreet;



//    ここからはRegistUpdateAtEntity.javaと同様
    @Column(name = "regist_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;


    @PostPersist
    protected void afterRegist() {
        Date date = new Date();
        setRegistAt(date);
        setUpdateAt(date);
    }
    @PostUpdate
    protected void afterUpdate(){
        Date date = new Date();
        setUpdateAt(date);
    }
}
