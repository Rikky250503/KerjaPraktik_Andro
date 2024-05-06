package com.example.projectkp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectkp.R;
import com.example.projectkp.databinding.FragmentPesananSeng1Binding;
import com.example.projectkp.ui.Activity.RestockActivity;
import com.example.projectkp.ui.Activity.SupplierActivity;

public class FragmentPesananSeng1 extends Fragment {
    private FragmentPesananSeng1Binding binding;
    Boolean isAllFabsVisible;
    public FragmentPesananSeng1() {
        // Required empty public constructor
    }

//    public static FragmentPesananSeng1 newInstance(String param1, String param2) {
//        FragmentPesananSeng1 fragment = new FragmentPesananSeng1();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.addNoatapenjualanActionSeng.setVisibility(View.GONE);
        binding.addNotapenjualanFabSeng.setVisibility(View.GONE);
        binding.addSupplierFabSeng.setVisibility(View.GONE);
        binding.addSupplierActionSeng.setVisibility(View.GONE);

        binding.addFabSeng.shrink();
        binding.addFabSeng.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {

                        if (!isAllFabsVisible) {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            binding.addNotapenjualanFabSeng.show();
                            binding.addSupplierFabSeng.show();
                            binding.addNoatapenjualanActionSeng.setVisibility(View.GONE);
                            binding.addSupplierActionSeng.setVisibility(View.GONE);

                            // Now extend the parent FAB, as
                            // user clicks on the shrinked
                            // parent FAB
                            binding.addFabSeng.extend();

                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            binding.addNotapenjualanFabSeng.hide();
                            binding.addSupplierFabSeng.hide();
                            binding.addSupplierActionSeng.setVisibility(View.GONE);
                            binding.addNoatapenjualanActionSeng.setVisibility(View.GONE);

                            // Set the FAB to shrink after user
                            // closes all the sub FABs
                            binding.addFabSeng.shrink();

                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                    }

                });
        binding.addNotapenjualanFabSeng.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getActivity(), RestockActivity.class));
                    }
                });
        binding.addSupplierFabSeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SupplierActivity.class));
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPesananSeng1Binding.inflate(inflater,container,false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pesanan_seng1, container, false);
    }
}