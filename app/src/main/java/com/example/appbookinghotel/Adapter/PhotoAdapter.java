package com.example.appbookinghotel.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbookinghotel.Model.Photo;
import com.example.appbookinghotel.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Photo> mListPhoto;
    private Context mContext;  // Lưu trữ context để sử dụng khi tạo Dialog

    public PhotoAdapter(Context context, List<Photo> mListPhoto) {
        this.mContext = context;
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = mListPhoto.get(position);
        if (photo == null) {
            return;
        }

        // Sử dụng Glide để tải hình ảnh từ URL
        Glide.with(holder.imgPhoto.getContext())
                .load(photo.getUrl())  // Sử dụng URL từ đối tượng Photo
                .into(holder.imgPhoto);

        // Bắt sự kiện nhấn vào hình ảnh
        holder.itemView.setOnClickListener(v -> {
            showZoomDialog(photo.getUrl());
        });
    }

    @Override
    public int getItemCount() {
        if (mListPhoto != null) {
            return mListPhoto.size();
        }
        return 0;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPhoto;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }

    // Phương thức để hiển thị Dialog phóng to hình ảnh
    private void showZoomDialog(String imageUrl) {
        Dialog dialog = new Dialog(mContext, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_image_zoom);

        ImageView imgZoom = dialog.findViewById(R.id.img_zoom);

        // Tải hình ảnh vào ImageView trong Dialog
        Glide.with(mContext)
                .load(imageUrl)
                .into(imgZoom);

        // Đóng dialog khi người dùng nhấn vào hình ảnh
        imgZoom.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}