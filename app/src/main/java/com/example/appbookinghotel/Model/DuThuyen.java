package com.example.appbookinghotel.Model;

import java.io.Serializable;

public class DuThuyen implements Serializable {

    private String hinhAnhDuThuyen;
    private String idDuThuyen;
    private String tenDuThuyen;
    private String diaDiemDuThuyen;
    private String moTaDuThuyen;
    private String giaDuThuyen;
    public DuThuyen(){}
    public DuThuyen(String idDuThuyen, String tenDuThuyen, String diaDiemDuThuyen, String moTaDuThuyen, String giaDuThuyen, String hinhAnhDuThuyen) {
        this.idDuThuyen = idDuThuyen;
        this.tenDuThuyen = tenDuThuyen;
        this.diaDiemDuThuyen = diaDiemDuThuyen;
        this.moTaDuThuyen = moTaDuThuyen;
        this.giaDuThuyen = giaDuThuyen;
        this.hinhAnhDuThuyen = hinhAnhDuThuyen;
    }

    public String getHinhAnhDuThuyen() {
        return hinhAnhDuThuyen;
    }

    public void setHinhAnhDuThuyen(String hinhAnhDuThuyen) {
        this.hinhAnhDuThuyen = hinhAnhDuThuyen;
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
