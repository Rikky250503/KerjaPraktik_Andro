package com.example.projectkp.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TampilMasukResponse {

    @SerializedName("data")
    private List<DataTampilMasuk> data;

    @SerializedName("message")
    private String message;

    public List<DataTampilMasuk> getData(){
        return data;
    }

    public String getMessage(){
        return message;
    }
}
