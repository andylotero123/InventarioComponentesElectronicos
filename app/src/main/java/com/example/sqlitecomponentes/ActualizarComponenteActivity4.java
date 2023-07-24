package com.example.sqlitecomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import utilidades.Utilidades;

public class ActualizarComponenteActivity4 extends AppCompatActivity {

    TextView campoNombreGuardar;
    TextView campoPrecioGuardar;
    TextView campoCategoriaGuardar;

    ConexionSQLiteHelper conexion;

    String nombreComponente, precio, categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_componente4);

        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes",null,3);

        campoNombreGuardar = findViewById(R.id.campoNombreGuardar);
        campoPrecioGuardar = findViewById(R.id.campoPrecioGuardar);
        campoCategoriaGuardar = findViewById(R.id.campoCategoriaGuardar);

        Bundle miBudle = this.getIntent().getExtras();

        if(miBudle != null){
            nombreComponente = miBudle.getString("nombreComponente");
            precio = miBudle.getString("precio");
            categoria = miBudle.getString("categoria");

            campoNombreGuardar.setText(nombreComponente);
            campoPrecioGuardar.setText(precio);
            campoCategoriaGuardar.setText(categoria);
        }
    }

    public void click(View view) {

        SQLiteDatabase bd = conexion.getWritableDatabase();
        String [] nombreComponente = {campoNombreGuardar.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombreGuardar.getText().toString()); // Se usa para guardar
        values.put(Utilidades.CAMPO_PRECIO,campoPrecioGuardar.getText().toString()); // en los Utilidades.CAMPO
        values.put(Utilidades.CATEGORIA,campoCategoriaGuardar.getText().toString()); // lo editado en los campos correspondientes

        bd.update(Utilidades.TABLA_COMPONENTES,values,Utilidades.CAMPO_NOMBRE + "=?", nombreComponente);//Metodo para guardar la info editada
        Toast.makeText(getApplicationContext(),"Componente actualizado",Toast.LENGTH_SHORT).show();
        bd.close();
    }
}