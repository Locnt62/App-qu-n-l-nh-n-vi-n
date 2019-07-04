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

public class Custom_Spinner_ThemNhanVien extends ArrayAdapter<PhongBanDTO> {
    Context context;
    int resource;
    List<PhongBanDTO> objects;

    public Custom_Spinner_ThemNhanVien(Context context, int resource, List<PhongBanDTO> objects){
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return getView(position,convertView,parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewrow= inflater.inflate(R.layout.custom_layout_spinner,parent,false);

        TextView vTenPhongBan=(TextView)viewrow.findViewById(R.id.vSpinnerPhongBan);

        PhongBanDTO phongban = objects.get(position);
        vTenPhongBan.setText(phongban.getTenphongban().toString());
         return  viewrow;


    }
}
