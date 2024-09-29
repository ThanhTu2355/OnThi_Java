/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import util.FileHelper;

/**
 *
 * @author ADMIN
 */
public class QLSinhVien {

    private ArrayList<SinhVien> dsSinhVien;

    public QLSinhVien() {
        dsSinhVien = new ArrayList<>();
    }

    public QLSinhVien(ArrayList<SinhVien> ds) {
        this.dsSinhVien = ds;
    }

    public ArrayList<SinhVien> getDsSinhVien() {
        return dsSinhVien;
    }

    public void setDsSinhVien(ArrayList<SinhVien> dsSinhVien) {
        this.dsSinhVien = dsSinhVien;
    }

    public void DocDanhSachSinhVien(String filename) {
        //sinh viên viết code 
        ArrayList<String> data = FileHelper.readFileText(filename);
        // do du lieu vao danh sach
        dsSinhVien.clear();
        for (String item : data) {
            String[] arr = item.split(";");
            SinhVien sv = new SinhVien();
            sv.setMaso(arr[0]);
            sv.setHoten(arr[1]);
            sv.setGioitinh(Boolean.parseBoolean(arr[2]));
            sv.setDiemTB(Double.parseDouble(arr[3]));
            //them sinh vien vao danh sach
            dsSinhVien.add(sv);
        }
    }

    public boolean GhiDanhSachSinhVien(String filename) {
        //sinh viên viết code 
        ArrayList<String> data = new ArrayList<>();
        for (SinhVien sv : dsSinhVien) {
            String info = sv.getMaso() + ";" + sv.getHoten() + ";" + sv.isGioitinh() + ";" + sv.getDiemTB();
            data.add(info);
        }
        return FileHelper.writeFileText(filename, data);
    }

    public boolean themSV(SinhVien sv) {
        //sinh viên viết code 
        for (SinhVien sinhVien : dsSinhVien) {
            if (sinhVien.getMaso().equals(sv.getMaso())) {
                return false;
            }
        }
        dsSinhVien.add(sv);
        return true;
    }

    public boolean xoaSV(String maso) {
        //sinh viên viết code 
        for (int i = 0; i < dsSinhVien.size(); i++) {
            if (dsSinhVien.get(i).getMaso().equals(maso)) {
                dsSinhVien.remove(i);
                return true;
            }
        }
        return true;
    }

    public void sapXepTheoHocLuc() {
        //sinh viên viết code    
        Comparator<SinhVien> cmp = (sv1, sv2) -> {
            return Double.compare(sv1.getDiemTB(), sv2.getDiemTB());
        };
        Collections.sort(dsSinhVien, cmp);
    }

    public void sapXepTheoMaSV() {
        // Sử dụng Comparator để so sánh mã sinh viên theo thứ tự tăng dần
        Comparator<SinhVien> cmp = (sv1, sv2) -> sv1.getMaso().compareTo(sv2.getMaso());

        // Sắp xếp danh sách sinh viên
        Collections.sort(dsSinhVien, cmp);
    }
}
