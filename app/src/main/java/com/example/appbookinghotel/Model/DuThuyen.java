package com.example.appbookinghotel.Model;

import java.io.Serializable;

public class DuThuyen implements Serializable {

    // Constructor đầy đủ
    public DuThuyen(String id, String tenDuThuyen, String diaDiemDuThuyen, String moTaDuThuyen, String giaDuThuyen, String hinhAnhDuThuyen, String hinhAnhDuThuyen1, String hinhAnhDuThuyen2, String hinhAnhDuThuyen3, String hinhAnhDuThuyen4, String hinhAnhDuThuyen5) {
        this.idDuThuyen = id;
        this.tenDuThuyen = tenDuThuyen;
        this.diaDiemDuThuyen = diaDiemDuThuyen;
        this.moTaDuThuyen = moTaDuThuyen;
        this.giaDuThuyen = giaDuThuyen;
        this.hinhAnhDuThuyen = hinhAnhDuThuyen;
        this.hinhAnhDuThuyen1 = hinhAnhDuThuyen1;
        this.hinhAnhDuThuyen2 = hinhAnhDuThuyen2;
        this.hinhAnhDuThuyen3 = hinhAnhDuThuyen3;
        this.hinhAnhDuThuyen4 = hinhAnhDuThuyen4;
        this.hinhAnhDuThuyen5 = hinhAnhDuThuyen5;
    }

    // Constructor mặc định (nếu cần cho Firestore)
    public DuThuyen() {}

    private String hinhAnhDuThuyen, hinhAnhDuThuyen1, hinhAnhDuThuyen2, hinhAnhDuThuyen3, hinhAnhDuThuyen4, hinhAnhDuThuyen5;
    private String idDuThuyen;
    private String tenDuThuyen;
    private String diaDiemDuThuyen;
    private String moTaDuThuyen;
    private String giaDuThuyen;

    // Tạo các getter và setter cho từng trường
    public String getHinhAnhDuThuyen() {
        return hinhAnhDuThuyen;
    }

    public void setHinhAnhDuThuyen(String hinhAnhDuThuyen) {
        this.hinhAnhDuThuyen = hinhAnhDuThuyen;
    }

    public String getHinhAnhDuThuyen1() {
        return hinhAnhDuThuyen1;
    }

    public void setHinhAnhDuThuyen1(String hinhAnhDuThuyen1) {
        this.hinhAnhDuThuyen1 = hinhAnhDuThuyen1;
    }

    public String getHinhAnhDuThuyen2() {
        return hinhAnhDuThuyen2;
    }

    public void setHinhAnhDuThuyen2(String hinhAnhDuThuyen2) {
        this.hinhAnhDuThuyen2 = hinhAnhDuThuyen2;
    }

    public String getHinhAnhDuThuyen3() {
        return hinhAnhDuThuyen3;
    }

    public void setHinhAnhDuThuyen3(String hinhAnhDuThuyen3) {
        this.hinhAnhDuThuyen3 = hinhAnhDuThuyen3;
    }

    public String getHinhAnhDuThuyen4() {
        return hinhAnhDuThuyen4;
    }

    public void setHinhAnhDuThuyen4(String hinhAnhDuThuyen4) {
        this.hinhAnhDuThuyen4 = hinhAnhDuThuyen4;
    }

    public String getHinhAnhDuThuyen5() {
        return hinhAnhDuThuyen5;
    }

    public void setHinhAnhDuThuyen5(String hinhAnhDuThuyen5) {
        this.hinhAnhDuThuyen5 = hinhAnhDuThuyen5;
    }

    public String getIdDuThuyen() {
        return idDuThuyen;
    }

    public void setIdDuThuyen(String idDuThuyen) {
        this.idDuThuyen = idDuThuyen;
    }

    public String getTenDuThuyen() {
        return tenDuThuyen;
    }

    public void setTenDuThuyen(String tenDuThuyen) {
        this.tenDuThuyen = tenDuThuyen;
    }

    public String getDiaDiemDuThuyen() {
        return diaDiemDuThuyen;
    }

    public void setDiaDiemDuThuyen(String diaDiemDuThuyen) {
        this.diaDiemDuThuyen = diaDiemDuThuyen;
    }

    public String getMoTaDuThuyen() {
        return moTaDuThuyen;
    }

    public void setMoTaDuThuyen(String moTaDuThuyen) {
        this.moTaDuThuyen = moTaDuThuyen;
    }

    public String getGiaDuThuyen() {
        return giaDuThuyen;
    }

    public void setGiaDuThuyen(String giaDuThuyen) {
        this.giaDuThuyen = giaDuThuyen;
    }
}