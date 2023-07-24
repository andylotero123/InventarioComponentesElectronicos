package com.example.sqlitecomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import materiales.Componente;

public class MostrarDatosComponenteActivity7 extends AppCompatActivity {

    TextView textComponente, textPrecio, textCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos_componente7);

        textComponente = findViewById(R.id.textComponente);
        textPrecio = findViewById(R.id.textPrecio);
        textCategoria = findViewById(R.id.textCategoria);

        Bundle datosEnviados = getIntent().getExtras();
        Componente componente = null; //Creo el objeto componente del tipo Componente con valor inicial a null

        if(datosEnviados != null){
            //obtengo el objeto que llego de la activity ListaComponentesListViewActivity6 y se la paso a componente
            componente = (Componente) datosEnviados.getSerializable("info_componente");

            //Forma de obtener los datos del objeto obtenido para mostrar cada uno de los datos
            textComponente.setText(componente.getNombre().toString());
            textPrecio.setText(componente.getPrecio().toString());
            textCategoria.setText(componente.getCategoria().toString());
        }
    }
}