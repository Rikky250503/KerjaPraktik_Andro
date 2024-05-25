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
import android.content.SharedPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import com.example.projectkp.R;
import com.google.gson.Gson;

public class PenjualanActivity extends AppCompatActivity {

    ImageView ivLogoutPesananPenjualanFragment;
    FloatingActionButton mAddNotaFab,mAddSupplierFab,mAddCustomerFab;
    ExtendedFloatingActionButton mAddFab;
    TextView addNotaFabActionText,addSupplierFabActionText,addCustomerFabActionText;
    Boolean isAllFabsVisible;
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
        bnvPenjualan = findViewById(R.id.bnv_penjualan);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String TokenJson = sharedPreferences.getString("Token", null);

//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String userJson = sharedPreferences.getString("Jabatan", null);
        ivLogoutPesananPenjualanFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("Token");
                editor.remove("Jabatan");
                editor.apply();
                Intent intent = new Intent(PenjualanActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        mAddFab = findViewById(R.id.logo_fab_pesanan);

        mAddNotaFab = findViewById(R.id.fab_add_notapenjualan);
        mAddSupplierFab = findViewById(R.id.fab_add_supplier_baru);
        mAddCustomerFab = findViewById(R.id.fab_add_customer_baru);

        addNotaFabActionText = findViewById(R.id.tv_fab_add_notapenjualan);
        addSupplierFabActionText = findViewById(R.id.tv_fab_add_supplier_baru);
        addCustomerFabActionText = findViewById(R.id.tv_fab_add_customer_baru);

        mAddNotaFab.setVisibility(View.GONE);
        mAddSupplierFab.setVisibility(View.GONE);
        mAddCustomerFab.setVisibility(View.GONE);
        addNotaFabActionText.setVisibility(View.GONE);
        addSupplierFabActionText.setVisibility(View.GONE);
        addCustomerFabActionText.setVisibility(View.GONE);

        mAddFab.shrink();
        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs VISIBLE.
                    mAddNotaFab.show();
                    mAddSupplierFab.show();
                    mAddCustomerFab.show();
                    addNotaFabActionText.setVisibility(View.VISIBLE);
                    addSupplierFabActionText.setVisibility(View.VISIBLE);
                    addCustomerFabActionText.setVisibility(View.VISIBLE);

                    // Now extend the parent FAB, as
                    // user clicks on the shrinked
                    // parent FAB
                    mAddFab.extend();

                    // make the boolean variable true as
                    // we have set the sub FABs
                    // visibility to GONE
                    isAllFabsVisible = true;
                } else {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs GONE.
                    mAddNotaFab.hide();
                    mAddSupplierFab.hide();
                    mAddCustomerFab.hide();
                    addNotaFabActionText.setVisibility(View.GONE);
                    addSupplierFabActionText.setVisibility(View.GONE);
                    addCustomerFabActionText.setVisibility(View.GONE);

                    // Set the FAB to shrink after user
                    // closes all the sub FABs
                    mAddFab.shrink();

                    // make the boolean variable false
                    // as we have set the sub FABs
                    // visibility to GONE
                    isAllFabsVisible = false;
                }
            }
        });

        mAddNotaFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenjualanActivity.this, NotaPenjualanActivity.class));
            }
        });

        mAddSupplierFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenjualanActivity.this,SupplierActivity.class));
            }
        });

        mAddCustomerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenjualanActivity.this,CustomerBaruActivity.class));
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
                else{
                    bukaFragment(new PemasukkanPenjualanFragment());
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