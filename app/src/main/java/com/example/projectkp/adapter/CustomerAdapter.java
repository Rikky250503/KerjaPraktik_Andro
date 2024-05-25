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
import com.example.projectkp.response.DataCustomer;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.ui.Activity.NotaPenjualanActivity;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.VHCustomer> {

    private Context ctx;
    private List<DataCustomer> ListCustomer;

    public CustomerAdapter(Context ctx,List<DataCustomer> listCustomer){
        this.ctx = ctx;
        this.ListCustomer = listCustomer;
    }

    @NonNull
    @Override
    public CustomerAdapter.VHCustomer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_customer, parent, false);
        return new CustomerAdapter.VHCustomer(varView);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.VHCustomer holder, int position) {
        DataCustomer MN = ListCustomer.get(position);
        holder.tvIdCustomer.setText(MN.getId_Customer());
        holder.tvNama_Pemesan.setText(MN.getNama_pemesan());
        holder.tvAlamat_Pemesan.setText(MN.getAlamat_pemesan());
        holder.tv_no_hp_pemesan.setText(MN.getNo_hp_pemesan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, NotaPenjualanActivity.class);
                intent.putExtra("id_customer",MN.getId_Customer());
                Log.d("id_customer", MN.getId_Customer());
                intent.putExtra("nama_customer",MN.getNama_pemesan());
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return ListCustomer.size();
    }

    public void setData(List<DataCustomer> newData){
        ListCustomer.clear();
        if (newData != null){
            ListCustomer.addAll(newData);
        }
        notifyDataSetChanged();
    }
    public static class VHCustomer extends RecyclerView.ViewHolder{

        TextView tvIdCustomer, tvNama_Pemesan, tvAlamat_Pemesan, tv_no_hp_pemesan;

        public VHCustomer(@NonNull View itemView) {
            super(itemView);
            tvIdCustomer = itemView.findViewById(R.id.tv_id_customer);
            tvNama_Pemesan  = itemView.findViewById(R.id.tv_nama_customer);
            tvAlamat_Pemesan = itemView.findViewById(R.id.tv_alamat_customer);
            tv_no_hp_pemesan = itemView.findViewById(R.id.tv_no_hp_customer);

        }
    }
}
