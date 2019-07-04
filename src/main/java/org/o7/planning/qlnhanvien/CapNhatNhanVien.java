package org.o7.planning.qlnhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import Adapter.Custom_Spinner_ThemNhanVien;
import DOA.NhanVienDAO;
import DOA.PhongBanDAO;
import DTO.NhanVienDTO;
import DTO.PhongBanDTO;

public class CapNhatNhanVien extends AppCompatActivity {
    PhongBanDAO dbPhongBan;
    NhanVienDAO dbNhanVien;
    Spinner spinner;
    EditText txtTenNhanVien, txtDiaChi, txtSoDienThoai, txtNgaySinh, txtLuong, txtEmail;
    Button btnThem, btnThoat;
    List<PhongBanDTO> listPhongBan;
    int vitri;
    int iMaNV;


    protected  void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_capnhatnhanvien);

        btnThem=(Button)findViewById(R.id.btnCapNhatNhanVien);
        btnThoat=(Button)findViewById(R.id.btnThoatSuaNhanVien);
        txtTenNhanVien=(EditText)findViewById(R.id.editSuaTenNhanVien);
        txtSoDienThoai=(EditText)findViewById(R.id.editSuaSoDienThoai);
        txtNgaySinh=(EditText)findViewById(R.id.editSuaNgaySinh);
        txtLuong=(EditText)findViewById(R.id.editSuaLuong);
        txtEmail=(EditText)findViewById(R.id.editSuaEmail);
        txtDiaChi=(EditText)findViewById(R.id.editSuaDiaChi);

        Intent intent= getIntent();
        iMaNV=intent.getExtras().getInt("manv");
        this.setResult(RESULT_OK);

        spinner = (Spinner) findViewById(R.id.spinnerSuaPhongBan);
        dbPhongBan = new PhongBanDAO(this);
        dbNhanVien = new NhanVienDAO(this);
        listPhongBan = dbPhongBan.layAllPhongBan();
        Custom_Spinner_ThemNhanVien adapter = new Custom_Spinner_ThemNhanVien(this, R.layout.custom_layout_spinner, listPhongBan);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                vitri=arg2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhatNhanVien1();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void CapNhatNhanVien1(){
        NhanVienDTO nhanvien= new NhanVienDTO();


        RadioGroup rdGroup=(RadioGroup)findViewById(R.id.rdSuaGioiTinh);
        int rdID = rdGroup.getCheckedRadioButtonId();
        RadioButton rdChecked=(RadioButton)findViewById(rdID);
        String gioitinh=rdChecked.getText().toString();
        int mapb = listPhongBan.get(vitri).getMaphongban();


            nhanvien.setManv(iMaNV);
            nhanvien.setDiachi(txtDiaChi.getText().toString());
            nhanvien.setEmail(txtEmail.getText().toString());
            nhanvien.setGioitinh(gioitinh);
            nhanvien.setLuong(Integer.parseInt(txtLuong.getText().toString()));
            nhanvien.setNgaysinh(txtNgaySinh.getText().toString());
            nhanvien.setSdt(txtSoDienThoai.getText().toString());
            nhanvien.setTennv(txtTenNhanVien.getText().toString());

            if(dbNhanVien.CapNhatNhanVien(nhanvien) !=-1){
                Toast.makeText(getApplicationContext()," Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }


    }
}
