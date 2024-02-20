package com.example.appbookinghotel.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbookinghotel.Fragment.DatTauFragment;
import com.example.appbookinghotel.Fragment.TaiKhoanFragment;
import com.example.appbookinghotel.Fragment.TrangChuFragment;
import com.example.appbookinghotel.R;
import com.example.appbookinghotel.databinding.ActivityTrangChuBinding;
public class TrangChu extends AppCompatActivity {

    ActivityTrangChuBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrangChuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new TrangChuFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                replaceFragment(new TrangChuFragment());
            } else if (itemId == R.id.booking) {
                replaceFragment(new DatTauFragment());
            } else if (itemId == R.id.account) {
                replaceFragment(new TaiKhoanFragment());
            }
            return  true;
        } );
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();


    }
}