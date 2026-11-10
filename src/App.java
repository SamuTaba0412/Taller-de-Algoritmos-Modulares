import java.util.Scanner;

public class InventarioCafeteria {

    static final int MAX = 20;
    static final String[] CATEGORIAS = {"Bebidas", "Panadería", "Snacks"};

    static int[] codigos = new int[MAX];
    static String[] nombres = new String[MAX];
    static double[] precios = new double[MAX];
    static int[] cantidades = new int[MAX];
    static String[] categorias = new String[MAX];
    static int total = 0;
    static double totalDia = 0;

    public static void ejecutar(Scanner sc) {
        int op;
        do {
            System.out.println("\n CAFETERÍA UNIVERSITARIA");
            System.out.println("1. Registrar producto\n2. Realizar venta\n3. Actualizar inventario");
            System.out.println("4. Productos agotados\n5. Total vendido del día\n6. Ver inventario\n0. Volver");
            op = (int) leerNumero(sc, "Seleccione una opción: ");

            switch (op) {
                case 1: registrar(sc); break;
                case 2: vender(sc); break;
                case 3: actualizar(sc); break;
                case 4: mostrarAgotados(); break;
                case 5: System.out.println("Total vendido hoy: $" + totalDia); break;
                case 6: mostrarInventario(); break;
                case 0: break;
                default: System.out.println("Opción Inválida.");
            }
        } while (op != 0);
    }

    static void registrar(Scanner sc) {
        if (total == MAX) {
            System.out.println("Inventario lleno (máximo " + MAX + " productos).");
            return;
        }
        int codigo = (int) leerNumero(sc, "Código: ");
        if (buscar(codigo) != -1) {
            System.out.println("Ya existe un producto con ese código.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        double precio = leerNumero(sc, "Precio por unidad: ");
        int cantidad = (int) leerNumero(sc, "Cantidad disponible: ");
        int cat = (int) leerNumero(sc, "Categoría (1. Bebidas, 2. Panadería, 3. Snacks): ");

        if (precio <= 0 || cantidad < 0 || cat < 1 || cat > 3) {
            System.out.println("Datos inválidos. Producto no registrado.");
            return;
        }

        codigos[total] = codigo;
        nombres[total] = nombre;
        precios[total] = precio;
        cantidades[total] = cantidad;
        categorias[total] = CATEGORIAS[cat - 1];
        total++;
        System.out.println("Producto registrado, quedo melo.");
    }

    static void vender(Scanner sc) {
        int pos = buscar((int) leerNumero(sc, "Código del producto: "));
        if (pos == -1) {
            System.out.println("El producto no existe.");
            return;
        }
        int cant = (int) leerNumero(sc, "Cantidad a vender: ");

    public static void main(String[] args) throws Exception {
        
        if (cant <= 0 || cant > cantidades[pos]) {
            System.out.println("Cantidad inválida o stock insuficiente (disponible: " + cantidades[pos] + ").");
            return;
        }

        cantidades[pos] -= cant;
        totalDia += cant * precios[pos];
        System.out.println("Venta realizada: " + cant + " x " + nombres[pos] + " = $" + (cant * precios[pos]));
    }

    static void actualizar(Scanner sc) {
        int pos = buscar((int) leerNumero(sc, "Código del producto: "));
        if (pos == -1) {
            System.out.println("El producto no existe.");
            return;
        }
        int agregar = (int) leerNumero(sc, "Cantidad a agregar (stock actual " + cantidades[pos] + "): ");
        if (agregar <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return;
        }
        cantidades[pos] += agregar;
        System.out.println("Nuevo stock: " + cantidades[pos]);
    }

    static void mostrarAgotados() {
        boolean hay = false;
        for (int i = 0; i < total; i++) {
            if (cantidades[i] == 0) {
                System.out.println(codigos[i] + " - " + nombres[i] + " (" + categorias[i] + ")");
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay productos agotados.");
        }
    }

    static void mostrarInventario() {
        if (total == 0) {
            System.out.println("No hay productos registrados.");
        }
        for (int i = 0; i < total; i++) {
            System.out.println(codigos[i] + " | " + nombres[i] + " | $" + precios[i]
                    + " | " + cantidades[i] + " und | " + categorias[i]);
        }
    }

    // Devuelve la posición del producto o -1 si no existe
    static int buscar(int codigo) {
        for (int i = 0; i < total; i++) {
            if (codigos[i] == codigo) {
                return i;
            }
        }
        return -1;
    }

    // Muestra el mensaje y lee un número si escriben algo inválido devuelve -1
    static double leerNumero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        try {
            return Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
