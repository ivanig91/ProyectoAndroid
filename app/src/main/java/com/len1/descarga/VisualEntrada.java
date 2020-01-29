package com.len1.descarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VisualEntrada extends AppCompatActivity implements View.OnClickListener {

    Button editar;
    int tipo;
    int reco;
    long id;
    String cadNombre;
    String cadExp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_entrada);

        Intent intent = getIntent();
        ImageView fotoTipo = findViewById(R.id.visualFotoTipo);
        ImageView fotoRecomendado = findViewById(R.id.visualFotoRecomendado);
        TextView  nombre = findViewById(R.id.tvVisualNombre);
        TextView experi = findViewById(R.id.visualOpinion);

        cadNombre = intent.getStringExtra("nombre");
        cadExp = intent.getStringExtra("experiencia");
        tipo = Integer.parseInt(intent.getStringExtra("tipo"));
        reco = Integer.parseInt(intent.getStringExtra("recomendado"));
        id = Long.parseLong(intent.getStringExtra("id"));
        editar = findViewById(R.id.butEditar);
        editar.setOnClickListener(this);

        nombre.setText(cadNombre);
        experi.setText(cadExp);

        if(tipo==0){
            fotoTipo.setImageResource(R.drawable.madrid);
        }else{
            fotoTipo.setImageResource(R.drawable.merca);
        }
        if(reco==0){
            fotoRecomendado.setImageResource(R.drawable.astronaut);
        }else{
            fotoRecomendado.setImageResource(R.drawable.like3);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==editar.getId()){
            Intent intent = new Intent(this,entradaDetalle.class);

            Bundle bundle = new Bundle();

            bundle.putString("nombre",cadNombre);
            bundle.putString("tipo",String.valueOf(tipo));
            bundle.putString("id",String.valueOf(id));
            bundle.putBoolean("editar",true);
            intent.putExtra("paquete",bundle);
            startActivityForResult(intent,0);
        }
    }
}
