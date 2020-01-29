package com.len1.descarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

public class entradaDetalle extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    DiarioClase entrada;
    ImageView fotoTipo;
    Button registraEntrada;
    EditText nombre;
    EditText opinion;
    Switch recom;
    int recomendado;
    int tipoBool=1;
    long diarId;
    boolean ediOregi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_detalle);
        Intent intent = getIntent();
        Bundle paque = intent.getBundleExtra("paquete");



        nombre = findViewById(R.id.nombreDetalle);
        opinion = findViewById(R.id.etOpinion);
        ediOregi = paque.getBoolean("editar");
        String cadenaNombre = paque.getString("nombre");
        String cadenaTipo = paque.getString("tipo");
        String  cadenaId = paque.getString("id");
        nombre.setText(cadenaNombre);
        diarId = Long.parseLong(cadenaId);

        Spinner spin = findViewById(R.id.tipoSpin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tipo,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        tipoBool = Integer.parseInt(cadenaTipo);
        if(tipoBool==0){
            spin.setSelection(0);
        }else{
            spin.setSelection(1);
        }

        recomendado=0;
        registraEntrada = findViewById(R.id.registraEntrada);
        registraEntrada.setOnClickListener(this);
        fotoTipo = findViewById(R.id.visualFotoRecomendado);
        recom = findViewById(R.id.recom);
        recom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    fotoTipo.setImageResource(R.drawable.like3);
                    recomendado =1;

                }else{
                    fotoTipo.setImageResource(R.drawable.astronaut);
                    recomendado=0;
                }
            }
        });
        fotoTipo.setImageResource(R.drawable.astronaut);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String tipo =  parent.getItemAtPosition(position).toString();
        if(tipo.equals("Monumento")){

            tipoBool =0;

        }else{
            tipoBool=1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==registraEntrada.getId()){

            String nom = nombre.getText().toString();
            String opi = opinion.getText().toString();

            DiarioClase entrada = new DiarioClase(nom,recomendado,opi,tipoBool,diarId);

            if(!ediOregi){
                DiarioLista.listaDiario.add(entrada);
            }else{
                int cont=0;
                int seleccion=0;
                for(DiarioClase elem : DiarioLista.listaDiario){
                    if(elem.diarioId==diarId){
                        seleccion=cont;
                    }
                    cont++;
                }

                DiarioLista.listaDiario.set(seleccion,entrada);
            }

            finish();

        }

    }
}
