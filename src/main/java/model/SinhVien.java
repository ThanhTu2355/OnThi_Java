package model;

public class SinhVien {

    private String maso;
    private String hoten;
    private boolean gioitinh;
    private double diemTB;

    public SinhVien() {
    }

    public SinhVien(String maso, String hoten, boolean gioitinh, double diemTB) {
        this.maso = maso;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.diemTB = diemTB;
    }

    public SinhVien(String maso) {
        this.maso = maso;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }

    public String getHocLuc() {
        String kq = "";
        if (diemTB < 5) {
            kq = "Yeu";
        } else if (diemTB < 6.5) {
            kq = "Trung binh";
        } else if (diemTB < 7.5) {
            kq = "Kha";
        } else if (diemTB < 9) {
            kq = "Gioi";
        } else {
            kq = "Xuat sac";
        }
        return kq;
    }

}
