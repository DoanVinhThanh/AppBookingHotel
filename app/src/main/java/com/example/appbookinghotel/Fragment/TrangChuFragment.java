package com.example.appbookinghotel.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appbookinghotel.Adapter.Adapterviewpager;
import com.example.appbookinghotel.Adapter.DuThuyenAdapter;
import com.example.appbookinghotel.Model.DuThuyen;
import com.example.appbookinghotel.Model.Firebase;
import com.example.appbookinghotel.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class TrangChuFragment extends Fragment {
    int currentPage = 0;
    private ViewPager viewPager;
    private RecyclerView rcv_duthuyen;
    private DuThuyenAdapter duThuyenAdapter;
    private ArrayList<DuThuyen> duThuyens;

    private Firebase firebase;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        Anhxa(view);
        setAutoScrollViewScroll();
        setDataForRcv();
        return view;
    }

    private void setDataForRcv() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcv_duthuyen.setLayoutManager(linearLayoutManager);

        firebase.getAllDuThuyen(new Firebase.FirebaseCallback<DuThuyen>() {
            @Override
            public void onCallback(ArrayList<DuThuyen> list) {

                duThuyens = list;
                duThuyenAdapter = new DuThuyenAdapter(getContext(),duThuyens, firebase);
                rcv_duthuyen.setAdapter(duThuyenAdapter);
            }
        });
    }

    private void Anhxa(View view) {
        firebase = new Firebase(getContext());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        rcv_duthuyen = view.findViewById(R.id.rcv_DuThuyen);
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