package DTO;

public class ctpnDTO {

    private String mapn;
    private String masach;
    private int soluong;
    private long dongia;
    private long thanhtien;

    public ctpnDTO() {
        mapn = null;
        masach = null;
        soluong = 0;
        dongia = 0;
        thanhtien = 0;
    }

    @Override
    public String toString() {
        return "ctpnDTO{" + "mapn=" + mapn + ", masach=" + masach + ", soluong=" + soluong + ", dongia=" + dongia + ", thanhtien=" + thanhtien + '}';
    }

    public ctpnDTO(String mapn, String masach, int soluong, long dongia, long thanhtien) {
        this.mapn = mapn;
        this.masach = masach;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    

    public String getMapn() {
        return mapn;
    }

    public void setMapn(String mapn) {
        this.mapn = mapn;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getDongia() {
        return dongia;
    }

    public void setDongia(long dongia) {
        this.dongia = dongia;
    }

    public long getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(long thanhtien) {
        this.thanhtien = thanhtien;
    }

}
