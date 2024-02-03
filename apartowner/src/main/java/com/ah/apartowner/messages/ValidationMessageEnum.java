package com.ah.apartowner.messages;

public enum ValidationMessageEnum {

	RequestRequiredIdError("リクエストに主キーが指定されていません。対象データをしぼれません。"),
	RequestNotRequiredIdError("リクエストに主キーが指定されています。"),
	RequestPartBodyError("部分更新のリクエスト内容に誤りがあります。"),
	RequestUnacceptedValueError("許容されない値が指定されています。"),
	RequestBodyError("リクエスト内容に誤りがあります。"),
	ApartownerUniqueError("すでに存在しているアパートオーナーです。"),
	ApartownerNotIdError("指定されたアパートオーナーIDのデータが存在しません。"),
	ApartmentUniqueError("すでに存在しているアパートメントです。"),
	ApartmentNotIdError("指定されたアパートメントIDのデータが存在しません。")
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
