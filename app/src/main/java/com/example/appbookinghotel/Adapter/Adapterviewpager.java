package com.example.appbookinghotel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.appbookinghotel.R;

public class Adapterviewpager extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    Integer[] images = {R.drawable.hinh1,R.drawable.hinh2,R.drawable.hinh3,R.drawable.hinh5,R.drawable.hinh6,R.drawable.hinh7,R.drawable.hinh8};
    public Adapterviewpager(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_viewpager_trangchu,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_viewpager);
        imageView.setImageResource(images[position]);

        ViewPager viewPager = (ViewPager) container;
        viewPager. addView (view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view =(View) object;
        viewPager.removeView (view) ;
    }
}
