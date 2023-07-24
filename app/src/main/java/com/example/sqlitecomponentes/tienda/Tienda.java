package com.example.sqlitecomponentes.tienda;

import java.io.Serializable;

public class Tienda implements Serializable {//implements de la interfaz Serializable para poder enviar objetos como parametros

    private Integer idTienda;
    private String nombre_tienda;
    private Integer nit;
    private String direccion;
    private Integer telefono;
    private String mi_componente;

    public Tienda(Integer idTienda, String nombre_tienda, Integer nit, String direccion, Integer telefono, String mi_componente) {
        this.idTienda = idTienda;
        this.nombre_tienda = nombre_tienda;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mi_componente = mi_componente;
    }

    public Tienda(){

    }

    public Integer getIdTienda(){
        return idTienda;
    }

    public void setIdTienda(Integer idTienda){ this.idTienda = idTienda;}

    public String getNombre_tienda() {
        return nombre_tienda;
    }

    public void setNombre_tienda(String nombre_tienda) {
        this.nombre_tienda = nombre_tienda;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getMi_componente(){ return mi_componente;}

    public void setMi_componente(String mi_componente){ this.mi_componente = mi_componente;}
}
