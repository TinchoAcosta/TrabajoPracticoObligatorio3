package com.example.trabajopractico3.ui.busqueda;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajopractico3.R;
import com.example.trabajopractico3.databinding.FragmentResultadoBusquedaBinding;
import com.example.trabajopractico3.model.Producto;
import com.example.trabajopractico3.model.ProductoVisual;

public class ResultadoBusquedaFragment extends Fragment {

    private ResultadoBusquedaViewModel mv;
    private FragmentResultadoBusquedaBinding binding;

    public static ResultadoBusquedaFragment newInstance() {
        return new ResultadoBusquedaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ResultadoBusquedaViewModel.class);
        binding = FragmentResultadoBusquedaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getMProducto().observe(getViewLifecycleOwner(), new Observer<ProductoVisual>() {
            @Override
            public void onChanged(ProductoVisual pv) {
                binding.tvTituloProducto.setText(pv.getProducto().getDescripcion());
                binding.tvCodigo.setText(pv.getProducto().getCodigo());
                binding.tvDescrip.setText(pv.getProducto().getDescripcion());
                binding.tvPrecio.setText("$ "+pv.getPrecioFormateado());
            }
        });

        mv.armarProducto(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}