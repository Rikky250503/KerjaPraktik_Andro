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

	@SerializedName("jam")
	private String jam;

	@SerializedName("nama_pemesan")
	private String namaPemesan;
	@SerializedName("alamat_pemesan")
	private String alamatPemesan;
	@SerializedName("kuantitas")
	private Integer Kuantitas;
	@SerializedName("harga_barang_keluar")
	private Double hargaBarangKeluar;

	@SerializedName("nama_barang")
	private String namaBarang;
	@SerializedName("id_barang_keluar")
	private String id_barang_keluar;

	public String getJam() {return jam;}

	public String getNomorInvoiceKeluar() {
		return nomorInvoiceKeluar;
	}

	public String getId_customer() {
		return id_customer;
	}

	public String getNamaStatus() {
		return namaStatus;
	}

	public String getTanggalKeluar() {
		return tanggalKeluar;
	}

	public String getNamaPemesan() {
		return namaPemesan;
	}

	public String getAlamatPemesan() {
		return alamatPemesan;
	}

	public Integer getKuantitas() {
		return Kuantitas;
	}

	public Double getHargaBarangKeluar() {
		return hargaBarangKeluar;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public String getId_barang_keluar() {
		return id_barang_keluar;
	}
}