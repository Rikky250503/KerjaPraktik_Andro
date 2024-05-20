package com.example.projectkp.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class KeluarResponse{

	@SerializedName("data")
	private List<DataKeluar> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<DataKeluar> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}