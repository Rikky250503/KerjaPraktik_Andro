package com.example.projectkp.ui.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectkp.R;
import com.example.projectkp.adapter.SupplierAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataSupplier;
import com.example.projectkp.response.TampilSupplierResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilSupplierActivity extends AppCompatActivity {

    RecyclerView rv_supplier;
    private SupplierAdapter adSupplier;
    private RecyclerView.LayoutManager lmSupplier;
    private List<DataSupplier> ListSupplier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_tampil_supplier);

        rv_supplier= findViewById(R.id.rv_supplier);

        lmSupplier = new LinearLayoutManager(this);
        rv_supplier.setLayoutManager(lmSupplier);
        adSupplier = new SupplierAdapter(this, ListSupplier);
        rv_supplier.setAdapter(adSupplier);

        retrieveSupplier();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void retrieveSupplier() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilSupplierResponse> proses = ARD.ardRetrieveSupplier();

        proses.enqueue(new Callback<TampilSupplierResponse>() {
            @Override
            public void onResponse(Call<TampilSupplierResponse> call, Response<TampilSupplierResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListSupplier = response.body().getData();
                    adSupplier.setData(ListSupplier);
                }
            }
            @Override
            public void onFailure(Call<TampilSupplierResponse> call, Throwable t) {
                Toast.makeText(TampilSupplierActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}