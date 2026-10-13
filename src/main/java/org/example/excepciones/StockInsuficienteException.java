package org.example.excepciones;

public class StockInsuficienteException extends PedidoInvalidoException {

    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
