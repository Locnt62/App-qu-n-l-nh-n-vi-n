package org.o7.planning.qlnhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuChinh extends AppCompatActivity {
    Button btnPhongBan, btnNhanVien, btnDangXuat, btnMatKhau;
    protected  void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menuchinh);

        btnPhongBan=(Button)findViewById(R.id.btnPB);
        btnNhanVien=(Button)findViewById(R.id.btnNV);
        btnDangXuat=(Button)findViewById(R.id.btnDX);
        btnMatKhau=(Button)findViewById(R.id.btnMK);

        btnPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ipb= new Intent(MenuChinh.this,PhongBanActivity.class);
                startActivity(ipb);
            }
        });

        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inv= new Intent(MenuChinh.this,NhanVienActivity.class);
                startActivity(inv);
            }
        });

        btnMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imk= new Intent(MenuChinh.this, ThietLapMatKhau.class);
                startActivity(imk);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idx= new Intent(MenuChinh.this,MainActivity.class);
                startActivity(idx);
            }
        });
    }
}
