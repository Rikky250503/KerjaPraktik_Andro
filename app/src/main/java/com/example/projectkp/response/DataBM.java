package com.example.projectkp.response;

public class DataBM {
    private String nomor_invoice_masuk;
    private String updated_at;
    private String id_supplier;
    public Double total;
    private String tanggal_masuk;
    private String created_at;
    private String id_barang_masuk;

    public String getNomor_invoice_masuk() {
        return nomor_invoice_masuk;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getId_supplier() {
        return id_supplier;
    }

    public Double getTotal() {
        return total;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getId_barang_masuk() {
        return id_barang_masuk;
    }
}
