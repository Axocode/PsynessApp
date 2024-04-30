package com.example.psynessapp.feed;

import java.util.Date;
import java.util.Objects;

public class publis {

    private String nombre;
    private String hora;
    private String fecha;
    private String publicaccion;
    private int imgPubli;
    private boolean isLiked;

    public publis(String nombre, String hora, String fecha, String publicaccion, int imgPubli) {
        this.nombre = nombre;
        this.hora = hora;
        this.fecha = fecha;
        this.publicaccion = publicaccion;
        this.imgPubli = imgPubli;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPublicaccion() {
        return publicaccion;
    }

    public void setPublicaccion(String publicaccion) {
        this.publicaccion = publicaccion;
    }

    public int getImgPubli() {
        return imgPubli;
    }

    public void setImgPubli(int imgPubli) {
        this.imgPubli = imgPubli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        publis publis = (publis) o;
        return Objects.equals(nombre, publis.nombre) && Objects.equals(hora, publis.hora) && Objects.equals(fecha, publis.fecha) && Objects.equals(publicaccion, publis.publicaccion) && Objects.equals(imgPubli, publis.imgPubli);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nombre, hora, fecha, publicaccion, imgPubli);
    }




    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
