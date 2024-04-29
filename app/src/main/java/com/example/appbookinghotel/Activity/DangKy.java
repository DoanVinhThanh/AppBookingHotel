package com.example.appbookinghotel.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.appbookinghotel.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class DangKy extends AppCompatActivity {

    private boolean passowrdShowing1 = false;
    private boolean passowrdShowing2 = false;
    private EditText edt_Hoten_DK, edt_Email_DK, edt_SDT_DK, edt_matkhau_DK, edt_XacNhanMatKhau_DK;
    private ImageView passwordIcon1,passwordIcon2;

    private ProgressDialog progressDialog_DK;

    TextView btn_DangNhap_DK;
    private AppCompatButton btn_DangKy_DK;

    FirebaseAuth fAuth_DK;
    FirebaseFirestore fStore_DK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        Anhxa();
        // Lấy layout gốc của Activity
        View rootView = getWindow().getDecorView().getRootView();

        // Thiết lập một OnTouchListener cho layout gốc
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Ẩn bàn phím
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });
        passwordIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passowrdShowing1){
                    passowrdShowing1 = false;

                    edt_matkhau_DK.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon1.setImageResource(R.drawable.show);
                }else {
                    passowrdShowing1 = true;

                    edt_matkhau_DK.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon1.setImageResource(R.drawable.hide);
                }

                edt_matkhau_DK.setSelection(edt_matkhau_DK.length());

            }
        });
        passwordIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passowrdShowing2){
                    passowrdShowing2 = false;

                    edt_XacNhanMatKhau_DK.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon2.setImageResource(R.drawable.show);
                }else {
                    passowrdShowing2 = true;

                    edt_XacNhanMatKhau_DK.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon2.setImageResource(R.drawable.hide);
                }

                edt_XacNhanMatKhau_DK.setSelection(edt_XacNhanMatKhau_DK.length());

            }
        });
        btn_DangNhap_DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
                finish();
            }
        });

        btn_DangKy_DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
                edt_Hoten_DK.setText("");
                edt_Email_DK.setText("");
                edt_matkhau_DK.setText("");
                edt_XacNhanMatKhau_DK.setText("");
                edt_SDT_DK.setText("");
            }
        });

    }
    private void Anhxa() {

        edt_Hoten_DK = findViewById(R.id.Edt_HoVaTen_DK);
        edt_Email_DK = findViewById(R.id.Edt_Email_DK);
        edt_SDT_DK = findViewById(R.id.Edt_SoDienThoai_DK);
        edt_matkhau_DK = findViewById(R.id.Edt_MatKhau_DK);
        edt_XacNhanMatKhau_DK = findViewById(R.id.Edt_XacNhanMatKhau_DK);

        passwordIcon1 = findViewById(R.id.Show_icon);
        passwordIcon2 = findViewById(R.id.Show_icon2);

        btn_DangNhap_DK = findViewById(R.id.DangNhapBtn_DK);
        btn_DangKy_DK = findViewById(R.id.DangKyBtn_DK);

        progressDialog_DK = new ProgressDialog(this);
        fAuth_DK = FirebaseAuth.getInstance();
        fStore_DK = FirebaseFirestore.getInstance();

    }
    private void showError(EditText mEdt, String s)
    {
        mEdt.setError(s);
    }
    private boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$");
    }
    private void register() {
        String email, pass,repass;
        email = edt_Email_DK.getText().toString();
        pass = edt_matkhau_DK.getText().toString();
        repass= edt_XacNhanMatKhau_DK.getText().toString();
        String hoten = edt_Hoten_DK.getText().toString();
        String sdt = edt_SDT_DK.getText().toString();

        if(TextUtils.isEmpty(hoten)){
            showError(edt_Hoten_DK,"Vui lòng nhập họ tên");
            return;
        }
        if(TextUtils.isEmpty(email)){
            showError(edt_Email_DK,"Vui lòng nhập Email!!");
            edt_Email_DK.requestFocus();
            return;
        }
        if(!email.contains("@gmail.com")){
            showError(edt_Email_DK, "Email đăng ký không hợp lệ");
            edt_Email_DK.requestFocus();
            return;
        }
        if (sdt.length() < 8 || sdt.length() > 10) {
            showError(edt_SDT_DK, "Số điện thoại phải có 10 số.");
            return;
        }
        if (!isPasswordValid(pass)) {
            showError(edt_matkhau_DK,"Mật khẩu phải có chữ in hoa , số và kí tự đặc biệt");
            edt_matkhau_DK.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            showError(edt_matkhau_DK,"Vui lòng nhập password");
            return;
        }
        if(TextUtils.isEmpty(repass)){
            showError(edt_XacNhanMatKhau_DK,"Vui lòng nhập password");
            return;
        }
        if(!pass.equals(repass)){
            showError(edt_XacNhanMatKhau_DK,"Mật khẩu không khớp!");
            edt_XacNhanMatKhau_DK.requestFocus();
            return;
        }
        progressDialog_DK.show();
        fStore_DK.collection("KhachHang").whereEqualTo("Gmail",email).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            if (task.getResult() != null && !task.getResult().isEmpty()){
                                Toast.makeText(DangKy.this, "Email đã tồn tại!", Toast.LENGTH_SHORT).show();
                                progressDialog_DK.dismiss();
                            }
                            else {
                                fAuth_DK.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(DangKy.this, "Tạo tài khoản thành công!!", Toast.LENGTH_SHORT).show();

                                            startActivity(new Intent(DangKy.this,DangNhap.class));

                                            FirebaseUser nguoidung = fAuth_DK.getCurrentUser();
                                            DocumentReference dr=fStore_DK.collection("KhachHang").document(nguoidung.getUid());
                                            Map<String,Object> nguoidunginfo=new HashMap<>();
                                            nguoidunginfo.put("Gmail",email);
                                            nguoidunginfo.put("MatKhau",pass);
                                            nguoidunginfo.put("HoTen",hoten);
                                            nguoidunginfo.put("Sdt",sdt);
                                            nguoidunginfo.put("isUser","1");
                                            dr.set(nguoidunginfo);
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(DangKy.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                        else {Toast.makeText(DangKy.this, "Lỗi kiểm tra email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}