package com.example.projectkp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;

import com.example.projectkp.response.DataSupplier;
import com.example.projectkp.ui.Activity.NotaPenjualanActivity;
import com.example.projectkp.ui.Activity.RestockActivity;

import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.VHSupplier>{
    private Context ctx;
    private List<DataSupplier> ListSupplier;

    public SupplierAdapter(Context ctx,List<DataSupplier> listSupplier){
        this.ctx = ctx;
        this.ListSupplier = listSupplier;
    }

    @NonNull
    @Override
    public SupplierAdapter.VHSupplier onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_supplier, parent, false);
        return new SupplierAdapter.VHSupplier(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHSupplier holder, int position) {
        DataSupplier MN = ListSupplier.get(position);
        holder.tvIdSupplier.setText(MN.getId_supplier());
        holder.tvNama_Supplier.setText(MN.getNama_supplier());
        holder.tvAlamat_Supplier.setText(MN.getAlamat());
        holder.tv_no_hp.setText(MN.getNo_hp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, RestockActivity.class);
                intent.putExtra("id_supplier",MN.getId_supplier());
                Log.d("id_supplier", MN.getId_supplier());
                intent.putExtra("nama_supplier",MN.getNama_supplier());

                context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return ListSupplier.size();
    }

    public void setData(List<DataSupplier> newData){
        ListSupplier.clear();
        if (newData != null){
            ListSupplier.addAll(newData);
        }
        notifyDataSetChanged();
    }
    public static class VHSupplier extends RecyclerView.ViewHolder{

        TextView tvIdSupplier, tvNama_Supplier, tvAlamat_Supplier, tv_no_hp;

        public VHSupplier(@NonNull View itemView) {
            super(itemView);
            tvIdSupplier = itemView.findViewById(R.id.tv_id_supplier);
            tvNama_Supplier  = itemView.findViewById(R.id.tv_nama_supplier);
            tvAlamat_Supplier = itemView.findViewById(R.id.tv_alamat_supplier);
            tv_no_hp = itemView.findViewById(R.id.tv_no_hp_supplier);
        }
    }
}
