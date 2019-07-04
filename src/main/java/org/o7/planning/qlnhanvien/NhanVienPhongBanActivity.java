package org.o7.planning.qlnhanvien;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.Custom_Listview_NhanVien;
import DOA.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVienPhongBanActivity extends AppCompatActivity {
    ListView listViewNhanVienPhongBan;
    NhanVienDAO dbNhanVien;
    List<NhanVienDTO> listNhanVien;


    protected  void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nhanvien_phongban);

        listViewNhanVienPhongBan =(ListView)findViewById(R.id.listNhanVienPhongBan);
        dbNhanVien= new NhanVienDAO(this);
        listNhanVien=new ArrayList<NhanVienDTO>();

        Intent intent= new Intent();
        int mapb= Integer.parseInt(intent.getExtras().getString("maphongban"));
        listNhanVien=dbNhanVien.LayNhanVienTheoPhongBan(mapb);

        Custom_Listview_NhanVien adapter= new Custom_Listview_NhanVien(this, R.layout.custom_layout_nhanvien,listNhanVien);
        listViewNhanVienPhongBan.setAdapter(adapter);

    }







    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hethong, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menuNhanVien:
                Intent iNhanVienActivity = new Intent(NhanVienPhongBanActivity.this, NhanVienActivity.class);
                startActivity(iNhanVienActivity);
                Toast.makeText(getApplicationContext(),"Nhân Viên",Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.menuPhongBan:
                Intent iPhongBanActivity = new Intent(NhanVienPhongBanActivity.this, PhongBanActivity.class);
                startActivity(iPhongBanActivity);
                Toast.makeText(getApplicationContext(),"Phòng Ban",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuHeThong:
                Intent ihethong= new Intent(NhanVienPhongBanActivity.this,ThietLapMatKhau.class);
                startActivity(ihethong);
                Toast.makeText(getApplicationContext(),"Hệ Thống",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuLienHe:
                Intent ithoat= new Intent(NhanVienPhongBanActivity.this,MainActivity.class);
                startActivity(ithoat);
                Toast.makeText(getApplicationContext(),"Đăng xuất",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }


    }
}

