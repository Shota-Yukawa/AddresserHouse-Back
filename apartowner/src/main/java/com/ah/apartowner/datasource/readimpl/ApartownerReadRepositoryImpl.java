package com.ah.apartowner.datasource.readimpl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.apartowner.datasource.repository.ApartownerRepository;
import com.ah.apartowner.models.request.ApartownerReq;
import com.ah.backendreadlib.abstracts.ReadAbstractRepository;
import com.ah.commonlib.JsonConverter;

@Service
public class ApartownerReadRepositoryImpl extends ReadAbstractRepository<ApartownersEntity, Integer, ApartownerRepository> {

	public ApartownerReadRepositoryImpl(ApartownerRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
	
	@Override
	public boolean isExsitsByUniqueCol(Object reqData) {
		ApartownerReq data = JsonConverter.deserializeJson(reqData, ApartownerReq.class);
		List<ApartownersEntity> results = super.jpaRepository.findByApartownerNameAndEmail(data.getApartownerName(), data.getEmail());
		
		return CollectionUtils.isNotEmpty(results);
	}

	@Override
	public boolean isExsitsByUniqueColNotEqId(Object reqData, Integer id) {
		ApartownerReq data = JsonConverter.deserializeJson(reqData, ApartownerReq.class);
		List<ApartownersEntity> results = super.jpaRepository.findByApartownerNameAndEmailAndApartownerIdNot(data.getApartownerName(), data.getEmail(), id);
		
		return CollectionUtils.isNotEmpty(results);
	}

	


}
