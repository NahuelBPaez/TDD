package org.example.model;

import org.example.excepciones.ProductoInvalidoException;

import java.util.regex.Pattern;

public class Producto {

    private static final Pattern FORMATO_CODIGO = Pattern.compile("^PRD-\\d{4}$");
    private static final int NOMBRE_LONGITUD_MINIMA = 3;
    private static final int NOMBRE_LONGITUD_MAXIMA = 40;

    private final String codigo;
    private final String nombre;
    private final double precio;
    private final int stock;

    private Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public static Producto crear(String codigo, String nombre, double precio, int stock) {
        validarCodigo(codigo);
        String nombreNormalizado = validarNombre(nombre);
        validarPrecio(precio);
        validarStock(stock);
        return new Producto(codigo, nombreNormalizado, precio, stock);
    }

    private static void validarCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new ProductoInvalidoException("El código es obligatorio");
        }
        if (!FORMATO_CODIGO.matcher(codigo).matches()) {
            throw new ProductoInvalidoException("El código debe tener el formato PRD-0000");
        }
    }

    private static String validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ProductoInvalidoException("El nombre es obligatorio");
        }
        String normalizado = nombre.trim();
        if (normalizado.length() < NOMBRE_LONGITUD_MINIMA
                || normalizado.length() > NOMBRE_LONGITUD_MAXIMA) {
            throw new ProductoInvalidoException("El nombre debe tener entre "
                    + NOMBRE_LONGITUD_MINIMA + " y " + NOMBRE_LONGITUD_MAXIMA + " caracteres");
        }
        return normalizado;
    }

    private static void validarPrecio(double precio) {
        if (precio <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor a cero");
        }
    }

    private static void validarStock(int stock) {
        if (stock < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo");
        }
    }

    public String getCodigo() { return codigo; }

    public String getNombre() { return nombre; }

    public double getPrecio() { return precio; }

    public int getStock() { return stock; }
}
