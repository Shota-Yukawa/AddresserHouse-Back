package com.ah.residence.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ah.residence.datasource.entity.ApartResidencesEntity;

@Repository
public interface ApartResidencesRepository extends JpaRepository<ApartResidencesEntity, Integer> {
	
	public List<ApartResidencesEntity> findByConsumerIdAndApartmentId(Integer consumerId, Integer apartmentsId);
	
	public List<ApartResidencesEntity> findByConsumerIdAndApartmentIdAndApartResidenceIdNot(Integer consumerId, Integer apartmentsId, Integer apartResidenceId);
}
