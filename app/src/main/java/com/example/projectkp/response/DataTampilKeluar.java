package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

public class DataTampilKeluar {

	@SerializedName("nomor_invoice_keluar")
	private String nomorInvoiceKeluar;

	@SerializedName("nama_status")
	private String namaStatus;

	@SerializedName("tanggal_keluar")
	private String tanggalKeluar;

	@SerializedName("nama_pemesan")
	private String namaPemesan;

	@SerializedName("id_barang_keluar")
	private String idBarangKeluar;

	public String getNomorInvoiceKeluar(){
		return nomorInvoiceKeluar;
	}

	public String getNamaStatus(){
		return namaStatus;
	}

	public String getTanggalKeluar(){
		return tanggalKeluar;
	}

	public String getNamaPemesan(){
		return namaPemesan;
	}

	public String getIdBarangKeluar(){
		return idBarangKeluar;
	}
}