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
import com.example.projectkp.response.DataBarang;
import com.example.projectkp.ui.Activity.RestockActivity;
import com.example.projectkp.ui.Activity.RestockActivity2;

import java.util.List;

public class BarangRestockAdapter extends RecyclerView.Adapter<BarangRestockAdapter.VHRestockBarang> {

    private Context ctx;
    private List<DataBarang> ListBarang;

    public BarangRestockAdapter(Context ctx,List<DataBarang> listBarang){
        this.ctx = ctx;
        this.ListBarang = listBarang;
    }

    @NonNull
    @Override
    public BarangRestockAdapter.VHRestockBarang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_barang, parent, false);//ganti layout nanti
        return new BarangRestockAdapter.VHRestockBarang(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangRestockAdapter.VHRestockBarang holder, int position) {
        DataBarang MN = ListBarang.get(position);
        holder.tvidBarang.setText(MN.getId_barang());
        holder.tvNamaBarang.setText(MN.getNama_barang());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, RestockActivity2.class);
                intent.putExtra("id_barang",MN.getId_barang());
                Log.d("id_barang", MN.getId_barang());
                intent.putExtra("nama_barang",MN.getNama_barang());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ListBarang.size();
    }

    public void setData(List<DataBarang> newData){
        ListBarang.clear();
        if (newData != null){
            ListBarang.addAll(newData);
        }
        notifyDataSetChanged();
    }

    public static class VHRestockBarang extends RecyclerView.ViewHolder{

        TextView tvidBarang,tvNamaBarang;
        public VHRestockBarang(@NonNull View itemView) {
            super(itemView);
            tvidBarang = itemView.findViewById(R.id.tv_id_barang_tampilan);
            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang_tampilan);
        }
    }
}
