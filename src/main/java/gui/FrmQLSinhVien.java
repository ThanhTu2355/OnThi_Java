package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.QLSinhVien;
import model.SinhVien;

/**
 *
 * @author ADMIN
 */
public class FrmQLSinhVien extends JFrame {

    private JTable tblSinhVien;
    private JButton btThem, btXoa;
    private JButton btDocFile, btGhiFile;

    private DefaultTableModel model;
    private JTextField txtMaSo, txtHoTen, txtDTB;

    private JRadioButton rdNam, rdNu;
    private JCheckBox chkSapXep;

    private static final String FILE_NAME = "data.txt";

    private QLSinhVien qlsv = new QLSinhVien();

    public FrmQLSinhVien(String title) {
        super(title);
        createGUI();
        processEVent();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createGUI() {

        //tạo JTable
        String[] columnNames = {"Mã số", "Họ tên", "Phái", "ĐTB", "Xếp loại"};
        model = new DefaultTableModel(null, columnNames);
        tblSinhVien = new JTable(model);
        //tạo thành phần quản lý cuộn cho Jtable
        JScrollPane scrollTable = new JScrollPane(tblSinhVien);

        //tạo các điều khiển nhập liệu  và các nút lệnh
        JPanel p = new JPanel();
        p.add(new JLabel("Mã sinh viên"));
        p.add(txtMaSo = new JTextField(5));
        p.add(new JLabel("Họ tên"));
        p.add(txtHoTen = new JTextField(10));

        p.add(rdNam = new JRadioButton("Nam"));
        p.add(rdNu = new JRadioButton("Nữ"));

        ButtonGroup btgPhai = new ButtonGroup();
        btgPhai.add(rdNam);
        btgPhai.add(rdNu);

        p.add(new JLabel("Điểm TB"));
        p.add(txtDTB = new JTextField(10));

        p.add(btDocFile = new JButton("Đọc File"));
        p.add(btThem = new JButton("Thêm"));
        p.add(btXoa = new JButton("Xoá"));
        p.add(btGhiFile = new JButton("Ghi File"));

        JPanel p2 = new JPanel();
        p2.add(chkSapXep = new JCheckBox("Sắp xếp theo học lực"));

        //add các thành phần vào cửa sổ
        add(p, BorderLayout.NORTH);
        add(scrollTable, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);
    }

    private void processEVent() {

        //tiep nhan su kien cho nut docFile
        btDocFile.addActionListener((ActionEvent e) -> {
            {
                qlsv.DocDanhSachSinhVien(FILE_NAME);
                //hien thi danh sach sinh vien len JTable
                loadDataToJTable();
            }
        });

        //tiep nhan su kien cho nut them
        btThem.addActionListener((ActionEvent e) -> {
            String loi = "";
            try {
                if (txtMaSo.getText().trim().length() == 0) {
                    loi += "Chua nhap ma so\n";
                }
                if (txtHoTen.getText().trim().length() == 0) {
                    loi += "Chua nhap ho ten\n";
                }
                if (txtDTB.getText().trim().length() == 0) {
                    loi += "Chua nhap diem trung binh\n";
                }

                if (!loi.isEmpty()) {
                    // Neu co loi nhap lieu, hien thi thong bao
                    JOptionPane.showMessageDialog(this, loi, "Loi nhap lieu", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lay thong tin tu JTextField
                String maSo = txtMaSo.getText();
                String hoTen = txtHoTen.getText();
                boolean gioiTinh = rdNam.isSelected();
                double diemTB = Double.parseDouble(txtDTB.getText());

                // Tao doi tuong SinhVien moi
                SinhVien sv = new SinhVien(maSo, hoTen, gioiTinh, diemTB);

                // Them sinh vien vao danh sach
                if (qlsv.themSV(sv)) {
                    // Hien thi danh sach len JTable
                    loadDataToJTable();
                    JOptionPane.showMessageDialog(this, "Da them sinh vien thanh cong", "Thong bao", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Them sinh vien that bai\nDo trung ma sinh vien", "Thong bao", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                // Bat loi nhap sai dinh dang cho diem trung binh
                JOptionPane.showMessageDialog(this, "Diem trung binh khong hop le", "Loi nhap lieu", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Bat cac loi khong mong doi khac
                JOptionPane.showMessageDialog(this, "Da xay ra loi: " + ex.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
            }
        });

        //them su kien cho nut xoa
        btXoa.addActionListener((ActionEvent e) -> {
            int selectedIndex = tblSinhVien.getSelectedRow();

            if (selectedIndex < 0) {
                JOptionPane.showMessageDialog(this, "Ban chua chon sinh vien can xoa", "Thong bao", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lay maSo cua sinh vien duoc chon tu JTable
            String maSo = tblSinhVien.getValueAt(selectedIndex, 0).toString();

            // Xoa sinh vien trong danh sach
            if (qlsv.xoaSV(maSo)) {
                // Tai lai du lieu len JTable
                loadDataToJTable();
                JOptionPane.showMessageDialog(this, "Da xoa sinh vien thanh cong", "Thong bao", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Xoa sinh vien that bai", "Thong bao", JOptionPane.ERROR_MESSAGE);
            }
        });

        //them su kien cho nut ghi
        btGhiFile.addActionListener((ActionEvent e) -> {
            if(qlsv.GhiDanhSachSinhVien(FILE_NAME)){
                JOptionPane.showMessageDialog(this, "Da ghi du lieu thanh cong","Thong bao",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Da ghi du lieu that bai","Thong bao",JOptionPane.ERROR_MESSAGE);
            }
        });
        //them su kien cho phan sap xep
        chkSapXep.addItemListener((e) -> {
            if(chkSapXep.isSelected()){
                qlsv.sapXepTheoHocLuc();
                loadDataToJTable();
            }else{
                qlsv.sapXepTheoMaSV();
                loadDataToJTable();
            }
        });
    }

    private void loadDataToJTable() {
        model.setRowCount(0);
        for (SinhVien sv : qlsv.getDsSinhVien()) {
            model.addRow(new Object[]{sv.getMaso(), sv.getHoten(), sv.isGioitinh() == true ? "Nam" : "Nu", sv.getDiemTB(), sv.getHocLuc()});
        }
    }
}
