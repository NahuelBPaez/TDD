package org.example.model;

import org.example.excepciones.PedidoInvalidoException;
import org.example.excepciones.StockInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Cliente cliente;
    private Producto producto;

    @BeforeEach
    void prepararDatos() {
        cliente = Cliente.crear("Ana Pérez", "ana@correo.com", 30);
        producto = Producto.crear("PRD-0001", "Teclado mecánico", 50.0, 5);
    }

    // ---------- Creación válida ----------

    @Test
    void crearPedidoValidoConservaSusDatos() {
        Pedido pedido = Pedido.crear(cliente, producto, 2);

        assertSame(cliente, pedido.getCliente());
        assertSame(producto, pedido.getProducto());
        assertEquals(2, pedido.getCantidad());
    }

    @Test
    void calcularTotalMultiplicaPrecioPorCantidad() {
        Pedido pedido = Pedido.crear(cliente, producto, 3);

        assertEquals(150.0, pedido.calcularTotal(), 0.001);
    }

    @Test
    void crearPedidoPorExactamenteElStockDisponibleEsValido() {
        Pedido pedido = Pedido.crear(cliente, producto, 5);

        assertEquals(5, pedido.getCantidad());
    }

    // ---------- Cliente y producto ----------

    @Test
    void crearPedidoSinClienteLanzaExcepcion() {
        assertThrows(PedidoInvalidoException.class,
                () -> Pedido.crear(null, producto, 1));
    }

    @Test
    void crearPedidoSinProductoLanzaExcepcion() {
        assertThrows(PedidoInvalidoException.class,
                () -> Pedido.crear(cliente, null, 1));
    }

    // ---------- Cantidad ----------

    @Test
    void crearPedidoConCantidadCeroLanzaExcepcion() {
        assertThrows(PedidoInvalidoException.class,
                () -> Pedido.crear(cliente, producto, 0));
    }

    @Test
    void crearPedidoConCantidadNegativaLanzaExcepcion() {
        assertThrows(PedidoInvalidoException.class,
                () -> Pedido.crear(cliente, producto, -2));
    }

    @Test
    void crearPedidoConCantidadMayorAlMaximoPermitidoLanzaExcepcion() {
        Producto conMuchoStock = Producto.crear("PRD-0002", "Cable HDMI", 5.0, 100);

        assertThrows(PedidoInvalidoException.class,
                () -> Pedido.crear(cliente, conMuchoStock, 11));
    }

    // ---------- Combinación cantidad / stock ----------

    @Test
    void crearPedidoConCantidadMayorAlStockLanzaStockInsuficiente() {
        assertThrows(StockInsuficienteException.class,
                () -> Pedido.crear(cliente, producto, 6));
    }

    @Test
    void stockInsuficienteEsUnTipoDePedidoInvalido() {
        assertThrows(PedidoInvalidoException.class,
                () -> Pedido.crear(cliente, producto, 6));
    }
}
