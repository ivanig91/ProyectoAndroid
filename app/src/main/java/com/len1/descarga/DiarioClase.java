package com.len1.descarga;

import android.graphics.Bitmap;

public class DiarioClase {
    long diarioId;
    Bitmap foto;
    String nombre;
    String direccion;
    double longitud;
    double latitud;
    int recomendado; // Este es int porque en la base de datos no hay booleanos
    String experiencia;
    int tipo;

    public  DiarioClase (){

    }

    public DiarioClase(Bitmap foto, String nombre, String direccion, double longitud, double latitud, int recomendado, String experiencia, int tipo) {
        this.foto = foto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.recomendado = recomendado;
        this.experiencia = experiencia;
        this.tipo = tipo;
    }

    public DiarioClase(String nombre, int recomendado, String experiencia, int tipo, long diarioId) {
        this.nombre = nombre;
        this.recomendado = recomendado;
        this.experiencia = experiencia;
        this.tipo = tipo;
        this.diarioId = diarioId;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public int getRecomendado() {
        return recomendado;
    }

    public void setRecomendado(int recomendado) {
        this.recomendado = recomendado;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
    public long getDiarioId() {
        return diarioId;
    }

    public void setDiarioId(long diarioId) {
        this.diarioId = diarioId;
    }
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
