package com.example.trabajopractico3.ui.home;

import static com.example.trabajopractico3.MainActivity.stock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopractico3.model.Producto;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mError, mCorrecto;

    public LiveData<String> getMError() {
        if(mError==null){
            mError = new MutableLiveData<>();
        }
        return mError;
    }

    public LiveData<String> getMCorrecto() {
        if(mCorrecto==null){
            mCorrecto = new MutableLiveData<>();
        }
        return mCorrecto;
    }

    public void validar(String codigo, String descrip, String precio){
        if(codigo.isEmpty() || descrip.isEmpty() || precio.isEmpty()){
            mError.setValue("Por favor, complete toda la información.");
            return;
        }
        if(precio.startsWith(".")){
            mError.setValue("El precio no puede comenzar con el punto.");
            return;
        }
        if(precio.equals("0")){
            mError.setValue("El precio no puede ser cero.");
            return;
        }
        if(!precio.matches("^(0|[1-9][0-9]*)(\\.[0-9]{1,2})?$")){
            mError.setValue("Ingrese un precio válido (hasta dos decimales si los tiene)");
            return;
        }
        for (Producto p:stock) {
            if(p.getCodigo().equalsIgnoreCase(codigo)){
                mError.setValue("El código ingresado ya esta registrado.");
                return;
            }
            if(p.getDescripcion().equalsIgnoreCase(descrip)){
                mError.setValue("El producto ingresado ya existe");
                return;
            }
        }
        Producto producto = new Producto(codigo,descrip,Double.parseDouble(precio));
        stock.add(producto);
        mCorrecto.setValue("¡Producto agregado correctamente!");
    }
}