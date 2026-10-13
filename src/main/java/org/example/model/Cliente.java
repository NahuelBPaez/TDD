package org.example.model;

import org.example.excepciones.ClienteInvalidoException;

import java.util.regex.Pattern;

public class Cliente {

    private static final int NOMBRE_LONGITUD_MINIMA = 2;
    private static final int NOMBRE_LONGITUD_MAXIMA = 50;
    private static final int EDAD_MINIMA = 18;
    private static final int EDAD_MAXIMA = 120;
    private static final Pattern FORMATO_EMAIL =
            Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");

    private final String nombre;
    private final String email;
    private final int edad;

    private Cliente(String nombre, String email, int edad) {
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }

    public static Cliente crear(String nombre, String email, int edad) {
        String nombreNormalizado = validarNombre(nombre);
        validarEmail(email);
        validarEdad(edad);
        return new Cliente(nombreNormalizado, email, edad);
    }

    private static String validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ClienteInvalidoException("El nombre es obligatorio");
        }
        String normalizado = nombre.trim();
        if (normalizado.length() < NOMBRE_LONGITUD_MINIMA
                || normalizado.length() > NOMBRE_LONGITUD_MAXIMA) {
            throw new ClienteInvalidoException("El nombre debe tener entre "
                    + NOMBRE_LONGITUD_MINIMA + " y " + NOMBRE_LONGITUD_MAXIMA + " caracteres");
        }
        return normalizado;
    }

    private static void validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new ClienteInvalidoException("El email es obligatorio");
        }
        if (!FORMATO_EMAIL.matcher(email).matches()) {
            throw new ClienteInvalidoException("El email no tiene un formato válido");
        }
    }

    private static void validarEdad(int edad) {
        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            throw new ClienteInvalidoException("La edad debe estar entre "
                    + EDAD_MINIMA + " y " + EDAD_MAXIMA);
        }
    }

    public String getNombre() { return nombre; }

    public String getEmail() { return email; }

    public int getEdad() { return edad; }
}
