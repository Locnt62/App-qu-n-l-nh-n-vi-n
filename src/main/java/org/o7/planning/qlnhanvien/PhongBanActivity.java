package org.o7.planning.qlnhanvien;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import Adapter.Custom_Listview_PhongBan;
import DOA.PhongBanDAO;
import DTO.PhongBanDTO;

public class PhongBanActivity extends AppCompatActivity {
    ListView listViewPhongBan;
    LinearLayout layout_phongban;
    EditText editTenPhongBan;
    Button btnThem;
    PhongBanDAO dbPhongBan;
    Custom_Listview_PhongBan adapter;
    List<PhongBanDTO> listPhongBan;
    int vitri;
    int idphongban;

    @Override
    protected  void onCreate( Bundle savedInstanceState){
        super.onCreate( savedInstanceState);
        setContentView(R.layout.layout_phongban);
        listViewPhongBan=(ListView)findViewById(R.id.listPhongBan);
        layout_phongban=(LinearLayout)findViewById(R.id.layout_phongban);
        registerForContextMenu(listViewPhongBan);
        registerForContextMenu(layout_phongban);
        dbPhongBan= new PhongBanDAO(this);


        LoadListViewPhongBan();


     listViewPhongBan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
             vitri=position;
             return false;
         }
     });
     //listViewPhongBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         //@Override
         //public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //listPhongBan=new ArrayList<PhongBanDTO>();
             //Intent iNhanVienPhongBan= new Intent(PhongBanActivity.this,NhanVienPhongBanActivity.class);
             //iNhanVienPhongBan.putExtra("maphongban", String.valueOf(listPhongBan.get(position).getMaphongban()));
             //startActivity(iNhanVienPhongBan);
         //}
     //});



    }

    public void LoadListViewPhongBan(){
        listPhongBan=new ArrayList<PhongBanDTO>();
        listPhongBan=dbPhongBan.layAllPhongBan();
        adapter = new Custom_Listview_PhongBan(this, R.layout.custom_listview_phongban,listPhongBan);
        listViewPhongBan.setAdapter(adapter);

    }

    public void onCreateContextMenu( ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.setHeaderTitle(" Chức năng");
        getMenuInflater().inflate(R.menu.menu_chucnang,menu);
        if(v.getId()==R.id.listPhongBan){
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(false);
        }

        super.onCreateContextMenu(menu,v,menuInfo);

    }

    public boolean onContextItemSelected(MenuItem item){
        int id=item.getItemId();
        if( id==R.id.menuThem){
            final Dialog dal= new Dialog(this);
            dal.setTitle("Thêm phòng ban");
            dal.setContentView(R.layout.layout_themphongban);
            editTenPhongBan=(EditText)dal.findViewById(R.id.viewTenPhongBan);
            btnThem=(Button)dal.findViewById(R.id.btnThem);
            dal.show();
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View argo) {
                    PhongBanDTO phongban=new PhongBanDTO();
                    phongban.setTenphongban(editTenPhongBan.getText().toString());
                    dbPhongBan.ThemPhongBan(phongban);
                    Toast.makeText(getApplicationContext(),"Thêm phòng ban thành công", Toast.LENGTH_SHORT).show();
                    LoadListViewPhongBan();
                    dal.dismiss();

                }
            });

        }
        if(id== R.id.menuSua){
            final Dialog dalsua= new Dialog(this);
            dalsua.setTitle(" Sửa phòng ban");
            dalsua.setContentView(R.layout.layout_suaphongban);
            dalsua.show();
            final EditText editMaPhongBanSua =(EditText)dalsua.findViewById(R.id.editMaPhongBanSua);
            final EditText editTenPhongBanSua=(EditText)dalsua.findViewById(R.id.editTenPhongBanSua);
            Button btnSua=(Button)dalsua.findViewById(R.id.btnSua);

            editMaPhongBanSua.setEnabled(false);
            final String maphongban=String.valueOf(listPhongBan.get(vitri).getMaphongban());
            editMaPhongBanSua.setText(maphongban);
            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final PhongBanDTO phongban = new PhongBanDTO();
                    phongban.setMaphongban(Integer.parseInt(maphongban));
                    phongban.setTenphongban(editTenPhongBanSua.getText().toString());

                    AlertDialog.Builder alb = new AlertDialog.Builder(PhongBanActivity.this);
                    alb.setTitle("Thông báo");
                    alb.setMessage(" Bạn có muốn sửa chữa không ?");
                    alb.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbPhongBan.SuaPhongBanTheoMa(phongban) !=-1){
                                Toast.makeText(getApplicationContext(),"Sửa phòng ban thành công",Toast.LENGTH_SHORT).show();
                                LoadListViewPhongBan();
                                dalsua.dismiss();
                            }else{
                                Toast.makeText(getApplicationContext(),"Sửa không phòng ban",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    alb.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alb.show();

                }
            });

        }
        if(id==R.id.menuXoa){
            int maphongban=listPhongBan.get(vitri).getMaphongban();
            if(dbPhongBan.XoaPhongBanTheoMa(maphongban) != -1){
                Toast.makeText(getApplicationContext(),"Xóa phòng ban",Toast.LENGTH_SHORT).show();
                LoadListViewPhongBan();
            }else {
                Toast.makeText(getApplicationContext(),"Xóa thất bại",Toast.LENGTH_SHORT).show();
            }

        }
        return  super.onContextItemSelected(item);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hethong, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menuNhanVien:
                Intent iNhanVienActivity = new Intent(PhongBanActivity.this, NhanVienActivity.class);
                startActivity(iNhanVienActivity);
                Toast.makeText(getApplicationContext(),"Nhân Viên",Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.menuPhongBan:
                Intent iPhongBanActivity = new Intent(PhongBanActivity.this, PhongBanActivity.class);
                startActivity(iPhongBanActivity);
                Toast.makeText(getApplicationContext(),"Phòng Ban",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuHeThong:
                Intent ihethong= new Intent(PhongBanActivity.this,ThietLapMatKhau.class);
                startActivity(ihethong);
                Toast.makeText(getApplicationContext(),"Hệ Thống",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuLienHe:
                Intent ithoat= new Intent(PhongBanActivity.this,MainActivity.class);
                startActivity(ithoat);
                Toast.makeText(getApplicationContext(),"Đăng xuất",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }


    }

}
