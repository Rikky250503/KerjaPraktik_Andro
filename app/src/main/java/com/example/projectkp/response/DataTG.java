package com.example.projectkp.response;

public class DataTG {
	private String nomorInvoiceKeluar;
	private int total;
	private String idStatus;
	private String updatedAt;
	private String idCustomer;
	private String tanggalKeluar;
	private String createdAt;
	private String idBarangKeluar;
	private Object createdBy;

	public String getNomorInvoiceKeluar(){
		return nomorInvoiceKeluar;
	}

	public int getTotal(){
		return total;
	}

	public String getIdStatus(){
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
