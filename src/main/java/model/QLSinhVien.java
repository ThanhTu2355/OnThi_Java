/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

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
    
    public void DocDanhSachSinhVien(String filename)
    {
        //sinh viên viết code 
    }

    public boolean GhiDanhSachSinhVien(String filename)
    {      
        //sinh viên viết code 
        return true;
    }    
    
    public boolean themSV(SinhVien sv)
    {
       //sinh viên viết code 
        return true;
    }  
    
    public boolean xoaSV(String maso)
    {
        //sinh viên viết code 
        return true;
    }     
   
    public void sapXepTheoHocLuc()
    {
        //sinh viên viết code         
    }   
    
}
