package com.example.projectkp.response;

import java.util.List;

public class TampilAdminResponse {
    private List<DataUsername> data;
    private String message;
    private boolean status;

    public List<DataUsername> getData() {
        return data;
    }

    public String getMessage(){
        return message;
    }

    public boolean isStatus(){
        return status;
    }

}
