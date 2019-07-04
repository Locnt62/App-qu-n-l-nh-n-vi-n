package org.o7.planning.qlnhanvien;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Button btnDangKy, btnThoatDK, btnDangNhap, btnThoatDN;
    EditText editTaiKhoanDK, editMatKhauDK, editNhapLaiMatKhauDK, editTaiKhoanDN, editMatKhauDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
        editTaiKhoanDN=(EditText)findViewById(R.id.editTenDangNhap);
        editMatKhauDN=(EditText)findViewById(R.id.editMatKhau);

    }

    public void DangNhap( View view){
        btnDangNhap=(Button)findViewById(R.id.btnDangNhap);
        btnThoatDN=(Button)findViewById(R.id.btnThoatDN);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argo) {
                String matkhauconfig, taikhoanconfig, taikhoanDN, matkhauDN;
                taikhoanconfig=sharedPreferences.getString("TaiKhoan", "");
                matkhauconfig=sharedPreferences.getString("MatKhau", "");
                taikhoanDN=editTaiKhoanDN.getText().toString();
                matkhauDN=editMatKhauDN.getText().toString();




        if(taikhoanDN.equals(taikhoanconfig) && matkhauDN.equals(matkhauconfig)){
            Intent intent=new Intent(MainActivity.this,MenuChinh.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"Đăng nhập không thành công", Toast.LENGTH_LONG).show();
            btnThoatDN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id == R.id.menuDangKy){
            String taikhoan, matkhau;
            taikhoan=sharedPreferences.getString("TaiKhoan", "");
            matkhau=sharedPreferences.getString("MatKhau", "");
            if(taikhoan.trim().length()==0 || matkhau.trim().length()==0) {
                final Dialog dal = new Dialog(this);
                dal.setContentView(R.layout.layout_dangky);
                dal.setTitle(R.string.titleDangKy);

                editMatKhauDK = (EditText) dal.findViewById(R.id.editMatKhauDK);
                editNhapLaiMatKhauDK = (EditText) dal.findViewById(R.id.editNhapLaiMatKhauDK);
                editTaiKhoanDK = (EditText) dal.findViewById(R.id.editTaiKhoanDK);

                btnDangKy = (Button) dal.findViewById(R.id.btnDangKy);
                btnThoatDK = (Button) dal.findViewById(R.id.btnThoat);
                btnDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String matkhaudk, nhaplaimatkhaudk;
                        matkhaudk = editMatKhauDK.getText().toString();
                        nhaplaimatkhaudk = editNhapLaiMatKhauDK.getText().toString();

                        if (!matkhaudk.equals(nhaplaimatkhaudk)) {
                            Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại không đúng", Toast.LENGTH_LONG).show();
                        } else {
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putString("MatKhau", editMatKhauDK.getText().toString());
                            edit.putString("TaiKhoan", editTaiKhoanDK.getText().toString());
                            edit.commit();
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                btnThoatDK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();

                    }
                });
                dal.show();
                dal.show();
            } else {
                Toast.makeText(getApplicationContext(), "Bạn không thể đăng ký vì đã có tài khoản", Toast.LENGTH_LONG).show();
            }

        }
        return super.onOptionsItemSelected(item);

    }
}
