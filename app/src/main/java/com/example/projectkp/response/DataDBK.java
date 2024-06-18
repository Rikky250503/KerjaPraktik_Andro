package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

public class DataDBK {
    @SerializedName("id_barang")
    private String id_barang;
    @SerializedName("update_at")
    private String updated_at;
    @SerializedName("kuantitas")
    private Integer kuantitas;
    @SerializedName("harga_barang_keluar")
    private Double harga_barang_keluar;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("id_barang_keluar")
    private String id_barang_keluar;
    @SerializedName("id_detail_barang_keluar")
    private String id_detail_barang_keluar;
    @SerializedName("total")
    private Double total;

    public String getId_barang() {
        return id_barang;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public Integer getKuantitas() {
        return kuantitas;
    }

    public Double getHarga_barang_keluar() {
        return harga_barang_keluar;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getId_barang_keluar() {
        return id_barang_keluar;
    }

    public String getId_detail_barang_keluar() {
        return id_detail_barang_keluar;
    }

    public Double getTotal() {
        return total;
    }
}
