package com.example.appbookinghotel.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbookinghotel.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class QuanLyDuThuyen extends AppCompatActivity {
    ImageView imagChonDuThuyen;
    EditText edtTenDuThuyen,edtDiaDiemDuThuyen,edtMoTaDuThuyen,edtGiaTienDuThuyen;
    Button btnTaiDuThuyen;
    Uri imageUri;
    StorageReference storageReference;
    FirebaseFirestore firestore;

    LinearLayout btn_back_admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_du_thuyen);
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

        btnTaiDuThuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri != null){
                    uploadImageToFirebaseStorage(imageUri);
                }else {
                    Toast.makeText(QuanLyDuThuyen.this,"Yêu cầu chọn hình ảnh",Toast.LENGTH_SHORT).show();
                }

            }
        });
        imagChonDuThuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        btn_back_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyDuThuyen.this, AdminTrangChu.class));
            }
        });
    }
    private void Anhxa() {
        imagChonDuThuyen = findViewById(R.id.imageChon);
        edtTenDuThuyen = findViewById(R.id.edt_tenDuThuyen_admin);
        edtDiaDiemDuThuyen = findViewById(R.id.edt_diaDiemDuThuyen_admin);
        edtMoTaDuThuyen = findViewById(R.id.edt_moTaDuThuyen_admin);
        edtGiaTienDuThuyen = findViewById(R.id.edt_giaTienDuThuyen_admin);
        btnTaiDuThuyen = findViewById(R.id.btnTaiDuThuyen);
        btn_back_admin = findViewById(R.id.back_admin);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("images/");
        firestore = FirebaseFirestore.getInstance();

    }
    private void showError(EditText mEdt, String s)
    {
        mEdt.setError(s);
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && data != null && data.getData() != null){
            imageUri = data.getData();
            Log.d("check", imageUri.toString());
            imagChonDuThuyen.setImageURI(imageUri);
        }
        else {
            Log.d("ngu", "fsdkjfs");
        }
    }

    private void uploadImageToFirebaseStorage(Uri imageUri) {
        StorageReference fileReference = storageReference.child(System.currentTimeMillis() + ".jpg");
        fileReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Lấy URL tải xuống của hình ảnh
                    fileReference.getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                String TenDuThuyen1 = edtTenDuThuyen.getText().toString();
                                String DiaDiemDuThuyen1 = edtDiaDiemDuThuyen.getText().toString();
                                String MoTaDuThuyen1 = edtMoTaDuThuyen.getText().toString();
                                String GiaTienDuThuyen1 = edtGiaTienDuThuyen.getText().toString();

                                if(TextUtils.isEmpty(TenDuThuyen1)){
                                    showError(edtTenDuThuyen,"Vui lòng nhập Tên du thuyền!!");
                                    edtTenDuThuyen.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(DiaDiemDuThuyen1)){
                                    showError(edtDiaDiemDuThuyen,"Vui lòng nhập địa điểm du thuyền");
                                    edtDiaDiemDuThuyen.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(MoTaDuThuyen1)){
                                    showError(edtMoTaDuThuyen,"Vui lòng nhập Mô tả du thuyền!!");
                                    edtMoTaDuThuyen.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(GiaTienDuThuyen1)){
                                    showError(edtGiaTienDuThuyen,"Vui lòng nhập Giá du thuyền");
                                    edtGiaTienDuThuyen.requestFocus();
                                    return;
                                }
                                Toast.makeText(this, "Tải dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                saveDataToFirestore(uri.toString());

                                edtTenDuThuyen.setText("");
                                edtDiaDiemDuThuyen.setText("");
                                edtMoTaDuThuyen.setText("");
                                edtGiaTienDuThuyen.setText("");

                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Không lấy được URL tải xuống: " , Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Tải ảnh thất bại", Toast.LENGTH_SHORT).show();
                });
    }


    private void saveDataToFirestore(String ImageUri) {
        String TenDuThuyen = edtTenDuThuyen.getText().toString();
        String DiaDiemDuThuyen = edtDiaDiemDuThuyen.getText().toString();
        String MoTaDuThuyen = edtMoTaDuThuyen.getText().toString();
        String GiaTienDuThuyen = edtGiaTienDuThuyen.getText().toString();

        if(TextUtils.isEmpty(TenDuThuyen)){
            showError(edtTenDuThuyen,"Vui lòng nhập Tên du thuyền!!");
            edtTenDuThuyen.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(DiaDiemDuThuyen)){
            showError(edtDiaDiemDuThuyen,"Vui lòng nhập địa điểm du thuyền");
            edtDiaDiemDuThuyen.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(MoTaDuThuyen)){
            showError(edtMoTaDuThuyen,"Vui lòng nhập Mô tả du thuyền!!");
            edtMoTaDuThuyen.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(GiaTienDuThuyen)){
            showError(edtGiaTienDuThuyen,"Vui lòng nhập Giá du thuyền");
            edtGiaTienDuThuyen.requestFocus();
            return;
        }
        Map<String, Object> duThuyen = new HashMap<>();
        duThuyen.put("TenDuThuyen",TenDuThuyen);
        duThuyen.put("DiaDiemDuThuyen",DiaDiemDuThuyen);
        duThuyen.put("MoTaDuThuyen",MoTaDuThuyen);
        duThuyen.put("GiaDuThuyen",GiaTienDuThuyen);
        duThuyen.put("HinhAnhDuThuyen",ImageUri);

        firestore.collection("DuThuyen")
                .add(duThuyen)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(QuanLyDuThuyen.this, "Tải thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(QuanLyDuThuyen.this, "Tải thất bại", Toast.LENGTH_SHORT).show();
                });

    }
}