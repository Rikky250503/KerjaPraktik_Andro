package com.example.projectkp.ui.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectkp.R;
import com.example.projectkp.databinding.FragmentPesananPenjualanBinding;
import com.example.projectkp.databinding.FragmentPesananSeng1Binding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PesananPenjualanFragment extends Fragment {
    FloatingActionButton mAddNotaFab,mAddSupplierFab,mAddCustomerFab;

    ExtendedFloatingActionButton mAddFab;
    TextView addNotaFabActionText,addSupplierFabActionText,addCustomerFabActionText;
    Boolean isAllFabsVisible;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pesanan_penjualan, container, false);
    }
}