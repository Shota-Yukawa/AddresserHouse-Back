
package com.ah.apartowner.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ah.apartowner.datasource.entity.ApartmentsEntity;

@Repository
public interface ApartmentsRepository extends JpaRepository<ApartmentsEntity, Integer> {

	public List<ApartmentsEntity> findByApartowner_ApartownerIdAndApartName(Integer apartownerId, String apartName);
	
	public List<ApartmentsEntity> findByApartowner_ApartownerIdAndApartNameAndApartmentIdNot(Integer apartownerId, String apartName, Integer ApartmentId);
}
