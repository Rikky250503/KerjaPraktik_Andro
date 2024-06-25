package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse{
	private Data data;
	private String message;

	public Data getData(){
		return data;
	}
	public String getMessage(){
		return message;
	}


}
