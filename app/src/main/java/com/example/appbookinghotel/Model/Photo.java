package com.example.appbookinghotel.Model;

public class Photo {

    private String url;  // Đổi int thành String để lưu URL hình ảnh

    public Photo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}