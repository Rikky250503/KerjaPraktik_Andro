package com.example.projectkp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.response.DataBarang;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.ui.Activity.DetailBarangKeluarActivity;

import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.VHLaporan> {
    private Context ctx;
    private List<DataBarang> ListLaporan;

    public LaporanAdapter(Context ctx, List<DataBarang> listLaporan){
        this.ctx = ctx;
        this.ListLaporan = listLaporan;
    }

    @NonNull
    @Override
    public LaporanAdapter.VHLaporan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_laporan, parent, false);
        return new LaporanAdapter.VHLaporan(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanAdapter.VHLaporan holder, int position) {
        DataBarang MN = ListLaporan.get(position);
        holder.tvNamaBarang.setText(MN.getNama_barang());
        holder.tvJumlah.setText(String.valueOf(MN.getKuantitas()));
    }

    @Override
    public int getItemCount() {
        return ListLaporan.size();
    }

    public void setData(List<DataBarang> newData){
        ListLaporan.clear();
        if (newData != null){
            ListLaporan.addAll(newData);
        }
        notifyDataSetChanged();
    }

    public static class VHLaporan extends RecyclerView.ViewHolder{

        TextView tvNamaBarang, tvJumlah;
        public VHLaporan(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.tv_nb_laporan);
            tvJumlah = itemView.findViewById(R.id.tv_jmlh_laporan);
        }
    }

}
