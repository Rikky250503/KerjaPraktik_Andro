package com.example.projectkp.response;

import java.util.List;

public class PengirimanResponse {
    private List<DataItem> data;
    private String message;
    private boolean status;

    public List<DataItem> getData(){
        return data;
    }

    public String getMessage(){
        return message;
    }

    public boolean isStatus(){
        return status;
    }
}
