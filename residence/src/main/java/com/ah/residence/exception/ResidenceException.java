package com.ah.residence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResidenceException extends Exception {

	public ResidenceException(String msg) {
		super(msg);
	}
}
