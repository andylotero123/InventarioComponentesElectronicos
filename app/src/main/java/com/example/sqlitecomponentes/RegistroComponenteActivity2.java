package com.example.sqlitecomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import utilidades.Utilidades;

public class RegistroComponenteActivity2 extends AppCompatActivity {

    EditText campoNombre, campoPrecio;
    Spinner spinnerCategoria;
    String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_componente2);

        campoNombre = findViewById(R.id.campoNombre);
        campoPrecio = findViewById(R.id.campoPrecio);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);

        ArrayList<String> listaCatagoria = new ArrayList<String>();
        listaCatagoria.add("Categoria");
        listaCatagoria.add("Pantallas");
        listaCatagoria.add("Placas Arduino");
        listaCatagoria.add("Placas Wifi IoT");
        listaCatagoria.add("Modulos");
        listaCatagoria.add("Modulos RF");
        listaCatagoria.add("Sensores");
        listaCatagoria.add("Circuitos Integrados");

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,listaCatagoria);
        spinnerCategoria.setAdapter(adaptador);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {

                categoria = parent.getItemAtPosition(posicion).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void click(View view) {

        if(categoria == "Categoria") { //Si no elige nada del Spinner o deja por default a categoria
            Toast.makeText(getApplicationContext(),"Elija un componente \nde la categoria",Toast.LENGTH_SHORT).show();
        }
            else{ //Si elije una opcion diferente a categoria
            //registrarComponentes();
            registrarComponentesSQL();
        }
    }

    private void registrarComponentesSQL() {
        //abro inicialemente la conexion a la base de datos
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes",null,3);
        SQLiteDatabase bd = conexion.getWritableDatabase();//indico que abro la base de datos para editarla

        //Insetar datos mediante lenguaje SQL de android
        //Sintaxis: " insert into " + Nombre_base_de_datos, luego los campos (precio,nombre,categoria) values (45000,'ESP32','placas IoT')
        String insert = "INSERT INTO "+Utilidades.TABLA_COMPONENTES+" ("+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_PRECIO+","+Utilidades.CATEGORIA+")" + " VALUES ('"+campoNombre.getText().toString()+"',"+campoPrecio.getText().toString()+",'"+categoria+"')";
        bd.execSQL(insert);
        bd.close();

        Toast.makeText(getApplicationContext(),"Componente registrado",Toast.LENGTH_SHORT).show();

        Intent intento = new Intent(RegistroComponenteActivity2.this,MainActivity.class);
        startActivity(intento);
    }

    private void registrarComponentes() {

        //abro la conexión de la base datos, para escribir en la tabla

        //Creo la instancia para la clase ConexionSQLiteHelper que recibe los siguientes parametros
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes",null,3);

        SQLiteDatabase bd = conexion.getWritableDatabase(); //indico que abro la base de datos para editarla

        //Insertar datos mediante la clase ContenValues
        //Registro en la base de datos, donde se usa un identificador y la info
        //identificador: Utilidades.CAMPO_PRECIO, info: campoPrecio.getText().toString()
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO,campoPrecio.getText().toString());
        values.put(Utilidades.CATEGORIA,categoria);

        //inserto los datos en la base de datos con el metodo insert
        long numRegistro = bd.insert(Utilidades.TABLA_COMPONENTES,Utilidades.CAMPO_NOMBRE,values);

        Toast.makeText(getApplicationContext(),"Número de registro: " + numRegistro,Toast.LENGTH_SHORT).show();
        bd.close();
    }
}