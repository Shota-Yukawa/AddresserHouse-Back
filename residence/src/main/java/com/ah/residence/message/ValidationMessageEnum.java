package com.ah.residence.message;

public enum ValidationMessageEnum {
	
	RequestRequiredIdError("リクエストに主キーが指定されていません。対象データをしぼれません。"),
	RequestNotRequiredIdError("リクエストに主キーが指定されています。"),
	RequestPartBodyError("部分更新のリクエスト内容に誤りがあります。"),
	RequestUnacceptedValueError("許容されない値が指定されています。"),
	RequestBodyError("リクエスト内容に誤りがあります。"),
	ApartResidenceUniqueError("すでに存在しているアパート住人です"),
	ApartResidenceNotIdEorror("指定されたアパート住人IDのデータが存在しません。"),
	AddresserResidenceUniqueError("すでに存在しているアドレッサー住人です"),
	AddresserResidenceNotIdEorror("指定されたアドレッサー住人IDのデータが存在しません。")
	;
	
	private String message;
	
	private ValidationMessageEnum(String message) {
		this.message = message;
	}
	
	public String getM() {
		return this.message;
	}
	
	public String getMWithParam(String column) {
		return "[" + column + "]" + this.message;
	}

}
