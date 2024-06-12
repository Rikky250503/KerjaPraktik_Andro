package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

public class DataDBM {
    @SerializedName("id_barang")
    private String id_barang;
    @SerializedName("update_at")
    private String updated_at;
    @SerializedName("kuantitas")
    private Integer kuantitas;
    @SerializedName("harga_satuan")
    private Double harga_satuan;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("id_barang_masuk")
    private String id_barang_masuk;
    @SerializedName("id_detail_barang_masuk")
    private String id_detail_barang_masuk;
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

    public Double getHarga_satuan() {
        return harga_satuan;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getId_barang_masuk() {
        return id_barang_masuk;
    }

    public String getId_detail_barang_masuk() {
        return id_detail_barang_masuk;
    }

    public Double getTotal() {
        return total;
    }
}
