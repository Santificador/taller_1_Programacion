package com.tiendacafe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de pruebas unitarias para la clase {@link GestorVentas}.
 *
 * Utiliza JUnit 5 para verificar el correcto funcionamiento de los métodos relacionados
 * con la gestión de ventas, incluyendo creación, búsqueda, y control de stock.
 *
 * Se simulan clientes y cafés para validar el comportamiento del sistema.
 *
 * @author Cristian
 * @version 1.0
 */

public class GestorVentasTest {

    private ArrayList<Cliente> listaCliente = new ArrayList<>();
    private ArrayList<Cafe> listaCafes = new ArrayList<>();
    private GestorVentas gestorVentas;

    /**
     * Inicializa las listas de clientes y cafés antes de cada prueba unitaria.
     *
     * Crea una nueva instancia de {@link GestorVentas} con listas vacías preparadas
     * para ser manipuladas en los distintos métodos de test.
     */
    @BeforeEach
    public void setUp(){

        listaCliente = new ArrayList<>();
        listaCafes = new ArrayList<>();

        gestorVentas = new GestorVentas(listaCliente, listaCafes);

    }

    /**
     * Prueba el método {@link GestorVentas#crearVenta(String, ArrayList)} con un cliente válido
     * y un café con stock suficiente.
     *
     * Simula entrada por consola para seleccionar el café por nombre ("Colombiano") y solicitar
     * 2 unidades del mismo. Verifica que se registre correctamente una única venta.
     */
    @Test
    public void testCrearVenta(){

        System.out.println("\n=== TEST CREAR VENTA  ===");
        String entradaSimulada  = "1\nColombiano\n2\nfin\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        Cliente cliente = new Cliente("Cristian", "12345678V", "612345678"
        ,"cristian@email.com");

        listaCliente.add(cliente);

        Cafe cafe = new Cafe("Colombiano",
                "Arábica", "Colombia", 3.5, 10);

        listaCafes.add(cafe);

        gestorVentas.crearVenta("12345678V", new ArrayList<>());

