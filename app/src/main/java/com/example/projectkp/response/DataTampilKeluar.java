package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

public class DataTampilKeluar {

	@SerializedName("nomor_invoice_keluar")
	private String nomorInvoiceKeluar;
	@SerializedName("id_customer")
	 private String id_customer;
	@SerializedName("nama_status")
	private String namaStatus;

	@SerializedName("tanggal_keluar")
	private String tanggalKeluar;

	@SerializedName("nama_pemesan")
	private String namaPemesan;
	@SerializedName("nama_barang")
	private String namaBarang;
	@SerializedName("id_barang_keluar")
	private String id_barang_keluar;

	public String getNomorInvoiceKeluar(){
		return nomorInvoiceKeluar;
	}

	public String getNamaStatus(){
		return namaStatus;
	}

	public String getTanggalKeluar(){
		return tanggalKeluar;
	}

	public String getId_Customer() {
		return id_customer;
	}
	public String getNamaPemesan(){
		return namaPemesan;
	}

	public String getIdBarangKeluar(){
		return id_barang_keluar;
	}

	public String getNamaBarang() {
		return namaBarang;
	}
}