package com.ah.apartowner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AapartownerException extends RuntimeException {

	public AapartownerException(String msg) {
		super(msg);
	}
}
