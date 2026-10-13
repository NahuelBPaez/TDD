package org.example.model;

import org.example.excepciones.ClienteInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    // ---------- Creación válida ----------

    @Test
    void crearClienteValidoConservaSusDatos() {
        Cliente cliente = Cliente.crear("Ana Pérez", "ana@correo.com", 30);

        assertEquals("Ana Pérez", cliente.getNombre());
        assertEquals("ana@correo.com", cliente.getEmail());
        assertEquals(30, cliente.getEdad());
    }

    @Test
    void crearClienteNormalizaEspaciosDelNombre() {
        Cliente cliente = Cliente.crear("  Ana Pérez  ", "ana@correo.com", 30);

        assertEquals("Ana Pérez", cliente.getNombre());
    }

    @Test
    void crearClienteAceptaEdadesLimite() {
        assertEquals(18, Cliente.crear("Ana", "ana@correo.com", 18).getEdad());
        assertEquals(120, Cliente.crear("Ana", "ana@correo.com", 120).getEdad());
    }

    // ---------- Nombre ----------

    @Test
    void crearClienteConNombreNuloLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear(null, "ana@correo.com", 30));
    }

    @Test
    void crearClienteConNombreVacioLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear("", "ana@correo.com", 30));
    }

    @Test
    void crearClienteConNombreSoloEspaciosLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear("     ", "ana@correo.com", 30));
    }

    @Test
    void crearClienteConNombreMuyCortoLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear("A", "ana@correo.com", 30));
    }

    @Test
    void crearClienteConNombreMuyLargoLanzaExcepcion() {
        String nombreLargo = "A".repeat(51);

        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear(nombreLargo, "ana@correo.com", 30));
    }

    // ---------- Email ----------

    @Test
    void crearClienteConEmailNuloLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear("Ana", null, 30));
    }

    @Test
    void crearClienteConEmailVacioLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear("Ana", "", 30));
    }

    @Test
    void crearClienteConEmailSinArrobaLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear("Ana", "anacorreo.com", 30));
    }

    @Test
    void crearClienteConEmailSinDominioLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear("Ana", "ana@", 30));
    }

    // ---------- Edad ----------

    @Test
    void crearClienteMenorDeEdadLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear("Ana", "ana@correo.com", 17));
    }

    @Test
    void crearClienteConEdadMayorAlMaximoLanzaExcepcion() {
        assertThrows(ClienteInvalidoException.class,
                () -> Cliente.crear("Ana", "ana@correo.com", 121));
    }
}
