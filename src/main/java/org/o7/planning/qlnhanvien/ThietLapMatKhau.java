package org.o7.planning.qlnhanvien;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ThietLapMatKhau extends AppCompatActivity {
    EditText editTenDangNhap, editMatKhau,editMatKhauMoi, editNhapLaiMatKhau, editTenDangNhapMoi;
    ToggleButton toggleTrangThai;
    RelativeLayout layoutTHayDoi;
    SharedPreferences sharedPreferences;
    Button btnLuu;
    protected  void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thaydoimatkhau);

        layoutTHayDoi = (RelativeLayout) findViewById(R.id.khungthaydoi);
        editTenDangNhap = (EditText) findViewById(R.id.edit1);
        editMatKhau = (EditText) findViewById(R.id.edit2);
        editTenDangNhapMoi = (EditText) findViewById(R.id.edit3);
        editMatKhauMoi = (EditText) findViewById(R.id.edit4);
        editNhapLaiMatKhau = (EditText) findViewById(R.id.edit5);
        toggleTrangThai = (ToggleButton) findViewById(R.id.tooglebutton1);
        sharedPreferences = getSharedPreferences("config", 0);
        btnLuu = (Button) findViewById(R.id.button1);

        toggleTrangThai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layoutTHayDoi.setVisibility(RelativeLayout.VISIBLE);
                } else {
                    layoutTHayDoi.setVisibility(RelativeLayout.GONE);
                }
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thaydoimatkhau();
            }
        });
    }

        private void thaydoimatkhau() {
            String tentaikhoan = sharedPreferences.getString("TaiKhoan", "");
            String matkhau = sharedPreferences.getString("MatKhau", "");
            String tentaikhoannhap = editTenDangNhap.getText().toString();
            String matkhaunhap = editMatKhau.getText().toString();

            if (tentaikhoan.equals(tentaikhoannhap) && matkhau.equals(matkhaunhap)) {
                String tentaikhoanmoi = editTenDangNhapMoi.getText().toString();
                String matkhaumoi = editMatKhauMoi.getText().toString();
                String nhaplaimatkhaumoi = editNhapLaiMatKhau.getText().toString();
                if (nhaplaimatkhaumoi.equals(matkhaumoi)) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("TaiKhoan", tentaikhoanmoi);
                    edit.putString("MatKhau", matkhaumoi);
                    edit.commit();
                    Toast.makeText(getApplicationContext(), " Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), " Mật khẩu nhập lại không giống", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), " Tài khoản và mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        }




    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hethong, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menuNhanVien:
                Intent iNhanVienActivity = new Intent(ThietLapMatKhau.this, NhanVienActivity.class);
                startActivity(iNhanVienActivity);
                Toast.makeText(getApplicationContext(),"Nhân Viên",Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.menuPhongBan:
                Intent iPhongBanActivity = new Intent(ThietLapMatKhau.this, PhongBanActivity.class);
                startActivity(iPhongBanActivity);
                Toast.makeText(getApplicationContext(),"Phòng Ban",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuHeThong:
                Intent ihethong= new Intent(ThietLapMatKhau.this,ThietLapMatKhau.class);
                startActivity(ihethong);
                Toast.makeText(getApplicationContext(),"Hệ Thống",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuLienHe:
                Intent ithoat= new Intent(ThietLapMatKhau.this,MainActivity.class);
                startActivity(ithoat);
                Toast.makeText(getApplicationContext(),"Đăng xuất",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }


    }
}
