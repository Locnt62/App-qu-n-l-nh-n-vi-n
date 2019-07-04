package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.o7.planning.qlnhanvien.R;

import java.util.List;

import DTO.PhongBanDTO;

public class Custom_Listview_PhongBan extends ArrayAdapter<PhongBanDTO> {
    Context context;
    int resource;
    List<PhongBanDTO> objects;

    public Custom_Listview_PhongBan(Context context, int resource, List<PhongBanDTO> objects){
        super(context,resource,objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewrow= inflater.inflate(R.layout.custom_listview_phongban,parent,false);

        TextView vTenPhongBan=(TextView)viewrow.findViewById(R.id.viewListTenPhongBan);
        TextView vMaPhongBan=(TextView)viewrow.findViewById(R.id.viewListMaPhongBan);


        PhongBanDTO phongban= objects.get(position);
        vTenPhongBan.setText(phongban.getTenphongban());
        vMaPhongBan.setText(String.valueOf(" Mã " + phongban.getMaphongban()));


        return  viewrow;


    }
}
