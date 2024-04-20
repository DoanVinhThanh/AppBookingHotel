package com.example.appbookinghotel.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_du_thuyen);
        Anhxa();
        setImageforDuThuyen();


    }
    private void Anhxa() {
        imagChonDuThuyen = findViewById(R.id.imageChon);
        edtTenDuThuyen = findViewById(R.id.edt_tenDuThuyen_admin);
        edtDiaDiemDuThuyen = findViewById(R.id.edt_diaDiemDuThuyen_admin);
        edtGiaTienDuThuyen = findViewById(R.id.edt_giaTienDuThuyen_admin);
        btnTaiDuThuyen = findViewById(R.id.btnTaiDuThuyen);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("images/");
        firestore = FirebaseFirestore.getInstance();
    }
    private void setImageforDuThuyen() {
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

                                Toast.makeText(this, "Tải dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                saveDataToFirestore(uri.toString());

                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Không lấy được URL tải xuống: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    private void saveDataToFirestore(String ImageUri) {
        String TenDuThuyen = edtTenDuThuyen.getText().toString();
        String DiaDiemDuThuyen = edtDiaDiemDuThuyen.getText().toString();
        String MoTaDuThuyen = edtMoTaDuThuyen.getText().toString();
        String GiaTienDuThuyen = edtGiaTienDuThuyen.getText().toString();

        Map<String, String> duThuyen = new HashMap<>();
        duThuyen.put("TenDuThuyen",TenDuThuyen);
        duThuyen.put("DiaDiemDuThuyen",DiaDiemDuThuyen);
        duThuyen.put("MoTaDuThuyen",MoTaDuThuyen);
        duThuyen.put("GiaTienDuThuyen",GiaTienDuThuyen);
        duThuyen.put("HinhAnh",ImageUri);

        firestore.collection("DuThuyen")
                .add(duThuyen)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(QuanLyDuThuyen.this, "Tải thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(QuanLyDuThuyen.this, "Tải thất bại"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }
}