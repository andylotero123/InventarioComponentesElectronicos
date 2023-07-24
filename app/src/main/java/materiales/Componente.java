package materiales;

import java.io.Serializable;

public class Componente implements Serializable { //implements de la interfaz Serializable para poder enviar objetos como parametros

    private String nombre;
    private Integer precio;
    private String categoria;


    public Componente(String nombre, Integer precio, String categoria) {

        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;

    }

    public Componente(){ //P
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
