package com.len1.descarga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mapa;
    private double latitud;
    private double longitud;
    private String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        Intent intent = getIntent();
        SupportMapFragment mapaFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapaFrag.getMapAsync(this);


        // en este punto esoty utilizando los atributos estaticos, no seas guarro

        Bundle paque = intent.getBundleExtra("paquete");


        String longiString = paque.getString("longitud");

        String latiString = paque.getString("latitud");
        latitud =Double.parseDouble(latiString);
        longitud = Double.parseDouble(longiString);
        nombre = paque.getString("id");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        float zoom = 16.0f;


        LatLng lugar = new LatLng(latitud,longitud);
        Intent intent = getIntent();
        mapa.addMarker(new MarkerOptions().position(lugar).title(nombre));
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(lugar, zoom));

    }
}
