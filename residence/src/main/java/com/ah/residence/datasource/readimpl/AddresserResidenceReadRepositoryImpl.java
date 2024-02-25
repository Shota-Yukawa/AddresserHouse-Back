package com.ah.residence.datasource.readimpl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ah.backendreadlib.abstracts.ReadAbstractRepository;
import com.ah.residence.datasource.entity.AddresserResidencesEntity;
import com.ah.residence.datasource.repository.AddresserResidencesRepository;
import com.ah.residence.exception.ResidenceException;
import com.ah.residence.message.ValidationMessageEnum;
import com.ah.residence.models.req.AddresserResidenceReq;

@Service
public class AddresserResidenceReadRepositoryImpl extends ReadAbstractRepository<AddresserResidencesEntity, Integer, AddresserResidencesRepository, AddresserResidenceReq> {

	public AddresserResidenceReadRepositoryImpl(AddresserResidencesRepository jparepository) {
		this.jpaRepository = jparepository;
	}
	
	@Override
	public AddresserResidencesEntity existCheckAndGetById(Integer id) {
		Optional<AddresserResidencesEntity> selectOpt = this.findOptById(id);
		if(selectOpt.isEmpty()) {
			throw new ResidenceException(ValidationMessageEnum.AddresserResidenceNotIdEorror.getM());
		}
		 return selectOpt.get();
	}

	@Override
	public boolean isExistsByUniqueCol(AddresserResidenceReq reqData) {
		List<AddresserResidencesEntity> results = jpaRepository.findByConsumerIdAndAddresserId(reqData.getConsumerId(), reqData.getAddresserId());
		return CollectionUtils.isNotEmpty(results);
	}

	@Override
	public boolean isExistsByUniqueColNotEqId(AddresserResidenceReq reqData, Integer id) {
		List<AddresserResidencesEntity> results = jpaRepository.
				findByConsumerIdAndAddresserIdAndAddresserResidenceIdNot(reqData.getConsumerId(), reqData.getAddresserId(), id);
		return CollectionUtils.isNotEmpty(results);
	}

	@Override
	public boolean isExistsByUniqueColNotEqIdForEntity(AddresserResidencesEntity entity) {
		List<AddresserResidencesEntity> results = jpaRepository.
				findByConsumerIdAndAddresserIdAndAddresserResidenceIdNot(entity.getConsumerId(), entity.getAddresserId(), entity.getAddresserResidenceId());
		return CollectionUtils.isNotEmpty(results);
	}

}
