package utilidades;

import android.app.admin.SystemUpdateInfo;

//En base de datos SQLite,en android developer recomiendan
// crear una clase con las constantes representando  los campos
// o cualquier entrada y las tablas de la base de datos
public class Utilidades {

    //Constantes de tabla Componente
    public static final String TABLA_COMPONENTES = "componente";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_PRECIO = "precio";
    public static final String CATEGORIA = "categoria";
    //contante para crear la base de datos
   // public static final String CREAR_TABLA_COMPONENTES = "CREATE TABLE" +TABLA_COMPONENTES+" ("+CAMPO_PRECIO+" INTEGER, "+CAMPO_NOMBRE+" TEXT, "+CATEGORIA+" TEXT)";

      public static final String CREAR_TABLA_COMPONENTES = "CREATE TABLE "+TABLA_COMPONENTES+" ( "+CAMPO_NOMBRE+" TEXT, "+CAMPO_PRECIO+" INTEGER, "+CATEGORIA+" TEXT)";
      //Este variable tiene la finalidad de crear la tabla componentes

    //Nueva tabla creada dentro de la base de datos llamada "bd_componentes"
    //Constantes de tabla Tienda
    public static final String TABLA_TIENDAS = "tiendas";
    public static final String CAMPO_ID_TIENDA = "id_tienda"; //ID incrementable, para tener el conteo de las tiendas
    public static final String CAMPO_NOMBRE_TIENDA = "nombre_tienda";
    public static final String CAMPO_NIT = "nit";
    public static final String CAMPO_DIRECCION = "direccion";
    public static final String CAMPO_TELEFONO = "telefono";
    public static final String MI_COMPONENTE = "mi_componente"; //Campo donde se guarda el objeto del tipo Componente, que contiene la info del componente seleccionado
   // public static final String[] MIS_COMPONENTES = {}; //Queda pendiente implementar un array dinamico

    public static final String CREAR_TABLA_TIENDAS = " CREATE TABLE "+TABLA_TIENDAS+" ("+CAMPO_ID_TIENDA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE_TIENDA+" TEXT, "+CAMPO_NIT+" INTEGER, "+CAMPO_DIRECCION+" TEXT, "+CAMPO_TELEFONO+" INTEGER, "+MI_COMPONENTE+" TEXT)";
}
