package com.ah.apartowner.datasource.readimpl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ah.apartowner.datasource.entity.ApartmentsEntity;
import com.ah.apartowner.datasource.repository.ApartmentsRepository;
import com.ah.apartowner.exception.AapartownerException;
import com.ah.apartowner.messages.ValidationMessageEnum;
import com.ah.apartowner.models.request.ApartmentReq;
import com.ah.backendreadlib.abstracts.ReadAbstractRepository;

@Service
public class ApartmentReadRepositoryImpl extends ReadAbstractRepository<ApartmentsEntity, Integer, ApartmentsRepository, ApartmentReq> {

	public ApartmentReadRepositoryImpl(ApartmentsRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public boolean isExsitsByUniqueCol(ApartmentReq data) {
		List<ApartmentsEntity> results = super.jpaRepository.findByApartowner_ApartownerIdAndApartName(data.getApartownerId(), data.getApartName());
		return CollectionUtils.isNotEmpty(results);
	}

	@Override
	public boolean isExsitsByUniqueColNotEqId(ApartmentReq data, Integer id) {
		List<ApartmentsEntity> results = super.jpaRepository.findByApartowner_ApartownerIdAndApartNameAndApartmentIdNot(data.getApartownerId(), data.getApartName(), id);
		return CollectionUtils.isNotEmpty(results);
	}

	@Override
	public ApartmentsEntity existCheckAndGetById(Integer id) {
		Optional<ApartmentsEntity> selectOpt = this.findOptById(id);
		if(selectOpt.isEmpty()) {
			throw new AapartownerException(ValidationMessageEnum.ApartmentNotIdError.getM());
		}
		return selectOpt.get();
	}
	
	
}
