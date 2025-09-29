package com.example.trabajopractico3.ui.busqueda;

import static com.example.trabajopractico3.MainActivity.stock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopractico3.model.Producto;
import com.example.trabajopractico3.model.ProductoVisual;

public class BusquedaViewModel extends ViewModel {
    private MutableLiveData<String> mError;
    private MutableLiveData<ProductoVisual> mProducto;

    public LiveData<String> getMError() {
        if(mError==null){
            mError = new MutableLiveData<>();
        }
        return mError;
    }

    public LiveData<ProductoVisual> getMProducto() {
        if(mProducto==null){
            mProducto = new MutableLiveData<>();
        }
        return mProducto;
    }

    public void verificarBusqueda(String s){
        for (Producto p : stock) {
            if(s.equalsIgnoreCase(p.getCodigo())){
                mProducto.setValue(new ProductoVisual(p));
                return;
            }
        }
        mError.setValue("No existe ningún producto con el código ingresado.");
    }
}