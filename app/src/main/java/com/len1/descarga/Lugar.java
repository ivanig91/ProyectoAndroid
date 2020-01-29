package com.len1.descarga;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Lugar implements Serializable {
    String id;
    Bitmap foto;
    String nombre;
    String descripcion;
    String direccion;
    Float latitud;
    Float longitud;
    String calle;
    boolean visto;
    int tipo;
    String fotoString;



    public Lugar(String id, String nombre, String descripcion, Float latitud, Float longitud, String calle) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.calle = calle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }


    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Lugar(Bitmap foto, String nombre, String descripcion, String direccion, boolean visto, int tipo) {
        this.foto = foto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.visto = visto;
        this.tipo=tipo;
        this.direccion=direccion;
    }

    public Lugar(String nombre, String descripcion, String direccion, boolean visto, int tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.visto = visto;
        this.tipo = tipo;
    }

    public Lugar(){

    }



    public Bitmap getFoto() {
        return foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }
}
