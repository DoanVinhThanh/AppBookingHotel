package com.example.appbookinghotel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbookinghotel.Model.DuThuyen;
import com.example.appbookinghotel.Model.Firebase;
import com.example.appbookinghotel.R;

import java.util.ArrayList;

public class DuThuyenAdapter_Admin extends RecyclerView.Adapter<DuThuyenAdapter_Admin.DuThuyenVHAdmin> {
    private Context context;
    private ArrayList<DuThuyen> duThuyens;
    private Firebase firebase;



    public DuThuyenAdapter_Admin(Context context, ArrayList<DuThuyen> duThuyens, Firebase firebase) {
        this.context = context;
        this.duThuyens = duThuyens;
        this.firebase = firebase;
    }

    @NonNull
    @Override
    public DuThuyenAdapter_Admin.DuThuyenVHAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.update_item_booking,parent,false);
        return new DuThuyenAdapter_Admin.DuThuyenVHAdmin(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuThuyenAdapter_Admin.DuThuyenVHAdmin holder, int position) {
        DuThuyen duThuyen = duThuyens.get(position);
        holder.tvTenDuThuyen.setText(duThuyen.getTenDuThuyen());
        holder.tvDiaDiemDuThuyen.setText(duThuyen.getDiaDiemDuThuyen());
        holder.tvMotaDuThuyen.setText(duThuyen.getMoTaDuThuyen());
        holder.tvGiaDuThuyen.setText(duThuyen.getGiaDuThuyen() + "đ / khách");
        Glide.with(context).load(duThuyen.getHinhAnhDuThuyen()).into(holder.img_DuThuyen);
    }


    @Override
    public int getItemCount() {
        return duThuyens.size();
    }

    public static class DuThuyenVHAdmin extends RecyclerView.ViewHolder{

        public TextView tvMaDuThuyen,tvTenDuThuyen,tvDiaDiemDuThuyen,tvMotaDuThuyen,tvGiaDuThuyen;
        public ImageView img_DuThuyen;

        public AppCompatButton btn_Update_admin;

        public DuThuyenVHAdmin(@NonNull View itemView) {
            super(itemView);
            tvTenDuThuyen = itemView.findViewById(R.id.name_booking_admin);
            tvDiaDiemDuThuyen = itemView.findViewById(R.id.DiaDiem_DuThuyen_admin);
            tvMotaDuThuyen = itemView.findViewById(R.id.Mota_DuThuyen_admin);
            tvGiaDuThuyen = itemView.findViewById(R.id.giatien_Duthuyen_admin);
            img_DuThuyen = itemView.findViewById(R.id.img_booking_admin);
            btn_Update_admin = itemView.findViewById(R.id.Edit_admin);
        }
    }
}
