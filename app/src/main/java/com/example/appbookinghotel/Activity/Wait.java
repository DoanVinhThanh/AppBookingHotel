package com.example.appbookinghotel.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbookinghotel.R;

public class Wait extends AppCompatActivity {

    ProgressDialog progressDialog_wait;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        progressDialog_wait = new ProgressDialog(this);
        progressDialog_wait.show();
        progressDialog_wait.dismiss();
        finish();

    }
}