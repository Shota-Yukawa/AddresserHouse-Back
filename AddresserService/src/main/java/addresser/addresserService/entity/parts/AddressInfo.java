package addresser.addresserService.entity.parts;

import addresser.addresserService.entity.table.base.AddressEntity;
import addresser.addresserService.entity.table.base.CityEntity;
import addresser.addresserService.entity.table.base.PrefecturesEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * この住所情報のprefecture_codeやcity_codeは補足情報ではなく、主的情報である。
 * （RegistUpdateAddressInfo.javaと異なる）
 * つまり、address_idは空で、city_codeのみの場合もある。nullか空を許容する
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AddressInfo {


    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private AddressEntity addressId;


    @ManyToOne
    @JoinColumn(name = "prefecture_code", referencedColumnName = "prefecture_code")
    private PrefecturesEntity prefectureCode;

    @ManyToOne
    @JoinColumn(name = "city_code", referencedColumnName = "city_code")
    private CityEntity cityCode;

    @Column(name = "after_street")
    private String afterStreet;


}
