package com.example.projectkp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.NumberFormatter;
import com.example.projectkp.R;
import com.example.projectkp.response.DataBarang;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.ui.Activity.DetailBarangKeluarActivity;

import java.util.List;

public class DetailBarangKeluarAdapter extends RecyclerView.Adapter<DetailBarangKeluarAdapter.VHDetailBarangKeluar>{

    private Context ctx;
    private List<DataTampilKeluar> ListDetailBK;
    private  String source;

    public DetailBarangKeluarAdapter(Context ctx, List<DataTampilKeluar> listDetailBarangKeluar) {
        this.ctx = ctx;
        this.ListDetailBK = listDetailBarangKeluar;
    }

    @NonNull
    @Override
    public VHDetailBarangKeluar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_detail_barang_keluar_penjualan, parent, false);//ganti layout nanti
        return new DetailBarangKeluarAdapter.VHDetailBarangKeluar(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHDetailBarangKeluar holder, int position) {
        DataTampilKeluar MN = ListDetailBK.get(position);

        Double hargasatuan = MN.getHargaBarangKeluar();
        String HargaSatuan = NumberFormatter.formatNumber(hargasatuan);
        holder.tvNamaBarang.setText(MN.getNamaBarang());
        holder.tvKuantitas.setText(String.valueOf(MN.getKuantitas()));
        holder.tvHargaSatuan.setText(HargaSatuan);
    }

    @Override
    public int getItemCount() {
        return ListDetailBK.size();
    }

    public void setData(List<DataTampilKeluar> newData){
        ListDetailBK.clear();
        if (newData != null){
            ListDetailBK.addAll(newData);
        }
        notifyDataSetChanged();
    }
    public static class VHDetailBarangKeluar extends RecyclerView.ViewHolder{

        TextView tvNamaBarang,tvKuantitas,tvHargaSatuan;
        public VHDetailBarangKeluar(@NonNull View itemView) {
            super(itemView);
             tvNamaBarang= itemView.findViewById(R.id.tv_isi_nama_barang_detail_penjualan);
             tvKuantitas = itemView.findViewById(R.id.tv_isi_kuantitas_detail_penjualan);
             tvHargaSatuan = itemView.findViewById(R.id.tv_isi_harga_detail_penjualan);
        }
    }
}