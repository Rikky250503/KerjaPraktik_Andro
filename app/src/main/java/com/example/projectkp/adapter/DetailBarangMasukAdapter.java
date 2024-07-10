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
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.DataTampilMasuk;

import java.util.List;

public class DetailBarangMasukAdapter extends RecyclerView.Adapter<DetailBarangMasukAdapter.VHDetailBarangMasuk>{
    private Context ctx;
    private List<DataTampilMasuk> ListDetailBM;
    private  String source;

    public DetailBarangMasukAdapter(Context ctx, List<DataTampilMasuk> listDetailBarangMasuk) {
        this.ctx = ctx;
        this.ListDetailBM = listDetailBarangMasuk;

    }

    @NonNull
    @Override
    public DetailBarangMasukAdapter.VHDetailBarangMasuk onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_detail_barang_keluar_penjualan, parent, false);//ganti layout nanti
        return new DetailBarangMasukAdapter.VHDetailBarangMasuk(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailBarangMasukAdapter.VHDetailBarangMasuk holder, int position) {
        DataTampilMasuk MN = ListDetailBM.get(position);
        Double hargasatuan = MN.getHarga_satuan();
        String HargaSatuan = NumberFormatter.formatNumber(hargasatuan);
        holder.tvNamaBarang.setText(MN.getNama_barang());
        holder.tvKuantitas.setText(String.valueOf(MN.getKuantitas()));
        holder.tvHargaSatuan.setText(HargaSatuan);
    }

    @Override
    public int getItemCount() {
        return ListDetailBM.size();
    }

    public void setData(List<DataTampilMasuk> newData){
        ListDetailBM.clear();
        if (newData != null){
            ListDetailBM.addAll(newData);
        }
        notifyDataSetChanged();
    }

    public static class VHDetailBarangMasuk extends RecyclerView.ViewHolder{

        TextView tvNamaBarang,tvKuantitas,tvHargaSatuan;

        public VHDetailBarangMasuk(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang= itemView.findViewById(R.id.tv_isi_nama_barang_detail_penjualan);
            tvKuantitas = itemView.findViewById(R.id.tv_isi_kuantitas_detail_penjualan);
            tvHargaSatuan = itemView.findViewById(R.id.tv_isi_harga_detail_penjualan);
        }
    }

}

