package com.example.trabajopractico3.mapa;

import static androidx.lifecycle.AndroidViewModel_androidKt.getApplication;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsViewModel extends AndroidViewModel {

    private FusedLocationProviderClient fused;
    private Context context;
    private MutableLiveData<Location> mLocation;
    private MutableLiveData<MapaActual> mMapa;
    public MapsViewModel(@NonNull Application application) {
        super(application);
        context=getApplication();
        fused= LocationServices.getFusedLocationProviderClient(context);
    }
    public LiveData<Location> getMlocation(){
        if(mLocation==null){
            mLocation=new MutableLiveData<>();
        }
        return mLocation;
    }



    public LiveData<MapaActual> getMMapa(){
        if(mMapa==null){
            mMapa = new MutableLiveData<>();
        }
        return mMapa;
    }

    public void cargarMapa(Location ubicacion){
        MapaActual mapaActual = new MapaActual(ubicacion);
        mMapa.setValue(mapaActual);
    }

    public void obtenerUbicacion(){

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context
                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> tarea= fused.getLastLocation();
        tarea.addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location!=null){

                    mLocation.postValue(location);

                }

            }
        });

    }

    public class MapaActual implements OnMapReadyCallback {

        LatLng ubi;

        public MapaActual(Location ubicacion){
            this.ubi = new LatLng(ubicacion.getLatitude(),ubicacion.getLongitude());
        }


        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            MarkerOptions marcador = new MarkerOptions();
            marcador.position(ubi);
            marcador.title("Ubicacion");

            googleMap.addMarker(marcador);
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            CameraPosition cam = new CameraPosition.Builder()
                    .target(ubi)
                    .zoom(30)
                    .bearing(45)
                    .tilt(15)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cam);
            googleMap.animateCamera(cameraUpdate);
        }
    }

}
