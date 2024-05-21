package com.example.projectkp.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TampilKeluarResponse{

	@SerializedName("data")
	private List<DataTampilKeluar> data;

	@SerializedName("message")
	private String message;

	public List<DataTampilKeluar> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}