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

import com.example.sqlitecomponentes.tienda.Tienda;

import java.util.ArrayList;

import utilidades.Utilidades;

public class ListViewTiendaActivity9 extends AppCompatActivity {

    ListView listViewTienda;
    ArrayList<String> arrayListTienda;
    ArrayList<Tienda> arrayListTiendaClase;
    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_tienda9);

        listViewTienda = findViewById(R.id.listViewTienda);
        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes",null,3);

        consultarListaTienda();//Metodo que consulta la lista de Tiendas

        ArrayAdapter adaptador = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayListTienda);
        listViewTienda.setAdapter(adaptador);

        listViewTienda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {

                //Envio de objetos a otras activitys (InfoTiendaComponenteActivity10)
                //Creo el objeto tiendas de tipo Tienda (Clase), que contiene los datos de la tienda seleccionada
                Tienda tiendas = arrayListTiendaClase.get(posicion);

                Intent intento = new Intent(ListViewTiendaActivity9.this,InfoTiendaComponenteActivity10.class);
                Bundle miBundle = new Bundle();
                miBundle.putSerializable("info_tiendas",tiendas);//Para este caso es putSerializable, porque es un objeto como parametro
                intento.putExtras(miBundle);//hago en intento del envio del objeto
                startActivity(intento);
            }
        });
    }

    private void consultarListaTienda() {

        //conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes", null,2);

        SQLiteDatabase bd = conexion.getReadableDatabase(); // Creo obejeto bd del tipo SQLiteDataBase y leo la base de datos

        //Creo la instancia tiendas de la clase Tienda
        Tienda tiendas = null; //Creo una instancia tiendas a la clase Tienda de valor inicialmente a null

        arrayListTiendaClase = new ArrayList<Tienda>();

        //consulto los registros de la tabla Tienda usando la sigiente sintaxis
        //Cursor cursor = bd.rawQuery(" SELECT * FROM "+ Utilidades.TABLA_TIENDAS,null);
        Cursor cursor = bd.rawQuery(" SELECT * FROM " + Utilidades.TABLA_TIENDAS,null);

        while (cursor.moveToNext()) {//recorro o itero la información de la lista de la base de datos
            tiendas = new Tienda(); //Asigno en el objeto tiendas lo que hay en la base de datos, usando:
            tiendas.setIdTienda(cursor.getInt(0));
            tiendas.setNombre_tienda(cursor.getString(1));
            tiendas.setNit(cursor.getInt(2));
            tiendas.setDireccion(cursor.getString(3));
            tiendas.setTelefono(cursor.getInt(4));
            tiendas.setMi_componente(cursor.getString(5));

            arrayListTiendaClase.add(tiendas);//Obtengo lista y la incorporo a arrayListaTienda
        }
        obtenerLista();//Construyo la lista que se muestra en el ListView
    }

    private void obtenerLista() {

        arrayListTienda = new ArrayList<String>();

        for(int i = 0; i < arrayListTiendaClase.size(); i++){//Recorro la lista, con un tamaño maximo  igual a la cantidad de registros

            arrayListTienda.add(arrayListTiendaClase.get(i).getIdTienda()+" - Nombre: "+ arrayListTiendaClase.get(i).getNombre_tienda() +" - Nit: "+ arrayListTiendaClase.get(i).getNit());
        }
    }
}