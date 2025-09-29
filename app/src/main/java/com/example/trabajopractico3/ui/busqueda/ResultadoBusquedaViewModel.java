package com.example.trabajopractico3.ui.busqueda;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopractico3.model.Producto;
import com.example.trabajopractico3.model.ProductoVisual;

public class ResultadoBusquedaViewModel extends ViewModel {
    private MutableLiveData<ProductoVisual> mProducto;

    public LiveData<ProductoVisual> getMProducto() {
        if(mProducto==null){
            mProducto = new MutableLiveData<>();
        }
        return mProducto;
    }

    public void armarProducto(Bundle bundle) {
        mProducto.setValue((ProductoVisual) bundle.getSerializable("producto"));
    }
}