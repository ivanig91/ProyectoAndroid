package com.len1.descarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;


public class lugarDetalle extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
   public Lugar lugSelec;
    Button nuevaEntrada;
    ImageButton direc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_detalle);
        Intent intent = getIntent();

        TextView leyenda = findViewById(R.id.tvLeyenda);

        leyenda.setMovementMethod(new ScrollingMovementMethod());
        Switch meGusta = findViewById(R.id.meGusta);
        meGusta.setOnCheckedChangeListener(this);
        meGusta.setOnClickListener(this);
        Lugar lugar = (Lugar) intent.getSerializableExtra("objeto");
        leyenda.setText(lugar.getDescripcion());
        direc = findViewById(R.id.btUbi);
        direc.setImageResource(R.drawable.place);
        direc.setOnClickListener(this);
        nuevaEntrada = findViewById(R.id.btEntra);
        nuevaEntrada.setOnClickListener(this);

        String id = lugar.getId();


        for(Lugar lug : MainActivity.lugares){
            if(id.equals(lug.id)){
                lugSelec=lug;
                //Quitar esta guarreria, depronto con base de datos

            }
        }

    }



    @Override
    public void onClick(View v) {
        if(v.getId()== direc.getId()){
            Intent intent = new Intent(v.getContext(),Mapa.class);

            Bundle bundle  = new Bundle();
            bundle.putString("longitud",lugSelec.getLongitud().toString());
            bundle.putString("latitud",lugSelec.getLatitud().toString());
            bundle.putString("id",lugSelec.getNombre());
            intent.putExtra("paquete",bundle);
            startActivityForResult(intent,0);

        }else{

            if(v.getId()==nuevaEntrada.getId()){
                Intent intent = new Intent(this,entradaDetalle.class);

                Bundle bundle = new Bundle();

                bundle.putString("nombre",lugSelec.getNombre());
                bundle.putString("tipo","0");
                bundle.putString("id",lugSelec.getId());
                bundle.putBoolean("editar",false);
                intent.putExtra("paquete",bundle);
                startActivityForResult(intent,0);

            }

        }


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked){

        }

    }
}
