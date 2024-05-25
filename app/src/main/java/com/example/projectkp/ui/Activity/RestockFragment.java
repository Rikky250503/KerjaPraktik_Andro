package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.projectkp.R;
import com.google.android.material.button.MaterialButton;


public class RestockFragment extends Fragment {

    String noInvoiceMasuk,tanggalMasuk,namaSupplier,createdBy;
    Context ctx;
    EditText etNoInvoiceMasuk,etTanggalMasuk,etNamaSupplier,etCreatedBy;
    MaterialButton btnNextRestock;

    public RestockFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNoInvoiceMasuk = getView().findViewById(R.id.et_invoice_restock);
        etTanggalMasuk = getView().findViewById(R.id.et_tgl_restock);
        etNamaSupplier = getView().findViewById(R.id.et_namaSupplier_restock);
        etCreatedBy = getView().findViewById(R.id.et_createdBy_restock);

        btnNextRestock = getView().findViewById(R.id.btn_next_restock);

        btnNextRestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noInvoiceMasuk = etNoInvoiceMasuk.getText().toString();
                tanggalMasuk = etTanggalMasuk.getText().toString();
                namaSupplier = etNamaSupplier.getText().toString();
                createdBy = etCreatedBy.getText().toString();

                if(noInvoiceMasuk.trim().isEmpty()){
                    etNoInvoiceMasuk.setError("Nomor Invoice Masuk tidak boleh kosong");
                }
                else if(tanggalMasuk.trim().isEmpty()){
                    etTanggalMasuk.setError("Tanggal Masuk tidak boleh Kosong");
                }
                else if(namaSupplier.trim().isEmpty()){
                    etNamaSupplier.setError("Nama Supplier tidak boleh Kosong");
                }
                else if(createdBy.trim().isEmpty()){
                    etCreatedBy.setError("Created by tidak boleh Kosong");
                }
                else{
//                    tambahRestock();
                    Intent intent = new Intent(getActivity(), RestockActivity2.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restock, container, false);
    }
}