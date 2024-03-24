package com.ah.residence.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ah.residence.datasource.entity.AddresserResidencesEntity;

@Repository
public interface AddresserResidencesRepository extends JpaRepository<AddresserResidencesEntity, Integer> {
	
	public List<AddresserResidencesEntity> findByConsumerIdAndAddresserId(Integer consumerId, Integer addresserId);
	
	public List<AddresserResidencesEntity> findByConsumerIdAndAddresserIdAndAddresserResidenceIdNot(Integer consumerId, Integer addresserId, Integer addresserResidenceId);
}
