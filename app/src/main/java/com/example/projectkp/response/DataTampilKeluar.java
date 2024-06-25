package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

public class DataTampilKeluar {

	@SerializedName("id_barang_keluar")
	private String id_barang_keluar;
	@SerializedName("tanggal_keluar")
	private String tanggalKeluar;
	@SerializedName("nomor_invoice_keluar")
	private String nomorInvoiceKeluar;
	@SerializedName("total")
	private Double total;
	@SerializedName("id_customer")
	 private String id_customer;
	@SerializedName("nama_status")
	private String namaStatus;
	@SerializedName("id_status")
	private String id_status;

	@SerializedName("created_by")
	private String createdBy;
	@SerializedName("jam")
	private String jam;
	@SerializedName("created_at")
	private String createdAt;
	@SerializedName("update_at")
	private String updateAt;
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

	public String getId_barang_keluar() {
		return id_barang_keluar;
	}

	public String getTanggalKeluar() {
		return tanggalKeluar;
	}

	public String getNomorInvoiceKeluar() {
		return nomorInvoiceKeluar;
	}

	public Double getTotal() {
		return total;
	}

	public String getId_customer() {
		return id_customer;
	}

	public String getNamaStatus() {
		return namaStatus;
	}

	public String getIdStatus() {
		return id_status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getJam() {
		return jam;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdateAt() {
		return updateAt;
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
}