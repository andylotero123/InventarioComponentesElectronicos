package com.example.sqlitecomponentes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.sqlitecomponentes.Adaptadores.ListaComponentesAdapter;

import java.util.ArrayList;

import materiales.Componente;
import utilidades.Utilidades;

public class ListaComponentesRecyclerView extends AppCompatActivity {

    ArrayList<Componente> listaComponentes;
    RecyclerView idRecyclerView;

    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_componentes_recycler_view);

        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_componentes",null,3);
        listaComponentes = new ArrayList<>();

        idRecyclerView = findViewById(R.id.idRecyclerView);
        idRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        consultarListaComponentes();

        ListaComponentesAdapter adapter = new ListaComponentesAdapter(listaComponentes);
        idRecyclerView.setAdapter(adapter);

    }

    private void consultarListaComponentes() {

        SQLiteDatabase bd = conexion.getReadableDatabase();

        Componente componente = null;

        Cursor cursor = bd.rawQuery(" SELECT * FROM " + Utilidades.TABLA_COMPONENTES,null);

        while (cursor.moveToNext()){

            componente = new Componente();

            componente.setNombre(cursor.getString(0));
            componente.setPrecio(cursor.getInt(1));
            componente.setCategoria(cursor.getString(2));

            listaComponentes.add(componente);
        }
    }
}