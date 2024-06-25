package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

public class DataUsername {
	@SerializedName("jabatan_user")
	private String jabatanUser;
	@SerializedName("password_user")
	private String passwordUser;
	@SerializedName("updated_at")
	private String updatedAt;
	@SerializedName("created_at")
	private String createdAt;
	@SerializedName("id_user")
	private String idUser;
	@SerializedName("nama_user")
	private String namaUser;
	@SerializedName("username_user")
	private String usernameUser;
	@SerializedName("status")
	private String status;

	public String getJabatanUser(){
		return jabatanUser;
	}

	public String getPasswordUser(){
		return passwordUser;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getIdUser(){
		return idUser;
	}

	public String getNamaUser(){
		return namaUser;
	}

	public String getUsernameUser(){
		return usernameUser;
	}

	public String getStatus(){
		return status;
	}
}
