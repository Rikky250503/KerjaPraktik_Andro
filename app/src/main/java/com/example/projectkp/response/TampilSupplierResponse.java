package com.example.projectkp.response;

import java.util.List;

public class TampilSupplierResponse {
    private List<DataSupplier> data;
    private String message;
    private boolean status;

    public List<DataSupplier> getData(){
        return data;
    }

    public String getMessage(){
        return message;
    }

    public boolean isStatus(){
        return status;
    }
}
