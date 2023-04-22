package addresser.addresserService.entity.parts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Setter
@Getter
public abstract class RegistUpdateAt {

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

