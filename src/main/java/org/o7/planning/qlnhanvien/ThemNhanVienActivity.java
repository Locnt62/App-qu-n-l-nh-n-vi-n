package org.o7.planning.qlnhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.Custom_Spinner_ThemNhanVien;
import DOA.NhanVienDAO;
import DOA.PhongBanDAO;
import DTO.NhanVienDTO;
import DTO.PhongBanDTO;

public class ThemNhanVienActivity extends AppCompatActivity {
    Spinner spinnerPhongBan;
    EditText txtTenNhanVien, txtDiaChi, txtSoDienThoai, txtNgaySinh, txtLuong, txtEmail;
    Button btnThem, btnThoat;
    List<PhongBanDTO> list;
    PhongBanDAO dbPhongBan;
    NhanVienDAO dbNhanVien;
    int vitri;

    protected  void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themnhanvien);

        btnThem=(Button)findViewById(R.id.btnThemNhanVien);
        btnThoat=(Button)findViewById(R.id.btnThoatNhanVien);
        txtTenNhanVien=(EditText)findViewById(R.id.editThemTenNhanVien);
        txtSoDienThoai=(EditText)findViewById(R.id.editThemSoDienThoai);
        txtNgaySinh=(EditText)findViewById(R.id.editThemNgaySinh);
        txtLuong=(EditText)findViewById(R.id.editThemLuong);
        txtEmail=(EditText)findViewById(R.id.editThemEmail);
        txtDiaChi=(EditText)findViewById(R.id.editThemDiaChi);

        dbPhongBan=new PhongBanDAO(this);
        dbNhanVien= new NhanVienDAO(this);

        spinnerPhongBan=(Spinner)findViewById(R.id.spinnerPhongBan);
        list= new ArrayList<PhongBanDTO>();
        list=dbPhongBan.layAllPhongBan();

        Custom_Spinner_ThemNhanVien adapter = new Custom_Spinner_ThemNhanVien(this,R.layout.custom_layout_spinner,list);
        spinnerPhongBan.setAdapter(adapter);

        spinnerPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vitri=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rdGroup=(RadioGroup)findViewById(R.id.rdGioiTinh);
                int rdID = rdGroup.getCheckedRadioButtonId();
                RadioButton rdChecked=(RadioButton)findViewById(rdID);
                String gioitinh=rdChecked.getText().toString();
                int mapb = list.get(vitri).getMaphongban();

                try{
                    NhanVienDTO nhanvien=new NhanVienDTO();
                    nhanvien.setDiachi(txtDiaChi.getText().toString());
                    nhanvien.setEmail(txtEmail.getText().toString());
                    nhanvien.setGioitinh(gioitinh);
                    nhanvien.setLuong(Integer.parseInt(txtLuong.getText().toString()));
                    nhanvien.setMapb(mapb);
                    nhanvien.setNgaysinh(txtNgaySinh.getText().toString());
                    nhanvien.setSdt(txtSoDienThoai.getText().toString());
                    nhanvien.setTennv(txtTenNhanVien.getText().toString());

                    dbNhanVien.ThemNhanVien(nhanvien);
                    Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext()," Thêm lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hethong, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menuNhanVien:
                Intent iNhanVienActivity = new Intent(ThemNhanVienActivity.this, NhanVienActivity.class);
                startActivity(iNhanVienActivity);
                Toast.makeText(getApplicationContext(),"Nhân Viên",Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.menuPhongBan:
                Intent iPhongBanActivity = new Intent(ThemNhanVienActivity.this, PhongBanActivity.class);
                startActivity(iPhongBanActivity);
                Toast.makeText(getApplicationContext(),"Phòng Ban",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuHeThong:
                Intent ihethong= new Intent(ThemNhanVienActivity.this,ThietLapMatKhau.class);
                startActivity(ihethong);
                Toast.makeText(getApplicationContext(),"Hệ Thống",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuLienHe:
                Intent ithoat= new Intent(ThemNhanVienActivity.this,MainActivity.class);
                startActivity(ithoat);
                Toast.makeText(getApplicationContext(),"Đăng xuất",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }


    }
}
