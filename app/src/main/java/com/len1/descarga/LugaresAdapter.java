package com.len1.descarga;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LugaresAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    MainActivity main;





    public LugaresAdapter(MainActivity main) {
       this.main=main;
    }
    static class ViewHolder{

        TextView id;
        TextView nombre;
        TextView calle;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bb,null);
            holder = new ViewHolder();

            holder.id = (TextView) convertView.findViewById(R.id.tvID);
            holder.nombre = (TextView) convertView.findViewById(R.id.tvNombre);
            holder.calle = (TextView) convertView.findViewById(R.id.tvCalle);

            convertView.setTag(holder);

        }else{
            holder = ( ViewHolder) convertView.getTag();
        }



        Lugar lugar = main.lugares.get(position);

        holder.id.setText(lugar.getId());
        holder.nombre.setText(lugar.getNombre());
        holder.calle.setText(lugar.getCalle());




        return convertView;
    }


    public void clear (){
        main.lugares.clear();
    }

    @Override
    public int getCount() {
        return main.lugares.size();
    }

    @Override
    public Lugar getItem(int position) {
        return main.lugares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

   }
