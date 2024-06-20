package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;
public class DataTampilMasuk {

    @SerializedName("nomor_invoice_masuk")
    private String nomor_invoice_masuk;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("nama_barang")
    private String nama_barang;
    @SerializedName("kuantitas")
    private String kuantitas;
    @SerializedName("harga_satuan")
    private String harga_satuan;

    @SerializedName("id_barang_masuk")
     private String id_barang_masuk;

    @SerializedName("nama_supplier")
    private String nama_supplier;

    @SerializedName("tanggal_masuk")
    private String tanggal_masuk;

    public String getNomor_invoice_masuk() {
        return nomor_invoice_masuk;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public String getKuantitas() {
        return kuantitas;
    }

    public String getHarga_satuan() {
        return harga_satuan;
    }

    public String getId_barang_masuk() {
        return id_barang_masuk;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }
}
