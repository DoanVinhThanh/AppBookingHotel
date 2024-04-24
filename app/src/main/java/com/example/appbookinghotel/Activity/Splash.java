package com.example.appbookinghotel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbookinghotel.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this,DangNhap.class));
                finish();
                //NextActivity();
            }
        },3800);
    }

    private void NextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            startActivity(new Intent(Splash.this, DangNhap.class));
        }else if (user != null) {
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
                                        startActivity(new Intent(Splash.this, TrangChu.class));
                                        finish();
                                    } else if (document.getString("isAdmin") != null) {
                                        // Chuyển hướng đến trang Admin
                                        startActivity(new Intent(Splash.this, AdminTrangChu.class));
                                        finish();
                                    } else {
                                        // Trường hợp khác, có thể xử lý tại đây
                                        Toast.makeText(Splash.this, "Không có quyền truy cập", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
        }
    }
}