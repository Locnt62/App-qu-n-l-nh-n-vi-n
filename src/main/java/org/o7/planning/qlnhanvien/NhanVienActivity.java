package org.o7.planning.qlnhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.Custom_Listview_NhanVien;
import DOA.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVienActivity extends AppCompatActivity {
    NhanVienDAO dbNhanVien;
    List<NhanVienDTO> listNhanVien=new ArrayList<>();
    Custom_Listview_NhanVien adapter;
    ListView listviewNhanVien;
    int vitri;
    int idnhanvien;

    public static int RESULT_CAPNHATNHANVIEN=100;

    protected  void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nhanvien);

        final LinearLayout layout_nhanvien=(LinearLayout)findViewById(R.id.layout_nhanvien);


        dbNhanVien= new NhanVienDAO(this);
        listNhanVien=new ArrayList<NhanVienDTO>();
        LoadListviewNhanVien();
        registerForContextMenu(layout_nhanvien);
        registerForContextMenu(listviewNhanVien);

        listviewNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vitri=position;
                return false;
            }
        });

       listviewNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(NhanVienActivity.this,ChiTietNhanVienActivity.class);
               intent.putExtra("nv",listNhanVien.get(position));
               startActivity(intent);
           }
       });


    }

    private  void  LoadListviewNhanVien(){
        listNhanVien.clear();
        listNhanVien.addAll(dbNhanVien.LoadAllNhanVien());
        adapter= new Custom_Listview_NhanVien(this, R.layout.custom_layout_nhanvien, listNhanVien);
        listviewNhanVien=(ListView)findViewById(R.id.listNhanVien);
        listviewNhanVien.setAdapter(adapter);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_chucnang,menu);

        if(v.getId()==R.id.listNhanVien){
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(false);
        }
    }
    private  void XoaNhanVien(){
       idnhanvien=listNhanVien.get(vitri).getManv();
        dbNhanVien.XoaNhanVien(idnhanvien);

    }


    @Override
    public  boolean onContextItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.menuThem) {
            Intent iThemNhanVien = new Intent(NhanVienActivity.this, ThemNhanVienActivity.class);
            startActivity(iThemNhanVien);
        }
        if(id==R.id.menuXoa){
            XoaNhanVien();
            Toast.makeText(getApplicationContext(),"Xóa thành công", Toast.LENGTH_SHORT).show();
            LoadListviewNhanVien();
        }

        if(id ==R.id.menuSua){
            Intent iCapNhatNhanVien= new Intent(NhanVienActivity.this, CapNhatNhanVien.class);
            idnhanvien= listNhanVien.get(vitri).getManv();
            iCapNhatNhanVien.putExtra("manv", idnhanvien);
            startActivityForResult(iCapNhatNhanVien,RESULT_CAPNHATNHANVIEN);
        }
        return  super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RESULT_CAPNHATNHANVIEN && resultCode==RESULT_OK){
            LoadListviewNhanVien();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hethong, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menuNhanVien:
                Intent iNhanVienActivity = new Intent(NhanVienActivity.this, NhanVienActivity.class);
                startActivity(iNhanVienActivity);
                Toast.makeText(getApplicationContext(),"Nhân Viên",Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.menuPhongBan:
                Intent iPhongBanActivity = new Intent(NhanVienActivity.this, PhongBanActivity.class);
                startActivity(iPhongBanActivity);
                Toast.makeText(getApplicationContext(),"Phòng Ban",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuHeThong:
                Intent ihethong= new Intent(NhanVienActivity.this,ThietLapMatKhau.class);
                startActivity(ihethong);
                Toast.makeText(getApplicationContext(),"Hệ Thống",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuLienHe:
                Intent ithoat= new Intent(NhanVienActivity.this,MainActivity.class);
                startActivity(ithoat);
                Toast.makeText(getApplicationContext(),"Đăng xuất",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        listNhanVien.clear();
        listNhanVien.addAll(dbNhanVien.LoadAllNhanVien());
        adapter.notifyDataSetChanged();
    }
}
