package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("data")
	private String data;

	@SerializedName("jabatan")
	private String jabatan;

	@SerializedName("message")
	private String message;

	@SerializedName("token")
	private String token;

	public String getData(){
		return data;
	}

	public String getJabatan(){
		return jabatan;
	}

	public String getMessage(){
		return message;
	}

	public String getToken(){
		return token;
	}
}