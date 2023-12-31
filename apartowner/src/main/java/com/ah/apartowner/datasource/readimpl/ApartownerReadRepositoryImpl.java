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
		String apartownerName = data.getApartownerName();
		String email = data.getEmail();
		List<ApartownersEntity> results = super.jpaRepository.findByApartownerNameAndEmail(apartownerName, email);
		
		return CollectionUtils.isNotEmpty(results);
	}

	


}
