package com.example.projectkp.response;

public class DataBarang {
    private String nama_barang;
    private String  id_barang;
    private int id_kategori;
    private int kuantitas;
    private  int harga;
    private Object updated_at;
    private Object created_at;

    public String getNama_barang() {
        return nama_barang;
    }

    public String getId_barang() {
        return id_barang;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public int getHarga() {
        return harga;
    }

    public Object getUpdated_at() {
        return updated_at;
    }

    public Object getCreated_at() {
        return created_at;
    }
}
