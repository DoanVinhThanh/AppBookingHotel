package com.example.appbookinghotel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbookinghotel.Adapter.DuThuyenAdapter_Admin;
import com.example.appbookinghotel.Model.DuThuyen;
import com.example.appbookinghotel.Model.Firebase;
import com.example.appbookinghotel.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class QuanLyDuThuyen extends AppCompatActivity {

    private RecyclerView rcv_duthuyen_admin;
    private DuThuyenAdapter_Admin duThuyenAdapter_admin;
    private ArrayList<DuThuyen> duThuyens;
    private Firebase firebase;

    private FirebaseFirestore firestore;

    LinearLayout btn_back_admin_qldt;

    AppCompatButton btn_Them_booking,btn_Sua_booking,btn_Xoa_booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_du_thuyen);
        Anhxa();
        setDataForRcv();
        btn_Them_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyDuThuyen.this,AddDuThuyenActivity.class));
                finish();
            }
        });

        btn_back_admin_qldt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyDuThuyen.this, AdminTrangChu.class));
                finish();
            }
        });


    }
    private void setDataForRcv() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QuanLyDuThuyen.this,LinearLayoutManager.VERTICAL,false);
        rcv_duthuyen_admin.setLayoutManager(linearLayoutManager);

        firebase.getAllDuThuyenAdmin(new Firebase.FirebaseCallback<DuThuyen>() {
            @Override
            public void onCallback(ArrayList<DuThuyen> list) {

                duThuyens = list;
                duThuyenAdapter_admin = new DuThuyenAdapter_Admin(QuanLyDuThuyen.this,duThuyens, firebase);
                rcv_duthuyen_admin.setAdapter(duThuyenAdapter_admin);
            }
        });
    }

    private void Anhxa() {
        firebase = new Firebase(QuanLyDuThuyen.this);
        rcv_duthuyen_admin = findViewById(R.id.rcv_DuThuyen_admin);
        btn_Them_booking = findViewById(R.id.btn_Them_Admin);
        btn_Sua_booking = findViewById(R.id.Edit_admin);
        FirebaseApp.initializeApp(QuanLyDuThuyen.this);
        firestore = FirebaseFirestore.getInstance();
        btn_back_admin_qldt = findViewById(R.id.back_admin_QuanLyDuThuyen);
    }
}