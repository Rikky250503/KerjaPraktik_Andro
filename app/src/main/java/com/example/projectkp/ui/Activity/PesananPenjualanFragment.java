package com.example.projectkp.ui.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectkp.R;
import com.example.projectkp.adapter.BarangKeluarAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataKeluar;
import com.example.projectkp.response.KeluarResponse;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananPenjualanFragment extends Fragment {
    FloatingActionButton mAddNotaFab,mAddSupplierFab,mAddCustomerFab;

    ExtendedFloatingActionButton mAddFab;
    TextView addNotaFabActionText,addSupplierFabActionText,addCustomerFabActionText;
    Boolean isAllFabsVisible;

    RecyclerView rv_pesanan_penjualan;
    private BarangKeluarAdapter adBarangKeluar;
    private RecyclerView.LayoutManager lmBarang;
    private List<DataKeluar> ListBarangKeluar = new ArrayList<>();


//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

//    public PesananPenjualanFragment newInstance(String param1, String param2) {
//        // Required empty public constructor
//        PesananPenjualanFragment fragment = new PesananPenjualanFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAddFab = getView().findViewById(R.id.logo_fab_pesanan);

        mAddNotaFab = getView().findViewById(R.id.fab_add_notapenjualan);
        mAddSupplierFab = getView().findViewById(R.id.fab_add_supplier_baru);
        mAddCustomerFab = getView().findViewById(R.id.fab_add_customer_baru);

        addNotaFabActionText = getView().findViewById(R.id.tv_fab_add_notapenjualan);
        addSupplierFabActionText = getView().findViewById(R.id.tv_fab_add_supplier_baru);
        addCustomerFabActionText = getView().findViewById(R.id.tv_fab_add_customer_baru);

        mAddNotaFab.setVisibility(View.GONE);
        mAddSupplierFab.setVisibility(View.GONE);
        mAddCustomerFab.setVisibility(View.GONE);
        addNotaFabActionText.setVisibility(View.GONE);
        addSupplierFabActionText.setVisibility(View.GONE);
        addCustomerFabActionText.setVisibility(View.GONE);

        isAllFabsVisible = false;

        rv_pesanan_penjualan= view.findViewById(R.id.rv_pesanan);

        lmBarang = new LinearLayoutManager(requireContext());
        rv_pesanan_penjualan.setLayoutManager(lmBarang);
        adBarangKeluar = new BarangKeluarAdapter(requireContext(), ListBarangKeluar);
        rv_pesanan_penjualan.setAdapter(adBarangKeluar);

        retrieveBarangKeluar();

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
                startActivity(new Intent(getActivity(), NotaPenjualanActivity.class));
            }
        });
        mAddSupplierFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SupplierActivity.class));
            }
        });
        mAddCustomerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CustomerBaruActivity.class));
            }
        });
    }

    public void retrieveBarangKeluar(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<KeluarResponse> proses = ARD.ardKeluar();

        proses.enqueue(new Callback<KeluarResponse>() {
            @Override
            public void onResponse(Call<KeluarResponse> call, Response<KeluarResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListBarangKeluar = response.body().getData();

                    adBarangKeluar.setData(ListBarangKeluar);
                }
            }

            @Override
            public void onFailure(Call<KeluarResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_pesanan_penjualan, container, false);
    }
}