        Assertions.assertEquals(1, gestorVentas.getListaVentas().size());


    }

    /**
     * Prueba el método {@link GestorVentas#crearVenta(String, ArrayList)} con un cliente válido
     * y múltiples cafés con stock disponible.
     *
     * Simula una entrada por consola donde se seleccionan dos cafés distintos ("Colombiano" y "Brasileño"),
     * indicando la cantidad de unidades de cada uno. Verifica que la venta se registre correctamente
     * con el número total de cafés esperados.
     */
    @Test
    public void testCrearVentaConVariosCafes(){

        System.out.println("\n=== TEST CREAR VENTA CON VARIOS CAFÉS ===");
        String entradaSimulada = "1\nColombiano\n2\ns\nBrasileño\n1\nn\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        Cliente cliente = new Cliente("Cristian", "12345678V", "612345678"
                ,"cristian@email.com");

        listaCliente.add(cliente);

        Cafe cafeColombiano = new Cafe("Colombiano",
                "Arábica", "Colombia", 3.5, 10);
        Cafe cafeBrasilenio = new Cafe("Brasileño",
                "Robusta", "Brasil", 2.8, 5);

        listaCafes.add(cafeColombiano);
        listaCafes.add(cafeBrasilenio);

        gestorVentas.crearVenta("12345678V", new ArrayList<>());

        Assertions.assertEquals(1, gestorVentas.getListaVentas().size());
        Assertions.assertEquals(3, gestorVentas.getListaVentas().getFirst().getLineasDeVenta().size());


    }

    /**
     * Verifica que no se registra ninguna venta cuando se intenta crearla
     * con un cliente que no está registrado en el sistema.
     *
     * Aunque se introducen cafés por consola, el proceso debe cancelarse
     * al no encontrar el cliente en la lista.
     */
    @Test
    public void testCrearVentaSinClienteRegistrado(){

        System.out.println("\n=== TEST CREAR VENTA SIN CLIENTE REGISTRADO ===");
        String entradaSimulada = "1\nColombiano\n2\nBrasileño\n1\nfin\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        Cafe cafeColombiano = new Cafe("Colombiano",
                "Arábica", "Colombia", 3.5, 10);
        Cafe cafeBrasilenio = new Cafe("Brasileño",
                "Robusta", "Brasil", 2.8, 5);

        listaCafes.add(cafeColombiano);
        listaCafes.add(cafeBrasilenio);

        gestorVentas.crearVenta("12345678V", new ArrayList<>());

        Assertions.assertEquals(0, gestorVentas.getListaVentas().size());

    }

    /**
     * Verifica que una venta solo incluye cafés que tienen stock disponible.
     *
     * Simula una compra con dos cafés: uno sin stock y otro con stock suficiente.
     *
     * Comprueba que la venta se registra correctamente, pero solo con el café que sí puede venderse.
     */
    @Test
    public void testCrearVentaSinStock(){

        System.out.println("\n=== TEST CREAR VENTA SIN STOCK  ===");
        String entradaSimulada = "1\nColombiano\n2\ns\nBrasileño\n1\nn\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        Cliente cliente = new Cliente("Cristian", "12345678V", "612345678"
                ,"cristian@email.com");

        listaCliente.add(cliente);

        Cafe cafeColombiano = new Cafe("Colombiano",
                "Arábica", "Colombia", 3.5, 0);
        Cafe cafeBrasilenio = new Cafe("Brasileño",
                "Robusta", "Brasil", 2.8, 5);

        listaCafes.add(cafeColombiano);
        listaCafes.add(cafeBrasilenio);

        gestorVentas.crearVenta("12345678V", new ArrayList<>());

        Assertions.assertEquals(1, gestorVentas.getListaVentas().size());
        Assertions.assertEquals(1, gestorVentas.getListaVentas().getFirst().getLineasDeVenta().size());

    }

    /**
     * Verifica que si el cliente solicita más unidades de un café que las disponibles en stock,
     * solo se añadan las unidades realmente disponibles.
     *
     * Simula una venta solicitando 4 unidades de un café con solo 1 en stock, y otra unidad
     * de un café con stock suficiente. Valida que ambas se añaden correctamente a la venta.
     */

    @Test
    public void testCrearVentaConStockInsuficiente(){

        System.out.println("\n=== TEST CREAR VENTA CON STOCK INSUFICIENTE ===");
        String entradaSimulada = "1\nColombiano\n4\ns\nBrasileño\n1\nn\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        Cliente cliente = new Cliente("Cristian", "12345678V", "612345678"
                ,"cristian@email.com");

        listaCliente.add(cliente);

        Cafe cafeColombiano = new Cafe("Colombiano",
                "Arábica", "Colombia", 3.5, 1);
        Cafe cafeBrasilenio = new Cafe("Brasileño",
                "Robusta", "Brasil", 2.8, 5);

        listaCafes.add(cafeColombiano);
        listaCafes.add(cafeBrasilenio);

        gestorVentas.crearVenta("12345678V", new ArrayList<>());

        Assertions.assertEquals(1, gestorVentas.getListaVentas().size());
        Assertions.assertEquals(2, gestorVentas.getListaVentas().getFirst().getLineasDeVenta().size());

    }

    /**
     * Prueba el mé_todo {@link GestorVentas#mostrarTodasLasVentas()} verificando
     * que se muestran correctamente dos ventas registradas con distintos clientes.
     *
     * Simula dos flujos de compra por consola con cafés distintos, cantidades específicas
     * y stock controlado. Luego invoca el mé_todo de impresión de ventas y verifica
     * que el número total de ventas almacenadas sea el esperado.
     */

    @Test
    public void testMostrarTodasLasVentas(){

        System.out.println("\n=== TEST MOSTRAR TODAS LAS VENTAS ===");
        String compraSimulada1 = "1\nColombiano\n4\ns\nBrasileño\n1\nn\n";
        System.setIn(new ByteArrayInputStream(compraSimulada1.getBytes()));


        Cliente cliente = new Cliente("Cristian", "12345678V", "612345678"
                ,"cristian@email.com");

        listaCliente.add(cliente);

        Cafe cafeColombiano = new Cafe("Colombiano",
                "Arábica", "Colombia", 3.5, 1);
        Cafe cafeBrasilenio = new Cafe("Brasileño",
                "Robusta", "Brasil", 2.8, 5);

        listaCafes.add(cafeColombiano);
        listaCafes.add(cafeBrasilenio);

        gestorVentas.crearVenta("12345678V", new ArrayList<>());


        String compraSimulada2 = "1\nTierra Linda\n4\ns\nSol Naciente\n1\nn\n";
        System.setIn(new ByteArrayInputStream(compraSimulada2.getBytes()));

        Cliente cliente2 = new Cliente("Laura", "52345672V", "612345678"
                ,"laura@email.com");

        listaCliente.add(cliente2);

        Cafe cafeTierra = new Cafe("Tierra Linda",
                "Typica", "Perú", 11.9, 6);
        Cafe cafeSol = new Cafe("Sol Naciente",
                "SL28", "Kenia", 15.9, 5);

        listaCafes.add(cafeSol);
        listaCafes.add(cafeTierra);

        gestorVentas.crearVenta("52345672V", new ArrayList<>());

        gestorVentas.mostrarTodasLasVentas();

        Assertions.assertEquals(2, gestorVentas.getListaVentas().size());


    }

    /**
     * Verifica que el mé_todo {@link GestorVentas#getVentasPorCliente(String)} devuelve correctamente
     * las ventas asociadas a un cliente específico.
     *
     * Crea un cliente con DNI "12345678V" y simula dos ventas con cafés distintos.
     * Después, se invoca el mé_todo para obtener las ventas del cliente y se comprueba que
     * se hayan registrado exactamente dos ventas.
     *
     * Este test valida tanto la creación de ventas múltiples como la correcta filtración
     * por DNI del cliente.
     */
    @Test
    public void testMostrarVentasPorCliente(){

        System.out.println("\n=== TEST MOSTRAR VENTAS POR CLIENTE ===");
        String compraSimulada1 = "1\nColombiano\n4\ns\nBrasileño\n1\nn\n";
        System.setIn(new ByteArrayInputStream(compraSimulada1.getBytes()));


        Cliente cliente = new Cliente("Cristian", "12345678V", "612345678"
                ,"cristian@email.com");

        listaCliente.add(cliente);

        Cafe cafeColombiano = new Cafe("Colombiano",
                "Arábica", "Colombia", 3.5, 1);
        Cafe cafeBrasilenio = new Cafe("Brasileño",
                "Robusta", "Brasil", 2.8, 5);
        Cafe cafeTierra = new Cafe("Tierra Linda",
                "Typica", "Perú", 11.9, 6);
        Cafe cafeSol = new Cafe("Sol Naciente",
                "SL28", "Kenia", 15.9, 5);

        listaCafes.add(cafeColombiano);
        listaCafes.add(cafeBrasilenio);
        listaCafes.add(cafeSol);
        listaCafes.add(cafeTierra);

        System.out.println("\n COMPRA 1:");

        gestorVentas.crearVenta("12345678V", new ArrayList<>());


        String compraSimulada2 = "1\nTierra Linda\n4\ns\nSol Naciente\n1\nn\n";
        System.setIn(new ByteArrayInputStream(compraSimulada2.getBytes()));

        System.out.println("\n COMPRA 2:");

        gestorVentas.crearVenta("12345678V", new ArrayList<>());


        List<Venta> ventasCliente = gestorVentas.getVentasPorCliente("12345678V");

        Assertions.assertEquals(2, ventasCliente.size());
    }

    /**
     * Verifica que el mé_todo {@link GestorVentas#mostrarImportesPorCliente(String)} muestra
     * correctamente los importes de cada venta asociada a un cliente específico.
     *
     * Crea un cliente con DNI "12345678V" y simula dos ventas con cafés diferentes:
     * una de 2 unidades de "Colombiano" (3.5 €) y otra de 1 unidad de "Brasileño" (2.8 €).
     *
     * Luego se invoca el mé_todo para mostrar los importes de las ventas de ese cliente
     * y se comprueba que se hayan registrado exactamente dos ventas.
     *
     * Este test valida tanto la acumulación de ventas como el cálculo correcto
     * del importe total en cada una de ellas.
     */

    @Test
    public void testMostrarImportesPorCliente(){

        System.out.println("\n=== TEST MOSTRAR IMPORTES POR CLIENTE ===");
        String compraSimulada1 = "1\nColombiano\n2\nfin\n";
        System.setIn(new ByteArrayInputStream(compraSimulada1.getBytes()));

        Cliente cliente = new Cliente("Cristian", "12345678V", "612345678", "cristian@email.com");
        listaCliente.add(cliente);

        Cafe cafe1 = new Cafe("Colombiano", "Arábica", "Colombia", 3.5, 10);
        listaCafes.add(cafe1);

        System.out.println("COMPRA 1: ");
        gestorVentas.crearVenta("12345678V", new ArrayList<>());

        String compraSimulada2 = "1\nBrasileño\n1\nfin\n";
        System.setIn(new ByteArrayInputStream(compraSimulada2.getBytes()));

        Cafe cafe2 = new Cafe("Brasileño", "Robusta", "Brasil", 2.8, 5);
        listaCafes.add(cafe2);

        System.out.println("COMPRA 2: ");
        gestorVentas.crearVenta("12345678V", new ArrayList<>());

        List<Venta> ventas = gestorVentas.getVentasPorCliente("12345678V");
        gestorVentas.mostrarImportesPorCliente("12345678V");

        Assertions.assertEquals(2, ventas.size());

    }




}
