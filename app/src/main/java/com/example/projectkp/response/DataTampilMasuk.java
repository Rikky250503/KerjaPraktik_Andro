package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;
public class DataTampilMasuk {

    @SerializedName("nomor_invoice_masuk")
    private String nomor_invoice_masuk;

    @SerializedName("id_barang_masuk")
    String id_barang_masuk;

    @SerializedName("nama_supplier")
    private String nama_supplier;

    @SerializedName("tanggal_masuk")
    private String tanggal_masuk;

    public String getNomor_invoice_masuk() {
        return nomor_invoice_masuk;
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
