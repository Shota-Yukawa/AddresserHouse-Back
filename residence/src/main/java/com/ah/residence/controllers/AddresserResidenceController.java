package com.ah.residence.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ah.residence.exception.ResidenceException;
import com.ah.residence.models.req.CommonReq;
import com.ah.residence.models.res.AddresserResidenceRes;
import com.ah.residence.services.AddresserResidenceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("residence/addresser")
public class AddresserResidenceController {

	private final AddresserResidenceService addresserResidenceService;

	/**
	 * 登録用リクエストマッピング
	 * 
	 * @param reqBody CommonReq
	 * @return
	 */
	@PostMapping("regist")
	public AddresserResidenceRes addAddresserResidence(@RequestBody CommonReq reqBody) {
		return addresserResidenceService
				.create(reqBody);

	}

	/**
	 * 更新用リクエストマッピング
	 * 
	 * @param reqBody CommonReq
	 * @return 登録後のデータ
	 * @throws ResidenceException
	 */
	@PutMapping("update")
	public AddresserResidenceRes updateAddresserResidence(@RequestBody CommonReq reqBody){
		Integer targetId = reqBody.getId();
		return addresserResidenceService.update(reqBody);
	}

	/**
	 * 削除用リクエストマッピング
	 * 
	 * @param reqBody CommonReq
	 * @throws ResidenceException
	 */
	@DeleteMapping("delete")
	public void deleteAddresserResidence(@RequestBody CommonReq reqBody){
		Integer targetId = reqBody.getId();
		addresserResidenceService.delete(targetId);
	}
}