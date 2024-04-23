package com.example.appbookinghotel.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.appbookinghotel.Activity.DangNhap;
import com.example.appbookinghotel.R;
import com.google.firebase.auth.FirebaseAuth;


public class TaiKhoanFragment extends Fragment {
    AppCompatButton btn_dangxuat_user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_tai_khoan, container, false);
         AnhXa(view);
         btn_dangxuat_user.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 FirebaseAuth.getInstance().signOut();
                 signOutUser();
                 getActivity().finishAffinity();
             }
         });
         return view;

    }

    private void signOutUser() {
        Intent intent=new Intent(getContext(), DangNhap.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void AnhXa(View view) {
        btn_dangxuat_user = view.findViewById(R.id.btn_DangXuat_User);
    }
}