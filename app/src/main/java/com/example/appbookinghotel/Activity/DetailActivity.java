package com.example.appbookinghotel.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbookinghotel.Model.DuThuyen;
import com.example.appbookinghotel.R;

public class DetailActivity extends AppCompatActivity {

    TextView tvNameUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        AnhXa();
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
            return;
        DuThuyen duThuyen = (DuThuyen) bundle.get("object_user");
        tvNameUser.setText(duThuyen.getTenDuThuyen());

    }

    private void AnhXa() {
        tvNameUser = findViewById(R.id.TenDuThuyen_CT);
    }
}