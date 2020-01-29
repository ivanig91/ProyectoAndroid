package com.len1.descarga;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DiarioAdapter extends BaseAdapter {
    private  Context context;
    private ArrayList<DiarioClase> diario;
    private LayoutInflater inflater;

    public DiarioAdapter(Context context,ArrayList<DiarioClase> diario) {
        this.context = context;
        this.diario = diario;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder{
        ImageView foto;
        TextView nombre;
        TextView id;
    }

    @Override
    public int getCount() {

        return diario.size();
    }

    @Override
    public Object getItem(int position) {
        return diario.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView==null){
            convertView = inflater.inflate(R.layout.entrada_diario,null);

            holder = new ViewHolder();
            holder.foto =  convertView.findViewById(R.id.ivDiarlista);
            holder.id =  convertView.findViewById(R.id.entId);
            holder.nombre =   convertView.findViewById(R.id.entNombre);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        DiarioClase entrada = diario.get(position);

        if(entrada.getTipo()==0){
            holder.foto.setImageResource(R.drawable.madrid);

        }else{
            holder.foto.setImageResource(R.drawable.merca);
        }
        holder.id.setText(String.valueOf(entrada.getDiarioId()));
        holder.nombre.setText(entrada.getNombre());

        return convertView;

    }

}
