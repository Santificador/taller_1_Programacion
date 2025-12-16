package com.tiendacafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase {@link GestorClientes}.
 * Verifica el funcionamiento de los métodos relacionados con la gestión de clientes,
 * incluyendo el alta, baja y control de duplicados mediante entrada simulada por consola.
 *
 * Utiliza JUnit 5 para validar los resultados esperados de cada operación.
 */



public class GestorClientesTest {

    private GestorClientes gestor;

    @BeforeEach
    void setUp(){
        gestor = new GestorClientes(new ArrayList<>());

    }

    /**
     * Verifica el funcionamiento del mé_todo agregarCliente() con entrada simulada por consola.
     *
     * El test realiza dos acciones:
     * - Añade un cliente por primera vez y comprueba que el resultado es true.
     * - Intenta añadir el mismo cliente (mismo DNI) y comprueba que el mé_todo devuelve false,
     *   validando que no se permiten duplicados.
     */


    @Test
    public void testAgregarClienteDesdeConsola() {
        String entradaSimulada = "12345678A\nCristian\n612345678\ncris@email.com\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        boolean resultado = gestor.agregarCliente();
        assertTrue(resultado);

        System.out.println("\nPrueba de cliente ya registrado:");

        String entradaRepetida = "12345678A\nCristian\n612345678\ncris@email.com\n";
        System.setIn(new ByteArrayInputStream(entradaRepetida.getBytes()));
        boolean resultadoDuplicado = gestor.agregarCliente();
        assertFalse(resultadoDuplicado);

    }

    /**
     * Verifica que el mé_todo eliminarCliente() funciona correctamente cuando el cliente existe.
     *
     * Primero se simula el alta de un cliente válido. Luego se simula la eliminación
     * del mismo cliente por su DNI. Se espera que el mé_todo devuelva true.
     */


    @Test
    public void testEliminarCliente(){
        String datosAlta = "12345678A\nCristian\n612345678\ncris@email.com\n";
        System.setIn(new ByteArrayInputStream(datosAlta.getBytes()));
        gestor.agregarCliente();

        String eliminarCliente = "12345678A\n";
        System.setIn(new ByteArrayInputStream(eliminarCliente.getBytes()));
        boolean resultado = gestor.eliminarCliente();

        assertTrue(resultado);
    }

    /**
     * Verifica que el mé_todo eliminarCliente() devuelve false al intentar eliminar
     * un cliente cuyo DNI no está registrado en la lista.
     *
     * Simula la entrada de un DNI inexistente por consola y comprueba que
     * no se realiza la eliminación.
     */


    @Test
    public void testEliminarClienteInexistente() {
        String entradaSimulada = "00000000X\n"; // DNI que no ha sido dado de alta
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        boolean resultado = gestor.eliminarCliente();
        assertFalse(resultado);
    }

    /**
     * Verifica que la opción de modificar el nombre de un cliente funciona correctamente.
     *
     * Simula la entrada por consola de un cliente existente, selecciona la opción 1 del menú de modificación
     * y cambia su nombre. Luego se comprueba que el nombre del cliente ha sido actualizado correctamente.
     */
    @Test
    public void testModificarNombreCliente(){
        String datosAlta = "12345678A\nCristian\n612345678\ncris@email.com\n";
        System.setIn(new ByteArrayInputStream(datosAlta.getBytes()));
        gestor.agregarCliente();


        String modificarNombre = "12345678A\n1\nPepe\n4\n";
        System.setIn(new ByteArrayInputStream(modificarNombre.getBytes()));
        gestor.modificarCliente();

        Cliente cliente = gestor.getListaClientes().getFirst();
        assertEquals("PEPE",cliente.getNombre());
    }

