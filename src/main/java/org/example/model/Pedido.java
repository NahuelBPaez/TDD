package org.example.model;

import org.example.excepciones.PedidoInvalidoException;
import org.example.excepciones.StockInsuficienteException;

public class Pedido {

    private static final int CANTIDAD_MINIMA = 1;
    private static final int CANTIDAD_MAXIMA = 10;

    private final Cliente cliente;
    private final Producto producto;
    private final int cantidad;

    private Pedido(Cliente cliente, Producto producto, int cantidad) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public static Pedido crear(Cliente cliente, Producto producto, int cantidad) {
        if (cliente == null) {
            throw new PedidoInvalidoException("El pedido debe tener un cliente");
        }
        if (producto == null) {
            throw new PedidoInvalidoException("El pedido debe tener un producto");
        }
        if (cantidad < CANTIDAD_MINIMA || cantidad > CANTIDAD_MAXIMA) {
            throw new PedidoInvalidoException("La cantidad debe estar entre "
                    + CANTIDAD_MINIMA + " y " + CANTIDAD_MAXIMA);
        }
        if (cantidad > producto.getStock()) {
            throw new StockInsuficienteException("Stock insuficiente: se pidieron "
                    + cantidad + " y hay " + producto.getStock());
        }
        return new Pedido(cliente, producto, cantidad);
    }

    public double calcularTotal() {
        return producto.getPrecio() * cantidad;
    }

    public Cliente getCliente() { return cliente; }

    public Producto getProducto() { return producto; }

    public int getCantidad() { return cantidad; }
}
