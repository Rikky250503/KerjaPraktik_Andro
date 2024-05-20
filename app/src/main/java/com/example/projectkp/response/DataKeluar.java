package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

public class DataKeluar {

	@SerializedName("nomor_invoice_keluar")
	private String nomorInvoiceKeluar;

	@SerializedName("total")
	private int total;

	@SerializedName("id_status")
	private int idStatus;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_customer")
	private String idCustomer;

	@SerializedName("tanggal_keluar")
	private String tanggalKeluar;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_barang_keluar")
	private String idBarangKeluar;

	@SerializedName("created_by")
	private Object createdBy;

	public String getNomorInvoiceKeluar(){
		return nomorInvoiceKeluar;
	}

	public int getTotal(){
		return total;
	}

	public int getIdStatus(){
		return idStatus;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getIdCustomer(){
		return idCustomer;
	}

	public String getTanggalKeluar(){
		return tanggalKeluar;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getIdBarangKeluar(){
		return idBarangKeluar;
	}

	public Object getCreatedBy(){
		return createdBy;
	}
}