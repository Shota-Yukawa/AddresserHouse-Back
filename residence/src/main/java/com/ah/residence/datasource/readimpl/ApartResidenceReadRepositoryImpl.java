package com.ah.residence.datasource.readimpl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ah.backendreadlib.abstracts.ReadAbstractRepository;
import com.ah.residence.datasource.entity.ApartResidencesEntity;
import com.ah.residence.datasource.repository.ApartResidencesRepository;
import com.ah.residence.exception.ResidenceException;
import com.ah.residence.message.ValidationMessageEnum;
import com.ah.residence.models.req.ApartResidenceReq;

@Service
public class ApartResidenceReadRepositoryImpl extends ReadAbstractRepository<ApartResidencesEntity, Integer, ApartResidencesRepository, ApartResidenceReq> {

	public ApartResidenceReadRepositoryImpl(ApartResidencesRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}
	@Override
	public ApartResidencesEntity existCheckAndGetById(Integer id) {
		Optional<ApartResidencesEntity> selectOpt = this.findOptById(id);
		if(selectOpt.isEmpty()) {
			throw new ResidenceException(ValidationMessageEnum.ApartResidenceNotIdEorror.getM());
		}
		return selectOpt.get();
	}

	@Override
	public boolean isExistsByUniqueCol(ApartResidenceReq reqData) {
		List<ApartResidencesEntity> results = jpaRepository.findByConsumerIdAndApartmentId(reqData.getConsumerId(), reqData.getApartmentId());
		return CollectionUtils.isNotEmpty(results);
	}

	@Override
	public boolean isExistsByUniqueColNotEqId(ApartResidenceReq reqData, Integer id) {
		List<ApartResidencesEntity> results = jpaRepository
				.findByConsumerIdAndApartmentIdAndApartResidenceIdNot(reqData.getConsumerId(), reqData.getApartmentId(), id);
		return CollectionUtils.isNotEmpty(results);
	}

	@Override
	public boolean isExistsByUniqueColNotEqIdForEntity(ApartResidencesEntity entity) {
		List<ApartResidencesEntity> results = jpaRepository
				.findByConsumerIdAndApartmentIdAndApartResidenceIdNot(entity.getConsumerId(), entity.getApartmentId(), entity.getApartResidenceId());
		return CollectionUtils.isNotEmpty(results);
	}

}
