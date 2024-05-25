package com.example.projectkp.response;

import java.util.List;

public class TampilCustomerResponse{
	private List<DataCustomer> data;
	private String message;
	private boolean status;

	public List<DataCustomer> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}