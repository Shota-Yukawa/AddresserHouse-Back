package com.ah.apartowner.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ah.apartowner.exception.AapartownerException;
import com.ah.apartowner.models.request.ApartownerReq;
import com.ah.apartowner.models.request.CommonReq;
import com.ah.apartowner.models.response.ApartownerRes;
import com.ah.apartowner.services.AapartownerService;
import com.ah.apartowner.util.JsonConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("apartowner")
public class ApartownerController {

	private final AapartownerService apartownerService;

	@PostMapping("regist")
	public ApartownerRes registApartowner(@RequestBody CommonReq reqBody) {

		Integer id = reqBody.getId();
		if (id != null) {
			new AapartownerException("登録リクエストにidが指定されています。");
		}

		return apartownerService.regist(JsonConverter.deserializeJson(reqBody.getData(), ApartownerReq.class));
	}

	@GetMapping("test")
	public String test() {
		return "success";
	}
}
