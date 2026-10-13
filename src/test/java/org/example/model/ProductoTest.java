package org.example.model;

import org.example.excepciones.ProductoInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    // ---------- Creación válida ----------

    @Test
    void crearProductoValidoConservaSusDatos() {
        Producto producto = Producto.crear("PRD-0001", "Teclado mecánico", 85.5, 10);

        assertEquals("PRD-0001", producto.getCodigo());
        assertEquals("Teclado mecánico", producto.getNombre());
        assertEquals(85.5, producto.getPrecio(), 0.001);
        assertEquals(10, producto.getStock());
    }

    @Test
    void crearProductoAceptaStockCero() {
        Producto producto = Producto.crear("PRD-0002", "Mouse", 20.0, 0);

        assertEquals(0, producto.getStock());
    }

    // ---------- Código ----------

    @Test
    void crearProductoConCodigoNuloLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear(null, "Mouse", 20.0, 5));
    }

    @Test
    void crearProductoConCodigoVacioLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("", "Mouse", 20.0, 5));
    }

    @Test
    void crearProductoConCodigoSinPrefijoLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("0001", "Mouse", 20.0, 5));
    }

    @Test
    void crearProductoConCodigoDeLongitudIncorrectaLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("PRD-01", "Mouse", 20.0, 5));
    }

    @Test
    void crearProductoConCodigoNoNumericoLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("PRD-ABCD", "Mouse", 20.0, 5));
    }

    // ---------- Nombre ----------

    @Test
    void crearProductoConNombreNuloLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("PRD-0001", null, 20.0, 5));
    }

    @Test
    void crearProductoConNombreVacioLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("PRD-0001", "  ", 20.0, 5));
    }

    @Test
    void crearProductoConNombreMuyCortoLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("PRD-0001", "ab", 20.0, 5));
    }

    @Test
    void crearProductoConNombreMuyLargoLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("PRD-0001", "A".repeat(41), 20.0, 5));
    }

    // ---------- Precio ----------

    @Test
    void crearProductoConPrecioCeroLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("PRD-0001", "Mouse", 0, 5));
    }

    @Test
    void crearProductoConPrecioNegativoLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("PRD-0001", "Mouse", -1.5, 5));
    }

    // ---------- Stock ----------

    @Test
    void crearProductoConStockNegativoLanzaExcepcion() {
        assertThrows(ProductoInvalidoException.class,
                () -> Producto.crear("PRD-0001", "Mouse", 20.0, -1));
    }
}
