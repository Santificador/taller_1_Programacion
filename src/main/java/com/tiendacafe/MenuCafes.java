package com.tiendacafe;

import java.util.Scanner;

/**
 * Clase que representa el submenú de gestión de cafés de la aplicación.
 * Permite al usuario realizar operaciones como dar de alta cafés,
 * listar el catálogo disponible o buscar cafés por variedad.
 *
 * Esta clase actúa como interfaz de consola y delega la lógica en la clase {@link GestorCafes}.
 *
 * @author Cristian
 * @version 1.0
 */

public class MenuCafes {

    /**
     * Muestra el submenú interactivo de gestión de cafés.
     * Permite al usuario seleccionar distintas opciones para operar sobre el catálogo:
     * dar de alta nuevos cafés, listar cafés existentes o buscar por variedad.
     *
     * El menú se ejecuta en bucle hasta que el usuario elige la opción de volver al menú principal.
     *
     * @param gestorCafes Instancia de {@link GestorCafes} que gestiona la lógica de los cafés.
     */


    public static void mostrarMenuCafes(GestorCafes gestorCafes){

        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        do {

            System.out.println("\n=== MENU DE GESTIÓN DE CAFÉS ===");
            System.out.println("1. Dar de alta ");
            System.out.println("2. Listar cafés");
            System.out.println("3. Buscar cafés por variedad");
            System.out.println("4. Volver al menú principal");
            System.out.println("Elige una opción(1 - 4): ");

            try{
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        gestorCafes.darAltaCafe();
                        break;
                    case 2:
                        gestorCafes.listarCafes();
                        break;
                    case 3:
                        gestorCafes.buscarCafePorVariedad();
                        break;
                    case 4:
                        System.out.println("Volviendo al menú principal....");
                        break;
                    default:
                        System.out.println("Opción no válida.Inténtalo de nuevo:");
                }

            }catch(NumberFormatException e){
                    System.out.println("\nEntrada inválida. Debes introducir un número del 1 al 4.");
                }


        }while(opcion != 4);
    }
}
