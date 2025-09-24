package com.example.trabajopractico3.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajopractico3.R;
import com.example.trabajopractico3.model.Producto;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolderStock> {
    private ArrayList<Producto> lista;
    private Context context;
    private LayoutInflater li;
    private DecimalFormat formatoPrecio = new DecimalFormat("#,##0.00");

    public ListaAdapter(ArrayList<Producto> lista, LayoutInflater li, Context context) {
        this.lista = lista;
        this.li = li;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderStock onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = li.inflate(R.layout.lista_adapter_item,parent,false);
        return new ViewHolderStock(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderStock holder, int position) {
        if (lista.isEmpty()) {
            holder.codigo.setText("");
            holder.descrip.setText(R.string.no_hay_productos_disponibles);
            holder.precio.setText("");
        } else {
            Producto prodActual = lista.get(position);
            holder.codigo.setText(prodActual.getCodigo());
            holder.descrip.setText(prodActual.getDescripcion());
            String precioFormateado = formatoPrecio.format(prodActual.getPrecio());
            holder.precio.setText("$ "+precioFormateado);
        }
    }

    @Override
    public int getItemCount() {
        return lista.isEmpty() ? 1 : lista.size();
    }

    public class ViewHolderStock extends RecyclerView.ViewHolder{
        TextView codigo, descrip, precio;
        public ViewHolderStock(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.tvCodigoItem);
            descrip = itemView.findViewById(R.id.tvDescripItem);
            precio = itemView.findViewById(R.id.tvPrecioItem);
        }
    }
}
