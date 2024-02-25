package com.example.appbookinghotel.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appbookinghotel.Adapter.Adapterviewpager;
import com.example.appbookinghotel.R;

import java.util.Timer;
import java.util.TimerTask;


public class TrangChuFragment extends Fragment {

    int currentPage = 0;

    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        Anhxa(view);
        setAutoScrollViewScroll();
        return view;
    }

    private void Anhxa(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

    }
    private void setAutoScrollViewScroll() {
        Adapterviewpager adapterviewpager = new Adapterviewpager(getContext());
        viewPager.setAdapter(adapterviewpager);
        viewPager.setClipToOutline(true);
        Timer timer;
        final long DELAY_MS = 500;
        final long PERIOD_MS = 3000;
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 8) currentPage = 0;
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },DELAY_MS, PERIOD_MS);
    }

}