package com.example.projectkp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.response.DataBarang;
import com.example.projectkp.ui.Activity.NotaPenjualan2Activity;
import com.example.projectkp.ui.Activity.RestockActivity2;

import java.util.List;

public class BarangPenjualanAdapter extends RecyclerView.Adapter<BarangPenjualanAdapter.VHRestockBarang> {

    private Context ctx;
    private List<DataBarang> ListBarang;
    private  String source;
    private String idBarangMasuk,idBarangMasukR,idBarangKeluar,idBarangKeluarR;

    public BarangPenjualanAdapter(Context ctx, List<DataBarang> listBarang, String source, String idBarangMasuk, String idBarangMasukR, String idBarangKeluar, String idBarangKeluarR){
        this.ctx = ctx;
        this.ListBarang = listBarang;
        this.source = source;
        this.idBarangMasuk = idBarangMasuk;
        this.idBarangMasukR = idBarangMasukR;
        this.idBarangKeluar = idBarangKeluar;
        this.idBarangKeluarR = idBarangKeluarR;
    }

    @NonNull
    @Override
    public BarangPenjualanAdapter.VHRestockBarang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_barang, parent, false);//ganti layout nanti
        return new BarangPenjualanAdapter.VHRestockBarang(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangPenjualanAdapter.VHRestockBarang holder, int position) {
        DataBarang MN = ListBarang.get(position);
        holder.tvidBarang.setText(MN.getId_barang());
        holder.tvNamaBarang.setText(MN.getNama_barang());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;

                if ("A".equals(source))
                {
                    if(idBarangMasuk == null)
                    {
                        idBarangMasuk = idBarangMasukR;
                    }
                    Context context = holder.itemView.getContext();

                    intent = new Intent(context, RestockActivity2.class);
                    intent.putExtra("id_barang",MN.getId_barang());
                    intent.putExtra("id_barang_masukr", idBarangMasuk);
                    intent.putExtra("nama_barang",MN.getNama_barang());
                    context.startActivity(intent);

                } else if ("B".equals(source)) {
                    if (idBarangKeluar == null)
                    {
                        idBarangKeluar = idBarangKeluarR;
                    }
                    Context context = holder.itemView.getContext();
                    intent = new Intent(ctx, NotaPenjualan2Activity.class);
                    intent.putExtra("id_barang",MN.getId_barang());
                    intent.putExtra("id_barang_keluarR",idBarangKeluar);
                    intent.putExtra("nama_barang",MN.getNama_barang());
                    context.startActivity(intent);
                }
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
