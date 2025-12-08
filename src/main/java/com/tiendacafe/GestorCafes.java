package com.tiendacafe;

import java.util.*;

/**
 * Clase que gestiona las operaciones relacionadas con los cafés disponibles en la tienda.
 * Permite dar de alta nuevos cafés, buscar por variedad y listar todo el catálogo.
 *
 * Las operaciones se realizan sobre una lista estática de objetos {@link Cafe}.
 *
 * @author Cristian
 * @version 1.0
 */

public class GestorCafes {

    private ArrayList<Cafe> listaCafes;

    public GestorCafes(ArrayList<Cafe> listaCafes) {
        this.listaCafes = listaCafes;
    }


    public ArrayList<Cafe> getListaCafes() {
        return listaCafes;
    }



    /**
     * Da de alta un nuevo café en el catálogo de la tienda.
     * Solicita al usuario los datos del café por consola: nombre, variedad, origen y precio.
     * Antes de añadirlo, verifica que no exista ya un café con el mismo nombre y variedad.
     * Si el café ya existe, muestra un mensaje y no lo añade.
     * Si es nuevo, lo agrega a la lista y confirma la operación.
     *
     * Usa {@link Scanner} para capturar datos desde la entrada estándar,
     * y {@link java.util.Locale#US} para permitir el uso de punto como separador decimal en el precio.
     */


    public void darAltaCafe(){
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);


        System.out.println("\n === NUEVO CAFÉ ===");
        System.out.println("\nNombre del café: ");
        String nombre = scanner.nextLine();

        System.out.println("\nVariedad del café: ");
        String variedad = scanner.nextLine();

        System.out.println("\nOrigen del café: ");
        String origen = scanner.nextLine();

        double precio = 0.0;
        while (true){

            System.out.println("\nPrecio (250 grs): ");
            String entrada = scanner.nextLine().replace(',','.').trim();
            try{
                precio = Double.parseDouble(entrada);
                if(precio < 0){
                    System.out.println("El precio no puede ser negativo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Introduce un número válido (ej: 5.25");;
            }
        }

        int stock = 0;
        while (true){
            System.out.println("\n¿Cuántas bolsas de café deseas añadir?: ");
            String entrada = scanner.nextLine().trim();
            try {
                stock = Integer.parseInt(entrada);
                if (stock < 0){
                    System.out.println("El stock no puede ser negativo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e ){

                System.out.println("Entrada no válida. Introduce un número entero.");
            }
        }


        for(Cafe cafe : listaCafes){
            if(cafe.getNombre().equalsIgnoreCase(nombre) &&
                    cafe.getVariedad().equalsIgnoreCase(variedad)){

                System.out.println("Ese café ya está registrado");
                return;
            }
        }

        Cafe nuevoCafe = new Cafe(nombre, variedad, origen, precio,stock);
        listaCafes.add(nuevoCafe);
        System.out.println("Café añadido correctamente.");

    }

    /**
     * Muestra un listado de los cafés disponibles en la tienda, ordenado según el criterio elegido por el usuario.
     * Las opciones de ordenación incluyen por nombre, variedad o precio.
     * La lista original no se modifica; se crea una copia temporal para mostrar los resultados.
     */

    public void listarCafes(){

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {

            System.out.println("\n=== LISTADO DE CAFÉS ===");
            System.out.println("\n1. Ordenar por nombre");
            System.out.println("\n2. Ordenar por variedad");
            System.out.println("\n3. Ordenar por precio");
            System.out.println("\n4. Volver al menú");
            System.out.println("\nElige una opción(1 - 4): ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            List<Cafe> copia = new ArrayList<>(listaCafes);

            switch (opcion){
                case 1:
                    copia.sort(Comparator.comparing(Cafe::getNombre));
                    System.out.println("\n--- Cafés ordenados por nombre ---");
                    break;
                case 2:
                    copia.sort(Comparator.comparing(Cafe::getVariedad));
                    System.out.println("\n--- Cafés ordenados por variedad");
                    break;
                case 3:
                    copia.sort(Comparator.comparing(Cafe::getPrecio));
                    System.out.println("\n--- Cafés ordenados por precio ---");
                    break;
                case 4:
                    System.out.println("Saliendo del listado de cafés...");
                    continue;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");

            }

            for (Cafe cafe : copia){
                System.out.println(cafe);
            }

        }while(opcion != 4);
    }

    /**
     * Busca y muestra los cafés que coinciden con la variedad introducida por el usuario.
     * La búsqueda no distingue entre mayúsculas y minúsculas.
     * Si no se encuentran coincidencias, se muestra un mensaje informativo.
     * Permite repetir la búsqueda varias veces hasta que el usuario decida salir.
     * Utiliza la entrada por consola para capturar la variedad a buscar.
     */

    public void buscarCafePorVariedad(){

        Scanner scanner = new Scanner(System.in);

        boolean encontrado = false;

        System.out.println("\nIntroduce la variedad: ");
        String variedad = scanner.nextLine();


        for(Cafe cafe: listaCafes){
            if(cafe.getVariedad().equalsIgnoreCase(variedad)){
                System.out.println(cafe);
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("No se encontraron cafés con la variedad indicada.");
        }
    }

}
