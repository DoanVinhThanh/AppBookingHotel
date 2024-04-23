package com.example.appbookinghotel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.appbookinghotel.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminTrangChu extends AppCompatActivity {

    LinearLayout btn_quanlyduthuyen , btn_quanlydonhang, btn_quanlythanhtoan, btn_quanlydanhgia;

    AppCompatButton btn_dangxuat_admin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_trang_chu);
        AnhXa();

        btn_dangxuat_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminTrangChu.this,DangNhap.class));
            }
        });

        btn_quanlyduthuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminTrangChu.this, QuanLyDuThuyen.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        btn_quanlyduthuyen = findViewById(R.id.quanlyduthuyen);
        btn_quanlydonhang = findViewById(R.id.quanlydonhang);
        btn_quanlythanhtoan = findViewById(R.id.quanlythanhtoan);
        btn_quanlydanhgia = findViewById(R.id.quanlydanhgia);
        btn_dangxuat_admin = findViewById(R.id.btn_DangXuat_Admin);
    }

}