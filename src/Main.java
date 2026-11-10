import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1:
                    InventarioCafeteria.ejecutar(sc);
                    break;
                case 0:
                    System.out.println("Saliendo del programa. Todo bien.");
                    break;
                default:
                    System.out.println("Opción Inválida, vuelva a intentarlo.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // MNos muestra el menu principal
    public static void mostrarMenu() {
        System.out.println();
        System.out.println(" MENÚ PRINCIPAL ");
        System.out.println("1. Inventario de la cafetería universitaria");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    
    public static int leerOpcion(Scanner sc) {
        String texto = sc.nextLine();
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
