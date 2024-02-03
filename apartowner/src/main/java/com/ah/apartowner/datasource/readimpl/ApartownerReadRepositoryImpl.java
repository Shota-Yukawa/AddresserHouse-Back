package com.ah.apartowner.datasource.readimpl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.apartowner.datasource.repository.ApartownerRepository;
import com.ah.apartowner.exception.AapartownerException;
import com.ah.apartowner.messages.ValidationMessageEnum;
import com.ah.apartowner.models.request.ApartownerReq;
import com.ah.backendreadlib.abstracts.ReadAbstractRepository;

@Service
public class ApartownerReadRepositoryImpl extends ReadAbstractRepository<ApartownersEntity, Integer, ApartownerRepository, ApartownerReq> {

	public ApartownerReadRepositoryImpl(ApartownerRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
	
	@Override
	public boolean isExsitsByUniqueCol(ApartownerReq data) {
		List<ApartownersEntity> results = super.jpaRepository.findByApartownerNameAndEmail(data.getApartownerName(), data.getEmail());
		
		return CollectionUtils.isNotEmpty(results);
	}

	@Override
	public boolean isExsitsByUniqueColNotEqId(ApartownerReq data, Integer id) {
		List<ApartownersEntity> results = super.jpaRepository.findByApartownerNameAndEmailAndApartownerIdNot(data.getApartownerName(), data.getEmail(), id);
		
		return CollectionUtils.isNotEmpty(results);
	}

	@Override
	public ApartownersEntity existCheckAndGetById(Integer id) {
		Optional<ApartownersEntity> selectOpt = this.findOptById(id);
		if (selectOpt.isEmpty())//idがない場合
		throw new AapartownerException(ValidationMessageEnum.ApartownerNotIdError.getM());

		return selectOpt.get();
	}


}
