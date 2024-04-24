package com.example.appbookinghotel.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DangNhap extends AppCompatActivity {
    private boolean passowrdShowing = false;
    private EditText edt_Email_DN, edt_MatKhau_DN;
    private ImageView passwordIcon;
    private TextView btn_DangKy_DN;

    private AppCompatButton btn_DangNhap_DN;

    private ProgressDialog progressDialog_DN;

    FirebaseAuth fAuth_DN;
    FirebaseFirestore fStore_DN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
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

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passowrdShowing){
                    passowrdShowing = false;

                    edt_MatKhau_DN.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.show);
                }else {
                    passowrdShowing = true;

                    edt_MatKhau_DN.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.hide);
                }

                edt_MatKhau_DN.setSelection(edt_MatKhau_DN.length());

            }
        });
        btn_DangNhap_DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });
        btn_DangKy_DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void login(){

        String strEmail = edt_Email_DN.getText().toString().trim();
        String strPass = edt_MatKhau_DN.getText().toString().trim();
        if(TextUtils.isEmpty(strEmail)){
            showError(edt_Email_DN,"Vui lòng nhập Email!!");
            edt_Email_DN.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(strPass)){
            showError(edt_MatKhau_DN,"Vui lòng nhập password");
            return;
        }
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            progressDialog_DN.show();

        mAuth.signInWithEmailAndPassword(strEmail, strPass)
                .addOnCompleteListener(DangNhap.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog_DN.dismiss();
                        if (task.isSuccessful()) {
                            // Người dùng đăng nhập thành công
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                // Truy vấn Firestore để kiểm tra quyền của người dùng
                                FirebaseFirestore.getInstance().collection("KhachHang").document(user.getUid())
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document != null && document.exists()) {
                                                        if (document.getString("isUser") != null) {
                                                            // Chuyển hướng đến trang User
                                                            startActivity(new Intent(DangNhap.this, TrangChu.class));
                                                            finish();
                                                        } else if (document.getString("isAdmin") != null) {
                                                            // Chuyển hướng đến trang Admin
                                                            startActivity(new Intent(DangNhap.this, AdminTrangChu.class));
                                                            finish();
                                                        } else {
                                                            // Trường hợp khác, có thể xử lý tại đây
                                                            Toast.makeText(DangNhap.this, "Không có quyền truy cập", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                } else {
                                                    Log.d("TAG", "get failed with ", task.getException());
                                                }
                                            }
                                        });
                            }
                        } else {
                            // Nếu đăng nhập thất bại, hiển thị thông báo cho người dùng.
                            Toast.makeText(DangNhap.this, "Tên tài khoản hoặc mật khẩu không đúng.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showError(EditText mEdt, String s)
    {
        mEdt.setError(s);
    }

    private void Anhxa() {
        edt_Email_DN = findViewById(R.id.Edt_Email_DN);
        edt_MatKhau_DN = findViewById(R.id.Edt_MatKhau_DN);
        passwordIcon = findViewById(R.id.Show_icon);
        btn_DangKy_DN = findViewById(R.id.DangKyBtn_DN);
        btn_DangNhap_DN = findViewById(R.id.DangNhapBtn_DN);
        progressDialog_DN = new ProgressDialog(this);
    }

}