    /**
     * Verifica que la opción de modificar el teléfono de un cliente funciona correctamente.
     *
     * Simula un alta previa del cliente, luego selecciona la opción 2 del menú de modificación
     * e introduce un nuevo número de teléfono. Finalmente comprueba que el dato ha sido actualizado.
     */
    @Test
    public void testModificarTelefonoCliente(){
        String datosAlta = "12345678A\nCristian\n612345678\ncris@email.com\n";
        System.setIn(new ByteArrayInputStream(datosAlta.getBytes()));
        gestor.agregarCliente();

        String modificarTelefono = "12345678A\n2\n626451692\n4\n";
        System.setIn(new ByteArrayInputStream(modificarTelefono.getBytes()));
        gestor.modificarCliente();

        Cliente cliente  = gestor.getListaClientes().getFirst();
        assertEquals("626451692",cliente.getTelefono());
    }

    /**
     * Verifica que la opción de modificar el email de un cliente funciona correctamente.
     *
     * Simula un alta previa del cliente, selecciona la opción 3 del menú de modificación
     * e introduce un nuevo email. Finalmente, comprueba que el campo ha sido actualizado correctamente.
     */
    @Test
    public void testModificarEmailCliente(){
        String datosAlta = "12345678A\nCristian\n612345678\ncris@email.com\n";
        System.setIn(new ByteArrayInputStream(datosAlta.getBytes()));
        gestor.agregarCliente();

        String modificarEmail = "12345678A\n3\nlaura@email.com\n4\n";
        System.setIn(new ByteArrayInputStream(modificarEmail.getBytes()));
        gestor.modificarCliente();

        Cliente cliente = gestor.getListaClientes().getFirst();
        assertEquals("laura@email.com", cliente.getEmail());
    }

    /**
     * Verifica que el método buscarClientePorDni() localiza correctamente a un cliente registrado.
     *
     * Simula el alta de un cliente y posteriormente su búsqueda por DNI mediante entrada simulada.
     * Comprueba que los datos del cliente devuelto coinciden con los originales.
     */


    @Test
    public void testBuscarClientePorDni(){
        String datosAlta = "12345678A\nCristian\n612345678\ncris@email.com\n";
        System.setIn(new ByteArrayInputStream(datosAlta.getBytes()));
        gestor.agregarCliente();

        String busquedaCliente = "12345678A";
        System.setIn(new ByteArrayInputStream(busquedaCliente.getBytes()));

        Cliente clienteEncontrado = gestor.buscarClientePorDni();

        assertEquals("CRISTIAN",clienteEncontrado.getNombre());
        assertEquals("612345678",clienteEncontrado.getTelefono());
        assertEquals("cris@email.com",clienteEncontrado.getEmail());

    }

    /**
     * Verifica que el mé_todo buscarClientePorDni() devuelve null cuando se busca
     * un DNI que no está registrado en la lista de clientes.
     *
     * Simula la entrada de un DNI inexistente por consola y comprueba que no se encuentra ningún cliente.
     */

    @Test
    public void testBuscarClienteInexistentePorDni(){
        String entradaInexistente = "45678901B";
        System.setIn(new ByteArrayInputStream(entradaInexistente.getBytes()));

        Cliente cliente = gestor.buscarClientePorDni();

        assertNull(cliente);
    }

    /**
     * Verifica que el método listarClientes() imprime correctamente los datos de los clientes registrados.
     *
     * Simula el alta de un cliente y captura la salida por consola tras llamar al método.
     * Comprueba que el nombre del cliente aparece en la salida esperada.
     */

    @Test
    public void testListarCliente(){
        String datosAlta = "12345678A\nCristian\n612345678\ncris@email.com\n";
        System.setIn(new ByteArrayInputStream(datosAlta.getBytes()));
        gestor.agregarCliente();

        ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
        PrintStream salidaOriginal = System.out;
        System.setOut(new PrintStream(salidaConsola));


        gestor.listarClientes();
        System.setOut(salidaOriginal);
        String salida = salidaConsola.toString();

        assertTrue(salida.contains("CRISTIAN"));
    }

}
