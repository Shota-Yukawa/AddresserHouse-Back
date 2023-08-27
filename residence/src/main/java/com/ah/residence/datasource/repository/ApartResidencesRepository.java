package com.ah.residence.datasource.repository;

import com.ah.residence.datasource.entity.ApartResidencesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartResidencesRepository extends JpaRepository<ApartResidencesEntity, Integer> {
}
