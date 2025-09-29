package com.example.trabajopractico3.ui.busqueda;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajopractico3.R;
import com.example.trabajopractico3.databinding.FragmentBusquedaBinding;
import com.example.trabajopractico3.model.Producto;
import com.example.trabajopractico3.model.ProductoVisual;

public class BusquedaFragment extends Fragment {

    private BusquedaViewModel mv;
    private FragmentBusquedaBinding binding;

    public static BusquedaFragment newInstance() {
        return new BusquedaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(BusquedaViewModel.class);
        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getMError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new AlertDialog.Builder(getContext())
                        .setTitle("ERROR")
                        .setMessage(s)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

        mv.getMProducto().observe(getViewLifecycleOwner(), new Observer<ProductoVisual>() {
            @Override
            public void onChanged(ProductoVisual pv) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("producto",pv);
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.resultadoBusquedaFragment,bundle);
            }
        });

        binding.btBuscarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.verificarBusqueda(binding.etCodigoProducto.getText().toString());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}