package com.example.appbookinghotel.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appbookinghotel.Adapter.PhotoAdapter;
import com.example.appbookinghotel.Model.DuThuyen;
import com.example.appbookinghotel.Model.Photo;
import com.example.appbookinghotel.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class DetailActivity extends AppCompatActivity {

    private ViewPager2 mViewpager2;

    private CircleIndicator3 mCircleIndicator3;
    private List<Photo> mListphoto;
    TextView tvNameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        AnhXa();

        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
            return;
        DuThuyen duThuyen = (DuThuyen) bundle.get("object_booking");
        tvNameUser.setText(duThuyen.getTenDuThuyen());
        
        mListphoto = getPhotoList(duThuyen);
        PhotoAdapter photoAdapter = new PhotoAdapter(this,mListphoto);
        mViewpager2.setAdapter(photoAdapter);
        mCircleIndicator3.setViewPager(mViewpager2);
        //setting viewpager 2
        mViewpager2.setOffscreenPageLimit(3);
        mViewpager2.setClipToPadding(false);
        mViewpager2.setClipChildren(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1- Math.abs(position);
                page.setScaleY((float) (0.85 + r * 0.15f));
            }
        });
        mViewpager2.setPageTransformer(compositePageTransformer);

        

    }

    private List<Photo> getPhotoList(DuThuyen duThuyen) {
        List<Photo> list = new ArrayList<>();

        // Thêm các hình ảnh từ DuThuyen vào danh sách Photo
        if (duThuyen.getHinhAnhDuThuyen() != null) {
            list.add(new Photo(duThuyen.getHinhAnhDuThuyen()));  // Hình ảnh từ Firestore
        }
        if (duThuyen.getHinhAnhDuThuyen1() != null) {
            list.add(new Photo(duThuyen.getHinhAnhDuThuyen1()));
        }
        if (duThuyen.getHinhAnhDuThuyen2() != null) {
            list.add(new Photo(duThuyen.getHinhAnhDuThuyen2()));
        }
        if (duThuyen.getHinhAnhDuThuyen3() != null) {
            list.add(new Photo(duThuyen.getHinhAnhDuThuyen3()));
        }
        if (duThuyen.getHinhAnhDuThuyen4() != null) {
            list.add(new Photo(duThuyen.getHinhAnhDuThuyen4()));
        }
        if (duThuyen.getHinhAnhDuThuyen5() != null) {
            list.add(new Photo(duThuyen.getHinhAnhDuThuyen5()));
        }

        return list;
    }
    private void AnhXa() {
        tvNameUser = findViewById(R.id.TenDuThuyen_CT);
        mViewpager2 = findViewById(R.id.view_pager_2);
        mCircleIndicator3 = findViewById(R.id.circle_indicator_3);
    }
}