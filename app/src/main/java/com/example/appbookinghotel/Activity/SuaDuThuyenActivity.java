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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.appbookinghotel.Model.App;
import com.example.appbookinghotel.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class SuaDuThuyenActivity extends AppCompatActivity {

    ImageView imagChonDuThuyen;
    EditText edtTenDuThuyen_Sua, edtDiaDiemDuThuyen_Sua, edtMoTaDuThuyen_Sua, edtGiaTienDuThuyen_Sua;
    AppCompatButton btnTaiDuThuyen_Sua,btnXoaDuThuyen_Sua;
    Uri imageUri;
    StorageReference storageReference;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_du_thuyen);
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

        btnTaiDuThuyen_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri != null){
                    uploadImageToFirebaseStorage(imageUri);
                }else {
                    Toast.makeText(SuaDuThuyenActivity.this,"Yêu cầu chọn hình ảnh",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnXoaDuThuyen_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        imagChonDuThuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
    }
    private void Anhxa() {
        imagChonDuThuyen = findViewById(R.id.imageChon_Sua);
        edtTenDuThuyen_Sua = findViewById(R.id.edt_tenDuThuyen_admin_Sua);
        edtDiaDiemDuThuyen_Sua = findViewById(R.id.edt_diaDiemDuThuyen_admin_Sua);
        edtMoTaDuThuyen_Sua = findViewById(R.id.edt_moTaDuThuyen_admin_Sua);
        edtGiaTienDuThuyen_Sua = findViewById(R.id.edt_giaTienDuThuyen_admin_Sua);
        btnTaiDuThuyen_Sua = findViewById(R.id.Edit_admin_Sua);
        btnXoaDuThuyen_Sua = findViewById(R.id.Delete_admin_Sua);

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
                                String TenDuThuyen1 = edtTenDuThuyen_Sua.getText().toString();
                                String DiaDiemDuThuyen1 = edtDiaDiemDuThuyen_Sua.getText().toString();
                                String MoTaDuThuyen1 = edtMoTaDuThuyen_Sua.getText().toString();
                                String GiaTienDuThuyen1 = edtGiaTienDuThuyen_Sua.getText().toString();

                                if(TextUtils.isEmpty(TenDuThuyen1)){
                                    showError(edtTenDuThuyen_Sua,"Vui lòng nhập Tên du thuyền!!");
                                    edtTenDuThuyen_Sua.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(DiaDiemDuThuyen1)){
                                    showError(edtDiaDiemDuThuyen_Sua,"Vui lòng nhập địa điểm du thuyền");
                                    edtDiaDiemDuThuyen_Sua.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(MoTaDuThuyen1)){
                                    showError(edtMoTaDuThuyen_Sua,"Vui lòng nhập Mô tả du thuyền!!");
                                    edtMoTaDuThuyen_Sua.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(GiaTienDuThuyen1)){
                                    showError(edtGiaTienDuThuyen_Sua,"Vui lòng nhập Giá du thuyền");
                                    edtGiaTienDuThuyen_Sua.requestFocus();
                                    return;
                                }
                                Toast.makeText(this, "Tải dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                updateDataToFirestore(uri.toString());

                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Không lấy được URL tải xuống: " , Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Tải ảnh thất bại", Toast.LENGTH_SHORT).show();
                });
    }


    private void updateDataToFirestore(String ImageUri) {

        edtTenDuThuyen_Sua.setText(App.duThuyen.getTenDuThuyen());
        edtDiaDiemDuThuyen_Sua.setText(App.duThuyen.getDiaDiemDuThuyen());
        edtMoTaDuThuyen_Sua.setText(App.duThuyen.getMoTaDuThuyen());
        edtGiaTienDuThuyen_Sua.setText(App.duThuyen.getGiaDuThuyen());

        String TenDuThuyen = edtTenDuThuyen_Sua.getText().toString();
        String DiaDiemDuThuyen = edtDiaDiemDuThuyen_Sua.getText().toString();
        String MoTaDuThuyen = edtMoTaDuThuyen_Sua.getText().toString();
        String GiaTienDuThuyen = edtGiaTienDuThuyen_Sua.getText().toString();


        if(TextUtils.isEmpty(TenDuThuyen)){
            showError(edtTenDuThuyen_Sua,"Vui lòng nhập Tên du thuyền!!");
            edtTenDuThuyen_Sua.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(DiaDiemDuThuyen)){
            showError(edtDiaDiemDuThuyen_Sua,"Vui lòng nhập địa điểm du thuyền");
            edtDiaDiemDuThuyen_Sua.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(MoTaDuThuyen)){
            showError(edtMoTaDuThuyen_Sua,"Vui lòng nhập Mô tả du thuyền!!");
            edtMoTaDuThuyen_Sua.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(GiaTienDuThuyen)){
            showError(edtGiaTienDuThuyen_Sua,"Vui lòng nhập Giá du thuyền");
            edtGiaTienDuThuyen_Sua.requestFocus();
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
                    Toast.makeText(SuaDuThuyenActivity.this, "Tải thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(SuaDuThuyenActivity.this, "Tải thất bại", Toast.LENGTH_SHORT).show();
                });

    }
}