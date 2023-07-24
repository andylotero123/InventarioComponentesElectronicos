package com.example.sqlitecomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo la instancia para la clase ConexionSQLiteHelper que recibe los siguientes parametros
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this,"bd_componentes",null,3);
    }

    public void click(View view) {

        Intent intento = null;

        switch (view.getId()){

            case R.id.btnRegistrar:
                intento = new Intent(MainActivity.this,RegistroComponenteActivity2.class);
                break;

            case R.id.btnConsultar:
                intento = new Intent(MainActivity.this,ConsultarComponenteActivity3.class);
                break;

            case R.id.btnSpinner:
                intento = new Intent(MainActivity.this,ListaSpinnerActivity5.class);
                break;

            case R.id.btnListView:
                intento = new Intent(MainActivity.this,ListaComponentesListViewActivity6.class);
                break;

            case R.id.btnRegistrarTienda:
                intento = new Intent(MainActivity.this,RegistroTiendaActivity8.class);
                break;

            case R.id.btnListViewTienda:
                intento = new Intent(MainActivity.this,ListViewTiendaActivity9.class);
                break;

            case R.id.btnListaRecyclerView:
                intento = new Intent(MainActivity.this,ListaComponentesRecyclerView.class);
                break;

        }
        startActivity(intento);
    }
}