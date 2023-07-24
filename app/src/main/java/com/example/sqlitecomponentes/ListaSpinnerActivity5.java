package com.example.sqlitecomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import materiales.Componente;
import utilidades.Utilidades;

public class ListaSpinnerActivity5 extends AppCompatActivity {

    Spinner listaConsultaSpinner;
    TextView textoNombre, textoPrecio, textoCategoria;
    ArrayList<String> listaComponentesString; //Lista tipo String que representa la informacion que contiene el Spinner
    ArrayList<Componente> listaComponenteClase; //lista tipo Componente (Clase) Información que se va  mapear

    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_spinner5);

        conexion = new ConexionSQLiteHelper(this, "bd_componentes", null, 3);

        textoNombre = findViewById(R.id.textoNombre);
        textoPrecio = findViewById(R.id.textoPrecio);
        textoCategoria = findViewById(R.id.textoCategoria);
        listaConsultaSpinner = findViewById(R.id.listaConsultaSpinner);

        consultarListaComponentes(); //Metodo que llama la base datos

        //Construyo el adaptador del Spinner
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,listaComponentesString);
        listaConsultaSpinner.setAdapter(adaptador);

        listaConsultaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {

                //Para seleccionar un componente, uso la siguiente forma: listaComponenteClase.get(posicion - 1).getElementoAObtener
                if(posicion != 0){
                    textoNombre.setText("Nombre:      " + listaComponenteClase.get(posicion - 1).getNombre()); // - 1, porque cuenta la posicion 0 = Seleccione componente
                    textoPrecio.setText("Precio:     $ " + listaComponenteClase.get(posicion - 1).getPrecio().toString());
                    textoCategoria.setText("Categoria:    " + listaComponenteClase.get(posicion - 1).getCategoria());
                }
                else{
                    textoNombre.setText("Nombre:   ");
                    textoPrecio.setText("Precio:   ");
                    textoCategoria.setText("Categoria: ");
                    Toast.makeText(getApplicationContext(),"Seleccione un componente de la lista",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consultarListaComponentes() {

        //Leo base de dato para tener acceso
        SQLiteDatabase bd = conexion.getReadableDatabase(); // Creo obejeto bd del tipo SQLiteDataBase y leo la base de datos

        Componente componentes = null; //Creo una instancia componentes a la clase Componente de valor inicialmente a null
        listaComponenteClase = new ArrayList<Componente>();

        // " SELET * FROM " TABLA_COMPONENTES, null   >>>> Esto devuelve la lista de la TABLA_COMPONENTES
        Cursor cursor = bd.rawQuery(" SELECT * FROM " + Utilidades.TABLA_COMPONENTES, null);//null porque no le envio parametros

        while (cursor.moveToNext()) { //recorro o itero la información de la lista de la base de datos

            componentes = new Componente(); //Asigno en el objeto componentes lo que hay en la base de datos, usando:
            componentes.setNombre(cursor.getString(0));
            componentes.setPrecio(cursor.getInt(1));
            componentes.setCategoria(cursor.getString(2));

            listaComponenteClase.add(componentes); //Le asigno a la lista el objeto componentes
        }
        obtenerLista();//Construyo la lista que se muestra en el Spinner
    }

    //construyo lista que se va a mostrar en el combo listaComponentesString
    private void obtenerLista() {

        listaComponentesString = new ArrayList<String>();
        listaComponentesString.add("Seleccione componente");

        for (int i = 0; i < listaComponenteClase.size(); i ++){ //Recorro la lista, con un tamaño maximo igual a la cantidad de registros

            listaComponentesString.add(listaComponenteClase.get(i).getNombre() + " - Precio: $ " + listaComponenteClase.get(i).getPrecio());
        }
    }
}