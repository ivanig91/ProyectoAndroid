package com.len1.descarga;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DiarioLista extends AppCompatActivity implements View.OnClickListener {


    public static ArrayList<DiarioClase> listaDiario = new ArrayList<>();
    private DiarioAdapter adapter;
    private String nom;
    private  String opi;
    private int recomen;
    private int tipoBool;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario_lista);

        adapter = new DiarioAdapter(this,listaDiario);
        ListView lvDiario = findViewById(R.id.lvDiario);
        lvDiario.setAdapter(adapter);
        lvDiario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiarioClase  entradaDiario;
                entradaDiario = (DiarioClase) parent.getAdapter().getItem(position);

                Intent intent = new Intent(view.getContext(),VisualEntrada.class);
                if(entradaDiario ==null){
                    Log.i("info recomendado", "es nulo");
                }



                intent.putExtra("recomendado",String.valueOf(entradaDiario.recomendado));
                intent.putExtra("tipo",String.valueOf(entradaDiario.tipo));
                intent.putExtra("nombre",entradaDiario.nombre);
                intent.putExtra("experiencia",entradaDiario.experiencia);
                intent.putExtra("id",String.valueOf(entradaDiario.diarioId));


                startActivityForResult(intent,0);

            }
        });

        adapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View v) {
        // Debo crearme la nueva activity para el registro de la nueva


    }
    @Override
    protected void onResume() {
        super.onResume();

        Log.i("el resume"," el resume se hace");
        int i =0;
        String mensaje="";
        mensaje = String.valueOf(adapter.getCount());
        Log.i("nombre",mensaje);
        adapter.notifyDataSetChanged();

    }


}
