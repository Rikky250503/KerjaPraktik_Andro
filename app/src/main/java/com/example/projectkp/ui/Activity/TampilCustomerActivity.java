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
import com.example.projectkp.adapter.CustomerAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataCustomer;
import com.example.projectkp.response.TampilCustomerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilCustomerActivity extends AppCompatActivity {

    RecyclerView rv_customer;
    private CustomerAdapter adCustomer;
    private RecyclerView.LayoutManager lmCustomer;
    private List<DataCustomer> ListCustomer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_tampil_customer);

        rv_customer= findViewById(R.id.rv_customer);

        lmCustomer = new LinearLayoutManager(this);
        rv_customer.setLayoutManager(lmCustomer);
        adCustomer = new CustomerAdapter(this, ListCustomer);
        rv_customer.setAdapter(adCustomer);

        retrieveCustomer();

        
        

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void retrieveCustomer() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilCustomerResponse> proses = ARD.ardRetrieveCustomer();

        proses.enqueue(new Callback<TampilCustomerResponse>() {
            @Override
            public void onResponse(Call<TampilCustomerResponse> call, Response<TampilCustomerResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListCustomer = response.body().getData();
                    adCustomer.setData(ListCustomer);
                }
            }

            @Override
            public void onFailure(Call<TampilCustomerResponse> call, Throwable t) {
                Toast.makeText(TampilCustomerActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}