package com.example.sqlitecomponentes.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.inline.InlineContentView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitecomponentes.R;

import java.util.ArrayList;

import materiales.Componente;

public class ListaComponentesAdapter extends RecyclerView.Adapter<ListaComponentesAdapter.ViewHolderComponentes> {


    ArrayList<Componente> listaComponentes;

    public ListaComponentesAdapter(ArrayList<Componente> listaComponente){
        this.listaComponentes = listaComponente;
    }

    @NonNull
    @Override
    public ViewHolderComponentes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_componentes_recycler_view,null,false);
        return new ViewHolderComponentes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderComponentes holder, int position) {

        holder.nameComponente.setText(listaComponentes.get(position).getNombre());
        holder.idPrecioComponente.setText(listaComponentes.get(position).getPrecio().toString());
        holder.idCategoriaComponente.setText(listaComponentes.get(position).getCategoria());
    }

    @Override
    public int getItemCount() {
        return listaComponentes.size();
    }

    public class ViewHolderComponentes extends RecyclerView.ViewHolder {

        TextView nameComponente, idPrecioComponente, idCategoriaComponente;

        public ViewHolderComponentes(@NonNull View itemView) {
            super(itemView);

            //Referencio los compopenentes del item_lista_componentes_recycler_view
            nameComponente = itemView.findViewById(R.id.nameComponente);
            idPrecioComponente = itemView.findViewById(R.id.idPrecioComponente);
            idCategoriaComponente = itemView.findViewById(R.id.idCategoriaComponente);
        }
    }
}
