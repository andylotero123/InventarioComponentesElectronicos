package com.example.sqlitecomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import materiales.Componente;
import utilidades.Utilidades;

public class ListaComponentesListViewActivity6 extends AppCompatActivity {

    ListView listViewComponentes;
    ArrayList<String> componentesLista; //Lista tipo String que representa la informacion que contiene el Spinner
    ArrayList<Componente> listaComponentesClase; //lista tipo Componente (Clase) Información que se va  mapear
    ConexionSQLiteHelper conexion; //Para usuarlo de manera global

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_componentes_list_view6);

        listViewComponentes = findViewById(R.id.listViewComponentes);

        conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_componentes", null, 3);

        consultarListaComponentes();//Metodo que consulta la lista de componentes

        //Sintaxis de adapatador para ListView
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,componentesLista);

        listViewComponentes.setAdapter(adaptador);

        listViewComponentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {

                //Para seleccionar un componente, uso la siguiente forma: listaComponenteClase.get(posicion).getElementoAObtener
                String informacion = "Nombre: " + listaComponentesClase.get(posicion).getNombre() + "\n";
                informacion += "Precio: " + listaComponentesClase.get(posicion).getPrecio() + "\n";
                informacion += "Categoría: " + listaComponentesClase.get(posicion).getCategoria();

                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_SHORT).show();

                //Envio de objetos a otras activitys (MostrarDatosComponenteActivity7)
                //Creo el objeto componente de tipo Componente (Clase), que contiene los datos del componente seleccionado
                Componente componente = listaComponentesClase.get(posicion);

                Intent intento = new Intent(ListaComponentesListViewActivity6.this,MostrarDatosComponenteActivity7.class);
                Bundle miBudle = new Bundle();
                miBudle.putSerializable("info_componente",componente);//Para este caso es putSerializable, porque es un objeto como parametro
                intento.putExtras(miBudle);//hago en intento del envio del objeto
                startActivity(intento);
            }
        });
    }

    private void consultarListaComponentes() {

        SQLiteDatabase bd = conexion.getReadableDatabase(); // Creo obejeto bd del tipo SQLiteDataBase y leo la base de datos

        Componente componentes = null; //Creo una instancia componentes a la clase Componente de valor inicialmente a null
        listaComponentesClase = new ArrayList<Componente>();

        //Consulto la tabla componentes usando la siguiente sintaxis
        Cursor cursor = bd.rawQuery("SELECT * FROM " + Utilidades.TABLA_COMPONENTES,null);

        while (cursor.moveToNext()){ //recorro o itero la información de la lista de la base de datos

            componentes = new Componente(); //Asigno en el objeto componentes lo que hay en la base de datos, usando:
            componentes.setNombre(cursor.getString(0));
            componentes.setPrecio(cursor.getInt(1));
            componentes.setCategoria(cursor.getString(2));

            listaComponentesClase.add(componentes);//Obtengo lista y la incorporo a listaComponentesClase
        }
        obtenerLista();//Construyo la lista que se muestra en el ListView
    }

    private void obtenerLista() {

        componentesLista = new ArrayList<String>();

        for(int i = 0; i < listaComponentesClase.size(); i ++){//Recorro la lista, con un tamaño maximo igual a la cantidad de registros

            componentesLista.add(listaComponentesClase.get(i).getNombre() + " - Precio: " + listaComponentesClase.get(i).getPrecio());
        }
    }
}