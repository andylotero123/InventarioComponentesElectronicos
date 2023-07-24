package com.example.sqlitecomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitecomponentes.tienda.Tienda;

import utilidades.Utilidades;

public class InfoTiendaComponenteActivity10 extends AppCompatActivity {

    //Datos de la tienda:
    TextView txtIdTienda, txtNombreTienda, txtNitTienda, txtDireccionTienda, txtTelefonoTienda;

    //Datos del componente:
    TextView txtNombreCompoenente, txtPrecioCompoenente, txtCategoriaComponente;
    String infoTienda;

    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tienda_componente10);

        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes",null,3);

        txtIdTienda = findViewById(R.id.txtIdTienda);
        txtNombreTienda = findViewById(R.id.txtNombreTienda);
        txtNitTienda = findViewById(R.id.txtNitTienda);
        txtDireccionTienda = findViewById(R.id.txtDireccionTienda);
        txtTelefonoTienda = findViewById(R.id.txtTelefonoTienda);

        txtNombreCompoenente = findViewById(R.id.txtNombreComponente);
        txtPrecioCompoenente = findViewById(R.id.txtPrecioComponente);
        txtCategoriaComponente = findViewById(R.id.txtCategoriaComponente);

        Bundle datosEnviados = getIntent().getExtras(); //Obtengo el objeto enviado

        Tienda tiendas = null; //Creo el objeto tiendas del tipo Tiendas

        if(datosEnviados != null){

            //obtengo el objeto que llego de la activity ListViewTiendaActivity9 y se la paso a tiendas
            tiendas = (Tienda) datosEnviados.getSerializable("info_tiendas");

            //Forma de obtener la información del objeto obtenido para mostrar cada uno de los datos
            txtIdTienda.setText(tiendas.getIdTienda().toString());
            txtNombreTienda.setText(tiendas.getNombre_tienda().toString());
            txtNitTienda.setText(tiendas.getNit().toString());
            txtDireccionTienda.setText(tiendas.getDireccion().toString());
            txtTelefonoTienda.setText(tiendas.getTelefono().toString());

            infoTienda = tiendas.getMi_componente(); //obtengo el parametro del campo MI_COMPONENTE, que esta en el objeto tiendas con el metodo getMi_componente()
                                                    //y lo guardo en una variable global infoTienda de tipo String
            infoComponente(); //llamo el metodo  infoComponente() para consultar y mostrar la información del componente elegido por la tienda
            /*
            NOTA: IDEA; QUE UNA TIENDA TENGA LA POSIBILIDAD DE ELEGIR VARIOS COMPONENTES DE LA LISTA E GUARDARLOS, PUEDE SER EN UN ARRAY DINAMICO
                        Y LLENARLOS CON LA AYUDA DE UN FOR, Y PARA MOSTRAR LA INFORMACIÓN DE LOS COMPONENTES, SE PUEDE HACER DE MANERA INDIVIDUAL
                        HACIENDOLO CON UN SPINNER, Y SOLO ES SELECCIONAR EL ELEMENTO DEL SPINNER, QUE REALMENTE ES UN OBJETO componente de la clase
                        Componente
             */
        }
    }

    private void infoComponente() {

        SQLiteDatabase bd = conexion.getReadableDatabase(); //Conecto con la base de datos para leerla
        String[] infoComponente = {infoTienda.toString()}; // arreglo que contiene el parametro nombreComponente

        try {
            //Consulto en la tabla Componentes de la siguiente manera:
            Cursor cursor = bd.rawQuery(" SELECT " +Utilidades.CAMPO_PRECIO+ "," +Utilidades.CATEGORIA+ " FROM " +Utilidades.TABLA_COMPONENTES+ " WHERE " +Utilidades.CAMPO_NOMBRE+ "=?",infoComponente);
            cursor.moveToFirst();
            txtNombreCompoenente.setText(infoTienda);
            txtPrecioCompoenente.setText(cursor.getString(0));
            txtCategoriaComponente.setText(cursor.getString(1));

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"No existe el componente",Toast.LENGTH_SHORT).show();
        }
    }
}