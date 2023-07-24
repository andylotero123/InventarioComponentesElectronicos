package com.example.sqlitecomponentes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import utilidades.Utilidades;

//
public class ConexionSQLiteHelper extends SQLiteOpenHelper {
                                                             //Nombre base de datos
    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version); //al llamar este constructor se crea la base de datos
    }
    //oncreate genera las tablas de la base de datos o los Scripts
    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(Utilidades.CREAR_TABLA_COMPONENTES); //crea la base de datos llamada componentes
        bd.execSQL(Utilidades.CREAR_TABLA_TIENDAS);
    }

    //este metodo, cada vez que se ejecuta la aplicación, verifica si existe una versión antigua de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAntigua, int versionNueva) {
        bd.execSQL(" DROP TABLE IF EXISTS "+Utilidades.TABLA_COMPONENTES);// si se instala la aplicación y encuentra una versión antigua, la elimina y vuelva y la genere
        bd.execSQL(" DROP TABLE IF EXISTS " + Utilidades.TABLA_TIENDAS);
        onCreate(bd); //vuelva y cree la base de datos
    }
}
