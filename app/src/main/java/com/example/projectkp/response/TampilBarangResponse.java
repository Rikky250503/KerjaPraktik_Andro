package com.example.projectkp.response;

import java.util.List;

public class TampilBarangResponse {

    private List<DataBarang> data;
    private String message;
    private boolean status;

    public List<DataBarang> getDataBarang() {
        return data;
    }

    public String getMessage(){
        return message;
    }

    public boolean isStatus(){
        return status;
    }
}
