package com.example.sqlitecomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import materiales.Componente;
import utilidades.Utilidades;

public class RegistroTiendaActivity8 extends AppCompatActivity {

    EditText campoNombreTienda, campoNit, campoDireccion, campoTelefono;
    Spinner listaAsociadaComponente;
    ArrayList<String> listaComponentes;
    ArrayList<Componente> listComponenteClase;

    ConexionSQLiteHelper conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tienda8);

        //Se coloca version 3, porque se creo otra tabla
        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes",null,3);

        campoNombreTienda = findViewById(R.id.campoNombreTienda);
        campoNit = findViewById(R.id.campoNit);
        campoDireccion = findViewById(R.id.campoDireccion);
        campoTelefono = findViewById(R.id.campoTelefono);
        listaAsociadaComponente = findViewById(R.id.listaAsociadaComponente);

        consultarComponente();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listaComponentes);
        listaAsociadaComponente.setAdapter(adaptador);

        listaAsociadaComponente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    //funcion consultar componente para que la tienda escoja su componente
    private void consultarComponente() {
        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes",null,3);
        SQLiteDatabase bd = conexion.getReadableDatabase();

        Componente componente = null;
        listComponenteClase = new ArrayList<Componente>();

        //SELET * FROM TABLA_COMPONENTES, null   >>>> Esto devuelve la lista de la TABLA_COMPONENTES

        Cursor cursor = bd.rawQuery(" SELECT * FROM "+Utilidades.TABLA_COMPONENTES,null);

        while (cursor.moveToNext()) {
            componente = new Componente();
            componente.setNombre(cursor.getString(0));
            componente.setPrecio(cursor.getInt(1));
            componente.setCategoria(cursor.getString(2));

            listComponenteClase.add(componente);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaComponentes = new ArrayList<String>();

        listaComponentes.add("Seleccione componente");

        for (int i = 0; i < listComponenteClase.size(); i++){

            listaComponentes.add("Nombre: " + listComponenteClase.get(i).getNombre() +" - Precio: "+listComponenteClase.get(i).getPrecio());
        }
    }

    public void click(View view) {
        registrarTienda();
    }

    private void registrarTienda() {

        SQLiteDatabase bd = conexion.getWritableDatabase();//Me conecto con la base de datos y registro
        //variable tipo int, que guarda la posicion del Spinner seleccionado:
        int posicionLista = (int) listaAsociadaComponente.getSelectedItemId();

        /*Nota: En esta parte del registro no introduzco el registro del campo MI_COMPONENTE, porque se debe de hacer una vez se
                seleccione de la lista Spinner el componente usando su posicion (posicionLista) y extraigo el nombre con el metodo
                getNombre, metodo creado en la Clase Componentes del paquete materiales. NO ME FUCIONÓ CON CODIGO SQL
         */
        ContentValues values = new ContentValues();//Registro tienda con la clase ContentValues
        values.put(Utilidades.CAMPO_NOMBRE_TIENDA,campoNombreTienda.getText().toString());
        values.put(Utilidades.CAMPO_NIT,campoNit.getText().toString());
        values.put(Utilidades.CAMPO_DIRECCION,campoDireccion.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        if(posicionLista != 0) {

            ///REVISAR PRIMERO SI CAMBIANDO LA POSICION DEL ID DEL COMPONENTE FUNCIONA DEL ULTIMO AL PRIMERO

            Log.i("TAMAÑO",listComponenteClase.size()+"");
            Log.i("id combo",posicionLista+"");
            Log.i("id combo - 1",(posicionLista - 1)+"");
            //int idComponente = listComponenteClase.get(posicionLista - 1).getIdComponente();

            //Variable creada para guardar el nombre del componente, seleccionado del Spinner, seleccionando la posicion del Sppiner y extrayendo el nombre con getNombre
            String idComponente = listComponenteClase.get(posicionLista - 1).getNombre();
            Log.i("id componente",idComponente+"");
            values.put(Utilidades.MI_COMPONENTE,idComponente); //Hago el registro del campo MI_COMPONENTE y le paso el su valor contenido en idComponente

            //String insertar = "INSERT INTO " +Utilidades.TABLA_TIENDAS+ " ("+Utilidades.CAMPO_NOMBRE_TIENDA+","+Utilidades.CAMPO_NIT+","+Utilidades.CAMPO_DIRECCION+","+Utilidades.CAMPO_TELEFONO+", "+Utilidades.MI_COMPONENTE+")" + "VALUES ('"+campoNombreTienda.getText().toString()+"',"+campoNit.getText().toString()+",'"+campoDireccion.getText().toString()+"',"+campoTelefono.getText().toString()+",'"+idComponente+"')";

            //Inserto los registros en la tabla TIENDAS
            long numResultante = bd.insert(Utilidades.TABLA_TIENDAS,Utilidades.CAMPO_NOMBRE_TIENDA,values);
            bd.close();//Cierro la coenxion con la base datos

            /*
            Recuerda que el ID (en este caso es el nombre del componente) del componente sirve como elemento de consulta
             */
            Toast.makeText(getApplicationContext(), "Tienda registrada con éxito", Toast.LENGTH_SHORT).show();

            Intent intento = new Intent(RegistroTiendaActivity8.this, MainActivity.class);
            startActivity(intento);
        }
        else{
            Toast.makeText(getApplicationContext(),"Seleccione un componente de la lista",Toast.LENGTH_SHORT).show();
        }
    }
}