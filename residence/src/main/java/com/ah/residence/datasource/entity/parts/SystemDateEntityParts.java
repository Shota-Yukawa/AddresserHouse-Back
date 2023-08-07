package com.ah.residence.datasource.entity.parts;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class SystemDateEntityParts {

    @Column(name = "regist_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registAt;

    @Column(name = "update_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateAt;


    @PostPersist
    protected void afterRegist() {
        LocalDateTime date = LocalDateTime.now();
        setRegistAt(date);
        setUpdateAt(date);
    }
    @PostUpdate
    protected void afterUpdate(){
        LocalDateTime date = LocalDateTime.now();
        setUpdateAt(date);
    }
}
