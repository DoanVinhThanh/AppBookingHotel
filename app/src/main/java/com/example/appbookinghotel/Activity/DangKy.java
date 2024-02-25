package com.example.appbookinghotel.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbookinghotel.R;

public class DangKy extends AppCompatActivity {

    private boolean passowrdShowing1 = false;
    private boolean passowrdShowing2 = false;
    private EditText Edt_Hoten,Edt_Email,Edt_SDT,Edt_matkhau,Edt_XacNhanMatKhau;
    private ImageView passwordIcon1,passwordIcon2;

    TextView DangNhapBtn;
    private AppCompatButton Btn_DangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        Anhxa();
        passwordIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passowrdShowing1){
                    passowrdShowing1 = false;

                    Edt_matkhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon1.setImageResource(R.drawable.show);
                }else {
                    passowrdShowing1 = true;

                    Edt_matkhau.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon1.setImageResource(R.drawable.hide);
                }

                Edt_matkhau.setSelection(Edt_matkhau.length());

            }
        });
        passwordIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passowrdShowing2){
                    passowrdShowing2 = false;

                    Edt_XacNhanMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon2.setImageResource(R.drawable.show);
                }else {
                    passowrdShowing2 = true;

                    Edt_XacNhanMatKhau.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon2.setImageResource(R.drawable.hide);
                }

                Edt_XacNhanMatKhau.setSelection(Edt_XacNhanMatKhau.length());

            }
        });
        DangNhapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
            }
        });

        Btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }



    private void Anhxa() {

        Edt_Hoten = findViewById(R.id.Edt_HoVaTen);
        Edt_Email = findViewById(R.id.Edt_Email);
        Edt_SDT = findViewById(R.id.Edt_SoDienThoai);
        Edt_matkhau = findViewById(R.id.Edt_MatKhau);
        Edt_XacNhanMatKhau = findViewById(R.id.Edt_XacNhanMatKhau);

        passwordIcon1 = findViewById(R.id.Show_icon);
        passwordIcon2 = findViewById(R.id.Show_icon2);

        DangNhapBtn = findViewById(R.id.DangNhapBtn);
        Btn_DangKy = findViewById(R.id.DangKyBtn);

    }
}