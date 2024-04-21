package com.example.appbookinghotel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.appbookinghotel.R;

public class DangNhap extends AppCompatActivity {
    private boolean passowrdShowing = false;
    private EditText Edt_Email,Edt_MatKhau;
    private ImageView passwordIcon;
    private TextView DangKyBtn;

    private AppCompatButton Btn_DangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        Anhxa();

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passowrdShowing){
                    passowrdShowing = false;

                    Edt_MatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.show);
                }else {
                    passowrdShowing = true;

                    Edt_MatKhau.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.hide);
                }

                Edt_MatKhau.setSelection(Edt_MatKhau.length());

            }
        });
        Btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, TrangChu.class);

                startActivity(intent);
            }
        });
        DangKyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });


    }

    private void Anhxa() {
         Edt_Email = findViewById(R.id.Edt_Email);
        Edt_MatKhau = findViewById(R.id.Edt_MatKhau);
        passwordIcon = findViewById(R.id.Show_icon);
        DangKyBtn = findViewById(R.id.DangKyBtn);
        Btn_DangNhap = findViewById(R.id.DangNhapBtn);
    }

}