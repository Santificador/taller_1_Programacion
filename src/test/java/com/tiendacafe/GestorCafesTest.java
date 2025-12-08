package com.tiendacafe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Clase de pruebas unitarias para la clase {@link GestorCafes}.
 * Utiliza simulación de entrada por consola para verificar el comportamiento de los métodos interactivos.
 *
 * Se emplea JUnit 5 para realizar pruebas de alta de cafés y validaciones de mensajes por salida estándar.
 *
 * @author Cristian
 * @version 1.0
 */


public class GestorCafesTest {

    private GestorCafes gestorCafes;

    @BeforeEach
    public void setUp() {
        gestorCafes = new GestorCafes(new ArrayList<>());
    }

    /**
     * Verifica que el mé_todo {@link GestorCafes#darAltaCafe()} añade correctamente un nuevo café
     * cuando se introducen datos válidos por consola.
     *
     * Simula la entrada del usuario usando {@link ByteArrayInputStream} y comprueba que la salida
     * contenga el mensaje de confirmación.
     */


    @Test
    public void testDarAltaCafe(){

        gestorCafes.getListaCafes().clear();

        String entradaSimulada = "Kenya AA\nArábica\nKenia\n10.5\n5\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaCapturada));

        gestorCafes.darAltaCafe();

        assertTrue(salidaCapturada.toString().contains("Café añadido correctamente"));

    }

    /**
     * Verifica que el mé_todo {@link GestorCafes#darAltaCafe()} detecta correctamente cafés duplicados
     * cuando ya existe uno con el mismo nombre y variedad.
     * Simula la entrada del usuario con datos repetidos y comprueba que se muestre el mensaje de advertencia.
     */

    @Test
    public void testEsCafeDuplicado(){

        gestorCafes.getListaCafes().add(new Cafe("Colombia",
                "Arábica","Colombia", 9.5, 4));

        String entradaSimulada = "Colombia\nArábica\nColombia\n9.5\n5\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaCapturada));

        gestorCafes.darAltaCafe();

        assertTrue(salidaCapturada.toString().contains("Ese café ya está registrado"));
    }

    /**
     * Verifica que el mé_todo {@link GestorCafes#listarCafes()} muestra correctamente
     * el contenido de la lista de cafés.
     *
     * Se preparan cafés de prueba y se comprueba que la salida por consola
     * contiene el nombre esperado.
     */


    @Test
    public void testListarCafes(){

        gestorCafes.getListaCafes().add(new Cafe("Colombia Supremo",
                "Arábica", "Colombia", 9.5, 5));

        gestorCafes.getListaCafes().add(new Cafe("Santos",
                "Robusta", "Brasil", 8.0, 5));

        String entradaSimulada = "1\n4\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaCapturada));

        gestorCafes.listarCafes();

        assertTrue(salidaCapturada.toString().contains("Colombia Supremo"));

    }

    /**
     * Verifica que el mé_todo {@link GestorCafes#buscarCafePorVariedad()} muestra correctamente
     * los cafés que coinciden con una variedad introducida por consola.
     *
     * Se simula la entrada de la variedad "Arábica" y se comprueba que la salida
     * contiene cafés con esa variedad.
     */

    @Test
    public void testBuscarCafePorVariedad(){

        gestorCafes.getListaCafes().add(new Cafe("Nicaragua Gourmet",
                "Arábica", "Nicaragua", 9.0, 3));
        gestorCafes.getListaCafes().add(new Cafe("Colombia Supremo",
                "Arábica", "Colombia", 9.5, 4));
        gestorCafes.getListaCafes().add(new Cafe("Vietnam Robusto",
                "Robusta", "Vietnam", 8.2, 3));

        String entradaSimulada = "Arábica\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaCapturada));

        gestorCafes.buscarCafePorVariedad();

        assertTrue(salidaCapturada.toString().contains("Arábica"));


    }

    /**
     * Verifica que el mé_todo {@link GestorCafes#buscarCafePorVariedad()} muestra el mensaje correcto
     * cuando no se encuentran cafés con la variedad introducida.
     *
     * Se simula la entrada de una variedad inexistente ("Excelsa") y se comprueba la respuesta del sistema.
     */

    @Test
    public void testBuscarCafePorVariedadInexistente(){
        String entradaSimulada = "Excelsa\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaCapturada));

        gestorCafes.buscarCafePorVariedad();

        assertTrue(salidaCapturada.toString().contains("No se encontraron cafés con la variedad indicada."));
    }
}
