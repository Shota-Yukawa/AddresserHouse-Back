package com.ah.apartowner.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ah.apartowner.datasource.entity.ApartownersEntity;

@Repository
public interface ApartownerRepository extends JpaRepository<ApartownersEntity, Integer> {

}
