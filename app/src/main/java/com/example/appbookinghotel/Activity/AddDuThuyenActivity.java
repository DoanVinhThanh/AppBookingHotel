package com.example.appbookinghotel.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDuThuyenActivity extends AppCompatActivity {

    int currentImage = 0;  // Đếm số ảnh hiện tại

    private ProgressDialog progressDialog_Add;
    ImageView imagChonDuThuyen, imagChonDuThuyen1, imagChonDuThuyen2, imagChonDuThuyen3, imagChonDuThuyen4, imagChonDuThuyen5;
    EditText edtTenDuThuyen, edtDiaDiemDuThuyen, edtMoTaDuThuyen, edtGiaTienDuThuyen;
    Button btnTaiDuThuyen;
    Uri imageUri, imageUri1, imageUri2, imageUri3, imageUri4, imageUri5;
    StorageReference storageReference;
    FirebaseFirestore firestore;

    LinearLayout btn_back_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_du_thuyen);
        AnhXa();

        // Thiết lập ẩn bàn phím khi bấm vào màn hình
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });

        ChonHinhAnh();

        btnTaiDuThuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null && imageUri1 != null && imageUri2 != null && imageUri3 != null && imageUri4 != null && imageUri5 != null) {
                    Uri[] imageUris = {imageUri, imageUri1, imageUri2, imageUri3, imageUri4, imageUri5};
                    uploadImagesToFirebaseStorage(imageUris);
                    Intent intent = new Intent(AddDuThuyenActivity.this, QuanLyDuThuyen.class);
                    startActivity(intent);
                    finishAffinity();
                } else {
                    Toast.makeText(AddDuThuyenActivity.this, "Yêu cầu chọn đủ 6 hình ảnh", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddDuThuyenActivity.this, QuanLyDuThuyen.class));
                finish();
            }
        });
    }

    private void AnhXa() {
        imagChonDuThuyen = findViewById(R.id.imageChon);
        imagChonDuThuyen1 = findViewById(R.id.imageChon1);
        imagChonDuThuyen2 = findViewById(R.id.imageChon2);
        imagChonDuThuyen3 = findViewById(R.id.imageChon3);
        imagChonDuThuyen4 = findViewById(R.id.imageChon4);
        imagChonDuThuyen5 = findViewById(R.id.imageChon5);

        edtTenDuThuyen = findViewById(R.id.edt_tenDuThuyen_admin);
        edtDiaDiemDuThuyen = findViewById(R.id.edt_diaDiemDuThuyen_admin);
        edtMoTaDuThuyen = findViewById(R.id.edt_moTaDuThuyen_admin);
        edtGiaTienDuThuyen = findViewById(R.id.edt_giaTienDuThuyen_admin);
        btnTaiDuThuyen = findViewById(R.id.btnTaiDuThuyen);
        btn_back_admin = findViewById(R.id.back_admin);

        // Khởi tạo Firebase
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("images/");
        firestore = FirebaseFirestore.getInstance();

        // Khởi tạo ProgressDialog
        progressDialog_Add = new ProgressDialog(this);
        progressDialog_Add.setMessage("Đang tải...");
        progressDialog_Add.setCancelable(false);
    }

    private void ChonHinhAnh() {
        imagChonDuThuyen.setOnClickListener(view -> {
            currentImage = 0;
            openFileChooser();
        });
        imagChonDuThuyen1.setOnClickListener(view -> {
            currentImage = 1;
            openFileChooser();
        });
        imagChonDuThuyen2.setOnClickListener(view -> {
            currentImage = 2;
            openFileChooser();
        });
        imagChonDuThuyen3.setOnClickListener(view -> {
            currentImage = 3;
            openFileChooser();
        });
        imagChonDuThuyen4.setOnClickListener(view -> {
            currentImage = 4;
            openFileChooser();
        });
        imagChonDuThuyen5.setOnClickListener(view -> {
            currentImage = 5;
            openFileChooser();
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null && data.getData() != null) {
            Uri selectedImage = data.getData();
            switch (currentImage) {
                case 0:
                    imageUri = selectedImage;
                    imagChonDuThuyen.setImageURI(imageUri);
                    break;
                case 1:
                    imageUri1 = selectedImage;
                    imagChonDuThuyen1.setImageURI(imageUri1);
                    break;
                case 2:
                    imageUri2 = selectedImage;
                    imagChonDuThuyen2.setImageURI(imageUri2);
                    break;
                case 3:
                    imageUri3 = selectedImage;
                    imagChonDuThuyen3.setImageURI(imageUri3);
                    break;
                case 4:
                    imageUri4 = selectedImage;
                    imagChonDuThuyen4.setImageURI(imageUri4);
                    break;
                case 5:
                    imageUri5 = selectedImage;
                    imagChonDuThuyen5.setImageURI(imageUri5);
                    break;
            }
        }
    }

    private void uploadImagesToFirebaseStorage(Uri[] imageUris) {
        progressDialog_Add.show();

        final List<String> imageUrls = new ArrayList<>();

        for (int i = 0; i < imageUris.length; i++) {
            Uri imageUri = imageUris[i];
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "_" + i + ".jpg");

            int finalI = i;
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                imageUrls.add(uri.toString());

                                // Khi tất cả các URL đã được thêm vào danh sách
                                if (imageUrls.size() == imageUris.length) {
                                    saveDataToFirestore(imageUrls);
                                    progressDialog_Add.dismiss();
                                }
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Lỗi lấy URL hình " + finalI, Toast.LENGTH_SHORT).show();
                                progressDialog_Add.dismiss();
                            })
                    )
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Tải lên hình thất bại cho hình " + finalI, Toast.LENGTH_SHORT).show();
                        progressDialog_Add.dismiss();
                    });
        }
    }

    private void saveDataToFirestore(List<String> imageUrls) {
        String TenDuThuyen = edtTenDuThuyen.getText().toString();
        String DiaDiemDuThuyen = edtDiaDiemDuThuyen.getText().toString();
        String MoTaDuThuyen = edtMoTaDuThuyen.getText().toString();
        String GiaTienDuThuyen = edtGiaTienDuThuyen.getText().toString();

        if (TextUtils.isEmpty(TenDuThuyen)) {
            showError(edtTenDuThuyen, "Vui lòng nhập Tên du thuyền!!");
            return;
        }
        if (TextUtils.isEmpty(DiaDiemDuThuyen)) {
            showError(edtDiaDiemDuThuyen, "Vui lòng nhập địa điểm du thuyền!!");
            return;
        }
        if (TextUtils.isEmpty(MoTaDuThuyen)) {
            showError(edtMoTaDuThuyen, "Vui lòng nhập Mô tả du thuyền!!");
            return;
        }
        if (TextUtils.isEmpty(GiaTienDuThuyen)) {
            showError(edtGiaTienDuThuyen, "Vui lòng nhập Giá du thuyền!!");
            return;
        }

        Map<String, Object> duThuyen = new HashMap<>();
        duThuyen.put("TenDuThuyen", TenDuThuyen);
        duThuyen.put("DiaDiemDuThuyen", DiaDiemDuThuyen);
        duThuyen.put("MoTaDuThuyen", MoTaDuThuyen);
        duThuyen.put("GiaDuThuyen", GiaTienDuThuyen);
        duThuyen.put("HinhAnhDuThuyen", imageUrls.get(0));
        duThuyen.put("HinhAnhDuThuyen1", imageUrls.get(1));
        duThuyen.put("HinhAnhDuThuyen2", imageUrls.get(2));
        duThuyen.put("HinhAnhDuThuyen3", imageUrls.get(3));
        duThuyen.put("HinhAnhDuThuyen4", imageUrls.get(4));
        duThuyen.put("HinhAnhDuThuyen5", imageUrls.get(5));

        firestore.collection("DuThuyen")
                .add(duThuyen)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AddDuThuyenActivity.this, "Tải thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AddDuThuyenActivity.this, "Tải thất bại", Toast.LENGTH_SHORT).show();
                });
    }

    private void showError(EditText mEdt, String s) {
        mEdt.setError(s);
        mEdt.requestFocus();
        progressDialog_Add.dismiss();  // Đảm bảo dừng ProgressDialog nếu có lỗi
    }
}