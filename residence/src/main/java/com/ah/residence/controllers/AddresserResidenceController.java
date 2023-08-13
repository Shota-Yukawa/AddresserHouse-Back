package com.ah.residence.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ah.residence.exception.ResidenceException;
import com.ah.residence.models.req.AddresserResidenceReq;
import com.ah.residence.models.req.CommonReq;
import com.ah.residence.models.res.AddresserResidenceIdRes;
import com.ah.residence.services.AddresserResidenceService;
import com.ah.residence.util.JsonConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("residence/addresser")
public class AddresserResidenceController {

	private final AddresserResidenceService addresserResidenceService;

	@PostMapping("add")
	public AddresserResidenceIdRes addAddresserResidence(@RequestBody CommonReq reqBody) {
		return addresserResidenceService
				.create(JsonConverter.deserializeJson(reqBody.getData(), AddresserResidenceReq.class));

	}

	@PutMapping("update")
	public AddresserResidenceIdRes updateAddresserResidence(@RequestBody CommonReq reqBody) throws ResidenceException {
		Integer targetId = reqBody.getId();
		return addresserResidenceService.update(targetId,
				JsonConverter.deserializeJson(reqBody.getData(), AddresserResidenceReq.class));
	}
}