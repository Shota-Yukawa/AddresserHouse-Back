package com.ah.residence.datasource.repository;

import com.ah.residence.datasource.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresserResidencesRepository extends JpaRepository<AddressEntity, Integer> {
}
