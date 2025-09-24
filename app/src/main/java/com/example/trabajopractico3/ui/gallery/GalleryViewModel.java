package com.example.trabajopractico3.ui.gallery;

import static com.example.trabajopractico3.MainActivity.stock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopractico3.model.Producto;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {


    private MutableLiveData<ArrayList<Producto>> mLista;


    public LiveData<ArrayList<Producto>> getMLista(){
        if(mLista==null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }

    public void cargarLista(){
        mLista.setValue(stock);
    }
}