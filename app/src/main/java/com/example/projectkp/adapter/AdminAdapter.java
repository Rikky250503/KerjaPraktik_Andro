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
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.DataUsername;
import com.example.projectkp.ui.Activity.DetailBarangKeluarActivity;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.VHadmin> {

    private Context ctx;
    private List<DataUsername> ListAdmin;

    public AdminAdapter(Context ctx, List<DataUsername> listAdmin){
        this.ctx = ctx;
        this.ListAdmin = listAdmin;
    }

    @NonNull
    @Override
    public VHadmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_adm, parent, false);
        return new VHadmin(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHadmin holder, int position) {
        DataUsername MN = ListAdmin.get(position);
        String jabatan = MN.getJabatanUser().toString();
        holder.tvIdStaff.setText(MN.getIdUser());
        holder.tvNamaStaff.setText(MN.getNamaUser());
        holder.tvJabatan.setText(MN.getJabatanUser());
        if (jabatan.equalsIgnoreCase("P"))
        {
            holder.tvJabatan.setText("Pimpinan");
        }
        if (jabatan.equalsIgnoreCase("G"))
        {
            holder.tvJabatan.setText("Gudang");
        }
        if (jabatan.equalsIgnoreCase("J"))
        {
            holder.tvJabatan.setText("Penjualan");
        }
        holder.tvStatusStaff.setText(MN.getStatus().toString());

    }

    @Override
    public int getItemCount() {
        return ListAdmin.size();
    }

    public void setData(List<DataUsername> newData){
        ListAdmin.clear();
        if (newData != null){
            ListAdmin.addAll(newData);
        }
        notifyDataSetChanged();
    }
    public static class VHadmin extends RecyclerView.ViewHolder{

        TextView tvIdStaff,tvNamaStaff, tvJabatan, tvStatusStaff;
        public VHadmin(@NonNull View itemView) {
            super(itemView);
            tvIdStaff = itemView.findViewById(R.id.tv_id_staf);
            tvNamaStaff = itemView.findViewById(R.id.tv_nama_staf);
            tvJabatan = itemView.findViewById(R.id.tv_jabatan_staf);
            tvStatusStaff = itemView.findViewById(R.id.tv_status_staf);

        }
    }
}
