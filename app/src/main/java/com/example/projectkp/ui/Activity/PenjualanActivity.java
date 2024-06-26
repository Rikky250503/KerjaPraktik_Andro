package com.example.projectkp.ui.Activity;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.TampilKeluarResponse;
import com.example.projectkp.response.UpdateDataTGResponse;
import com.example.projectkp.response.UpdateResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import com.example.projectkp.R;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenjualanActivity extends AppCompatActivity {

    private ImageView ivLogoutPesananPenjualanFragment, ivRoundedNota, ivroundedSupplier, ivRoundedCustomer,ivDeleteAcc;
    private FloatingActionButton mAddNotaFab,mAddSupplierFab,mAddCustomerFab;
    private ExtendedFloatingActionButton mAddFab;
    private TextView addNotaFabActionText,addSupplierFabActionText,addCustomerFabActionText;
    private Boolean isAllFabsVisible;
    private String token, id_user,status;
    private BottomNavigationView bnvPenjualan;
    private ActionBar judulBarPenjualan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_penjualan);

        judulBarPenjualan = getSupportActionBar();
        bukaFragment(new PesananPenjualanFragment());


        ivLogoutPesananPenjualanFragment = findViewById(R.id.iv_logout_pesanan_penjualan_fragment);
        ivDeleteAcc = findViewById(R.id.iv_delete_acc_pesanan_penjualan_fragment);
        bnvPenjualan = findViewById(R.id.bnv_penjualan);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
         token = sharedPreferences.getString("Token", null).substring(1, 53);
         id_user = sharedPreferences.getString("id_user",null).substring(1,37);


        ivLogoutPesananPenjualanFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(PenjualanActivity.this)
                        .setTitle("Apakah Anda yakin ingin Logout?")

                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("Token");
                                editor.remove("Jabatan");
                                editor.remove("id_user");
                                editor.apply();
                                Intent intent = new Intent(PenjualanActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
            }
        });

        ivDeleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                status = "nonaktif";
                new MaterialAlertDialogBuilder(PenjualanActivity.this)
                        .setTitle("Apakah Anda yakin ingin Menghapus Account ini?")

                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                UpdateAcc();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("Token");
                                editor.remove("Jabatan");
                                editor.remove("id_user");
                                editor.apply();

                                Intent intent = new Intent(PenjualanActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
            }
        });

        mAddFab = findViewById(R.id.logo_fab_pesanan);

        mAddNotaFab = findViewById(R.id.fab_add_notapenjualan);
        mAddSupplierFab = findViewById(R.id.fab_add_supplier_baru);
        mAddCustomerFab = findViewById(R.id.fab_add_customer_baru);

        addNotaFabActionText = findViewById(R.id.tv_fab_add_notapenjualan);
        addSupplierFabActionText = findViewById(R.id.tv_fab_add_supplier_baru);
        addCustomerFabActionText = findViewById(R.id.tv_fab_add_customer_baru);

        View overlay = findViewById(R.id.overlay);

        ivRoundedNota = findViewById(R.id.iv_nota);
        ivroundedSupplier= findViewById(R.id.iv_supplier);
        ivRoundedCustomer= findViewById(R.id.iv_customer);

        mAddNotaFab.setVisibility(View.GONE);
        mAddSupplierFab.setVisibility(View.GONE);
        mAddCustomerFab.setVisibility(View.GONE);
        addNotaFabActionText.setVisibility(View.GONE);
        addSupplierFabActionText.setVisibility(View.GONE);
        addCustomerFabActionText.setVisibility(View.GONE);
        ivRoundedNota.setVisibility(View.GONE);
        ivroundedSupplier.setVisibility(View.GONE);
        ivRoundedCustomer.setVisibility(View.GONE);

        mAddFab.shrink();
        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {

                    mAddNotaFab.show();
                    mAddSupplierFab.show();
                    mAddCustomerFab.show();
                    addNotaFabActionText.setVisibility(View.VISIBLE);
                    addSupplierFabActionText.setVisibility(View.VISIBLE);
                    addCustomerFabActionText.setVisibility(View.VISIBLE);
                    ivRoundedNota.setVisibility(View.VISIBLE);
                    ivroundedSupplier.setVisibility(View.VISIBLE);
                    ivRoundedCustomer.setVisibility(View.VISIBLE);

                    overlay.setVisibility(View.VISIBLE);

                    mAddFab.extend();

                    isAllFabsVisible = true;

                } else {
                    mAddNotaFab.hide();
                    mAddSupplierFab.hide();
                    mAddCustomerFab.hide();
                    addNotaFabActionText.setVisibility(View.GONE);
                    addSupplierFabActionText.setVisibility(View.GONE);
                    addCustomerFabActionText.setVisibility(View.GONE);
                    ivRoundedNota.setVisibility(View.GONE);
                    ivroundedSupplier.setVisibility(View.GONE);
                    ivRoundedCustomer.setVisibility(View.GONE);

                    overlay.setVisibility(View.GONE);

                    mAddFab.shrink();

                    isAllFabsVisible = false;
                }
            }
        });

        mAddNotaFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenjualanActivity.this, NotaPenjualanActivity.class));
                finish();
            }
        });

        mAddSupplierFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenjualanActivity.this, SupplierBaruActivity.class));
                finish();
            }
        });

        mAddCustomerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenjualanActivity.this,CustomerBaruActivity.class));
                finish();
            }
        });

        isAllFabsVisible = false;

        bnvPenjualan.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment FrJual;
                if(item.getItemId()==R.id.nav_penjualan_pesanan){
                    bukaFragment(new PesananPenjualanFragment());
                }
                else if (item.getItemId()==R.id.nav_penjualan_pemesananSupplier){
                    bukaFragment(new PemesananSupplierFragment());
                }
                return true;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            isAllFabsVisible = false;
        }
        return super.onTouchEvent(event);
    }

    private void bukaFragment(Fragment FrJual)
    {
        FragmentManager Fm = getSupportFragmentManager();
        FragmentTransaction Ft = Fm.beginTransaction();
        Ft.replace(R.id.fl_penjualan, FrJual);
        Ft.commit();
    }

    private void UpdateAcc(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<UpdateResponse> proses = ARD.ardUpdate(id_user,status,"Bearer "+ token);

        proses.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if(response.isSuccessful() && response.body()!= null){
                    Toast.makeText(PenjualanActivity.this,"Berhasil menghapus akun",Toast.LENGTH_SHORT).show();
                }
                else {
                    // Handle unsuccessful response
                    String errorMessage = "Error: ";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage += response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        errorMessage += "Response body kosong";
                    }
                    Toast.makeText(PenjualanActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                Toast.makeText(PenjualanActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
                //Log.d("DetailTransaksiGudangActivity", "onFailure: " + t.getMessage());

            }
        });
    }
}