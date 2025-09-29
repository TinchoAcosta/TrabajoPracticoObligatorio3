package com.example.trabajopractico3.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class ProductoVisual implements Serializable {
    private Producto producto;
    private String precioFormateado;

    public ProductoVisual(Producto producto) {
        this.producto = producto;
        this.precioFormateado = new DecimalFormat("#,##0.00").format(producto.getPrecio());
    }

    public Producto getProducto() {
        return producto;
    }

    public String getPrecioFormateado() {
        return precioFormateado;
    }
}

