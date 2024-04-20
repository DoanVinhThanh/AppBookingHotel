package com.example.appbookinghotel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbookinghotel.R;

public class AdminTrangChu extends AppCompatActivity {

    LinearLayout btn_quanlyduthuyen , btn_quanlydonhang, btn_quanlythanhtoan, btn_quanlydanhgia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_trang_chu);
        AnhXa();

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
    }

}