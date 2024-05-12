package com.example.projectkp.response;

public class DataItem {
    private String nama_status;
    private int id_status;
    private Object updated_at;
    private Object created_at;

    public String getNamaStatus() {
        return nama_status;
    }

    public int getIdStatus() {
        return id_status;
    }

    public Object getUpdatedAt() {
        return updated_at;
    }

    public Object getCreatedAt() {
        return created_at;
    }
}
