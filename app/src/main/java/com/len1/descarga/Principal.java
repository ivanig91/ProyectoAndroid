package com.len1.descarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Principal extends AppCompatActivity implements View.OnClickListener {

    private Button btMonu;
    private Button btMerca;
    private Button btDiario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ImageView img = findViewById(R.id.ivPrincipal);
        img.setImageResource(R.drawable.mtj);

        btMonu = findViewById(R.id.btPMonu);
        btMonu.setTag(1);
        btMonu.setOnClickListener(this);
        btMerca = findViewById(R.id.btPMerca);
        btMerca.setTag(2);
        btMerca.setOnClickListener(this);
        btDiario = findViewById(R.id.btDiario);
        btDiario.setTag(3);
        btDiario.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(v.getTag()==btMonu.getTag()){
            Intent intent = new Intent(v.getContext(),MainActivity.class);
            startActivityForResult(intent,0);


        }else{
            if(v.getTag()==btDiario.getTag()){
                Intent intent = new Intent (v.getContext(),DiarioLista.class);
                startActivityForResult(intent,0);
            }
        }

    }
}
