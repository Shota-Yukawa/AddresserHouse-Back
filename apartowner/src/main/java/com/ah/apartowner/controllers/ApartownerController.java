package com.ah.apartowner.controllers;

import java.util.Objects;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		if (Objects.nonNull(reqBody.getId())) {
			new AapartownerException("登録リクエストに主キーが指定されています。");
		}
		return apartownerService.regist(JsonConverter.deserializeJson(reqBody.getData(), ApartownerReq.class));
	}
	
	@PutMapping("update")
	public ApartownerRes updateApartowner(@RequestBody CommonReq reqBody) {
		if(Objects.isNull(reqBody.getId())) {
			new AapartownerException("更新リクエストに主キーが指定されていません。対象データをしぼれません。");
		}
		ApartownerReq reqData = JsonConverter.deserializeJson(reqBody.getData(), ApartownerReq.class);
		reqData.setApartownerId(reqBody.getId());
		return apartownerService.update(reqData);
	}

	@PostMapping("test")
	public ApartownerReq test(@RequestBody CommonReq reqBody) {
		
		ApartownerReq req = JsonConverter.deserializeJson(reqBody.getData(), ApartownerReq.class);

//		return apartownerService.test(req, reqBody.getId());
		return req;
	}
}
