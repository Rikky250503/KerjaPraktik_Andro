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

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import com.example.projectkp.R;

public class PenjualanActivity extends AppCompatActivity {
    private BottomNavigationView bnvPenjualan;
    private ActionBar judulBarPenjualan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_penjualan);

        judulBarPenjualan = getSupportActionBar();
        judulBarPenjualan.setTitle("Penjualan");
        bukaFragment(new PesananPenjualanFragment());

        bnvPenjualan = findViewById(R.id.bnv_penjualan);
        bnvPenjualan.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment FrJual;
                if(item.getItemId()==R.id.nav_penjualan_pesanan){
                    bukaFragment(new PesananPenjualanFragment());
                    judulBarPenjualan.setTitle("Orders");
                }
                else if (item.getItemId()==R.id.nav_penjualan_pemesananSupplier){
                    bukaFragment(new RestockFragment());
                    judulBarPenjualan.setTitle("Supplier");
                }
                else{
                    bukaFragment(new PemasukkanPenjualanFragment());
                    judulBarPenjualan.setTitle("Pemasukkan");
                }
                return true;
//                switch (item.getItemId())
//                {
//                    case R.id.nav_penjualan_pesanan:
//                        bukaFragment(new PesananPenjualanFragment());
//                        judulBarPenjualan.setTitle("Orders");
//                        return true;
//                    case R.id.nav_penjualan_pemesananSupplier:
//                        bukaFragment(new RestockFragment());
//                        judulBarPenjualan.setTitle("Supplier");
//                        return true;
//
//                    case R.id.nav_penjualan_pemasukkan:
//                        bukaFragment(new PemasukkanPenjualanFragment());
//                        judulBarPenjualan.setTitle("Pemasukkan");
//                        return true;
//                }
//                return false;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void bukaFragment(Fragment FrJual)
    {
        FragmentManager Fm = getSupportFragmentManager();
        FragmentTransaction Ft = Fm.beginTransaction();
        Ft.replace(R.id.fl_penjualan, FrJual);
        Ft.commit();
    }
}