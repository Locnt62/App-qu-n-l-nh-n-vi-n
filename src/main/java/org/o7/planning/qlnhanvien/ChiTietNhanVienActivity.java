package org.o7.planning.qlnhanvien;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import DOA.NhanVienDAO;
import DTO.NhanVienDTO;

public class ChiTietNhanVienActivity extends AppCompatActivity {
    TextView vTenNV, vGioiTinh, vSoDienThoai, vDiaChi, vNgaySinh, vPhongBan, vEmail, vLuong;
    NhanVienDAO dbNhanVien;
    NhanVienDTO nhanvien;




    protected  void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietnhanvien);
        nhanvien= (NhanVienDTO) getIntent().getSerializableExtra("nv");
        vTenNV=(TextView)findViewById(R.id.vCTTenNhanVien);
        vGioiTinh=(TextView)findViewById(R.id.vCTGioiTinh);
        vSoDienThoai=(TextView)findViewById(R.id.vCTSoDienThoai);
        vDiaChi=(TextView)findViewById(R.id.vHTDiaChi);
        vPhongBan=(TextView)findViewById(R.id.vHTPhongBan);
        vEmail=(TextView)findViewById(R.id.vHTEmail);

        vNgaySinh=(TextView)findViewById(R.id.vHTNgaySinh);
        vLuong=(TextView)findViewById(R.id.vHTLuong);


        vTenNV.setText(nhanvien.getTennv());
        vGioiTinh.setText(nhanvien.getGioitinh());
        vSoDienThoai.setText(nhanvien.getSdt());
        vDiaChi.setText(nhanvien.getDiachi());
        vEmail.setText(nhanvien.getEmail());
        vNgaySinh.setText(nhanvien.getNgaysinh());
        vPhongBan.setText(String.valueOf(nhanvien.getMapb()));
        vLuong.setText(String.valueOf(nhanvien.getLuong()) + " VND");







    }
}
