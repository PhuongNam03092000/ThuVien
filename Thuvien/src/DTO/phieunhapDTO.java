package DTO;

public class phieunhapDTO {

    private String mapn;
    private String manv;
    private String mancc;
    private String ngaynhap;
    private long tongtien;

    public phieunhapDTO() {
        mapn = null;
        manv = null;
        mancc = null;
        ngaynhap = null;
        tongtien = 0;
    }

    public phieunhapDTO(String mapn, String manv, String mancc, String ngaynhap, long tongtien) {
        this.mapn = mapn;
        this.manv = manv;
        this.mancc = mancc;
        this.ngaynhap = ngaynhap;
        this.tongtien = tongtien;
    }

    public String getMapn() {
        return mapn;
    }

    public void setMapn(String mapn) {
        this.mapn = mapn;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public String toString() {
        return "phieunhapDTO{" + "mapn=" + mapn + ", manv=" + manv + ", mancc=" + mancc + ", ngaynhap=" + ngaynhap + ", tongtien=" + tongtien + '}';
    }

}
