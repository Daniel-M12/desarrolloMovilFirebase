package com.matos.firebase.model;

import androidx.annotation.NonNull;

public class Persona {
    private String id;
    private String nombres;
    private String apellidos;
    private String correo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @NonNull
    @Override
    public String toString() {
        return nombres + ' ' + apellidos;
    }
}
