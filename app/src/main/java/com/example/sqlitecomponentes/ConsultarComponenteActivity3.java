package com.example.sqlitecomponentes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import utilidades.Utilidades;

public class ConsultarComponenteActivity3 extends AppCompatActivity {

    EditText campoConsultar;
    TextView txtPrecio;
    TextView txtCategoria;
    ImageView imagenComponente;

    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_componente3);

        campoConsultar = findViewById(R.id.campoConsultar);
        txtPrecio= findViewById(R.id.txtPrecio);
        txtCategoria = findViewById(R.id.txtCategoria);
        imagenComponente = findViewById(R.id.imagenComponente);

        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes",null,3);
    }

    public void click(View view) {

        switch (view.getId()){

            case R.id.btnMiConsulta:
                //funcionConsular();
                funcionConsularSQL();
                break;

            case R.id.actualizarComponente:
                funcionActualizarComponente();
                break;

            case R.id.btnEliminarComponente:
                funcionEliminarComponente();
                break;
        }
    }

    private void funcionConsularSQL() {
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String[] nombreComponente = {campoConsultar.getText().toString()};

        try {
            //" select " categoria,precio from componente (tabla componente) where codigo=?
            Cursor cursor = bd.rawQuery(" SELECT " +Utilidades.CAMPO_PRECIO+ "," +Utilidades.CATEGORIA+  " FROM " +Utilidades.TABLA_COMPONENTES+ " WHERE " + Utilidades.CAMPO_NOMBRE + "=?",nombreComponente);
            cursor.moveToFirst();

            txtPrecio.setText(cursor.getString(0));
            txtCategoria.setText(cursor.getString(1));
            imagenComponente.setImageDrawable(getDrawable(R.drawable.esp32_pines));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El componente no existe\nen la lista",Toast.LENGTH_SHORT).show();
            fucionlimpiar();
        }
    }


    private void funcionActualizarComponente(){
       // SQLiteDatabase bd = conexion.getWritableDatabase();
        String [] nombreComponente = {campoConsultar.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoConsultar.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO,txtPrecio.getText().toString());
        values.put(Utilidades.CATEGORIA,txtCategoria.getText().toString());

       // bd.update(Utilidades.TABLA_COMPONENTES,values,Utilidades.CAMPO_NOMBRE + "=?",nombreComponente);
       // Toast.makeText(getApplicationContext(),"Componente actualizado",Toast.LENGTH_SHORT).show();

        String precio,categoria;

        precio = txtPrecio.getText().toString();
        categoria = txtCategoria.getText().toString();

        Intent intento = new Intent(getApplicationContext(),ActualizarComponenteActivity4.class);
        Bundle miBundle = new Bundle();
        miBundle.putString("nombreComponente",nombreComponente[0]);
        miBundle.putString("precio",precio);
        miBundle.putString("categoria",categoria);
        intento.putExtras(miBundle);
        startActivity(intento);
       // bd.close();
    }

    private void funcionEliminarComponente() {
        SQLiteDatabase bd = conexion.getWritableDatabase(); //Abro mi base de datos
        String [] nombreComponente = {campoConsultar.getText().toString()}; //Parametro de consulta

        // Envio la el registro de la tabla que quiero eliminar consultado por nombre
        bd.delete(Utilidades.TABLA_COMPONENTES,Utilidades.CAMPO_NOMBRE + "=?",nombreComponente);
        Toast.makeText(getApplicationContext(),"Componente eliminado",Toast.LENGTH_SHORT).show();
        campoConsultar.setText(""); //Limpio el campoConsultar
        imagenComponente.setImageDrawable(getDrawable(R.drawable.componente));
        fucionlimpiar();
        bd.close();
    }



//Revisar porque esta función consultar componente no me funcionó
    private void funcionConsular() {
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String [] parametros = {campoConsultar.getText().toString()};
        String [] infoComponente = {Utilidades.CAMPO_PRECIO,Utilidades.CATEGORIA};

        try {
            Cursor cursor = bd.query(Utilidades.TABLA_COMPONENTES,infoComponente,Utilidades.CAMPO_NOMBRE+"?",parametros,null,null,null);
            //Los parametors enviados como null corresponden a datos String asociados a groupBy, Having y OrderBy
            cursor.moveToFirst();
            txtPrecio.setText(cursor.getString(0));// esta posicion corresponde a la posicion cero del arreglo infoComponente
            txtCategoria.setText(cursor.getString(1));// esta posicion corresponde a la posicion 1 del arreglo infoComponente
            cursor.close();
            imagenComponente.setImageDrawable(getDrawable(R.drawable.esp32_pines));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El componente no existe\nen la lista",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"parametros:"+parametros,Toast.LENGTH_SHORT).show();

            fucionlimpiar();
        }
    }

    private void fucionlimpiar() {
        txtPrecio.setText("");
        txtCategoria.setText("");
        imagenComponente.setImageDrawable(getDrawable(R.drawable.componente));
    }
}