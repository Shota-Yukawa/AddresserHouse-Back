package com.ah.apartowner.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ah.apartowner.datasource.entity.ApartownersEntity;

@Repository
public interface ApartownerRepository extends JpaRepository<ApartownersEntity, Integer> {

	public List<ApartownersEntity> findByApartownerNameAndEmail(String apartownerName, String email);

}
