package com.example.projectkp.ui.Activity;

import android.os.Bundle;
import android.view.MenuItem;

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

import com.example.projectkp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class GudangActivity extends AppCompatActivity {

    private BottomNavigationView bnvGudang;
    private ActionBar judulBarGudang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gudang);

        judulBarGudang = getSupportActionBar();
        judulBarGudang.setTitle("Gudang");
        bukaFragment(new StokGudangFragment());

        bnvGudang = findViewById(R.id.bnv_gudang);
        bnvGudang.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment FrJual;
                if(item.getItemId()==R.id.nav_penjualan_pesanan){
                    bukaFragment(new StokGudangFragment());
                    judulBarGudang.setTitle("Stok");
                }
                else if (item.getItemId()==R.id.nav_penjualan_pemesananSupplier){
                    bukaFragment(new TransaksiGudangFragment());
                    judulBarGudang.setTitle("Transaksi");
                }
                else{
                    bukaFragment(new PemberitahuanGudangFragment());
                    judulBarGudang.setTitle("Pemberitahuan");
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