package com.example.appbookinghotel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbookinghotel.Activity.DetailActivity;
import com.example.appbookinghotel.Model.DuThuyen;
import com.example.appbookinghotel.Model.Firebase;
import com.example.appbookinghotel.R;

import java.util.ArrayList;

public class DuThuyenAdapter extends RecyclerView.Adapter<DuThuyenAdapter.DuThuyenVH> {
    private Context context;
    private ArrayList<DuThuyen> duThuyens;
    private Firebase firebase;



    public DuThuyenAdapter(Context context, ArrayList<DuThuyen> duThuyens, Firebase firebase) {
        this.context = context;
        this.duThuyens = duThuyens;
        this.firebase = firebase;
    }

    @NonNull
    @Override
    public DuThuyenAdapter.DuThuyenVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking,parent,false);
        return new DuThuyenAdapter.DuThuyenVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuThuyenAdapter.DuThuyenVH holder, int position) {
        DuThuyen duThuyen = duThuyens.get(position);
        holder.tvTenDuThuyen.setText(duThuyen.getTenDuThuyen());
        holder.tvDiaDiemDuThuyen.setText(duThuyen.getDiaDiemDuThuyen());
        holder.tvMotaDuThuyen.setText(duThuyen.getMoTaDuThuyen());
        holder.tvGiaDuThuyen.setText(duThuyen.getGiaDuThuyen() + "đ / khách");
        Glide.with(context).load(duThuyen.getHinhAnhDuThuyen()).into(holder.img_DuThuyen);
        holder.btn_DatNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(duThuyen);
            }
        });
    }

    private void onClickGoToDetail(DuThuyen duThuyen) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user",duThuyen);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return duThuyens.size();
    }

    public static class DuThuyenVH extends RecyclerView.ViewHolder{

        public TextView tvMaDuThuyen,tvTenDuThuyen,tvDiaDiemDuThuyen,tvMotaDuThuyen,tvGiaDuThuyen;
        public ImageView img_DuThuyen;

        public AppCompatButton btn_DatNgay;

        public DuThuyenVH(@NonNull View itemView) {
            super(itemView);
            tvTenDuThuyen = itemView.findViewById(R.id.name_booking);
            tvDiaDiemDuThuyen = itemView.findViewById(R.id.DiaDiem_DuThuyen);
            tvMotaDuThuyen = itemView.findViewById(R.id.Mota_DuThuyen);
            tvGiaDuThuyen = itemView.findViewById(R.id.giatien_Duthuyen);
            img_DuThuyen = itemView.findViewById(R.id.img_booking);
            btn_DatNgay = itemView.findViewById(R.id.DatNgay);



        }
    }
}
