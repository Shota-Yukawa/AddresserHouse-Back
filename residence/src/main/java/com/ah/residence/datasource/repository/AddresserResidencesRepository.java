package com.ah.residence.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ah.residence.datasource.entity.AddresserResidencesEntity;

@Repository
public interface AddresserResidencesRepository extends JpaRepository<AddresserResidencesEntity, Integer> {
}
