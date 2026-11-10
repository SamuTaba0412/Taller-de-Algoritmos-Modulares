import java.util.Scanner;
public class App {
    
    static Scanner sc = new Scanner(System.in);

    static final int MAX = 25;

    // Vectores
    static String[] codigo = new String[MAX];
    static String[] nombre = new String[MAX];
    static double[] asistencia = new double[MAX];
    static double[] definitiva = new double[MAX];
    static String[] estado = new String[MAX];

    // Matriz para las tres notas parciales
    static double[][] notas = new double[MAX][3];

    static int cantidad = 0;

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE CONTROL ACADEMICO =====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Mostrar estudiantes");
            System.out.println("3. Mostrar promedio general");
            System.out.println("4. Mostrar mejor y peor promedio");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {

                case 1:
                    registrarEstudiante();
                    break;

                case 2:
                    mostrarEstudiantes();
                    break;

                case 3:
                    mostrarPromedioGeneral();
                    break;

                case 4:
                    mostrarMejorYPeor();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);

    }

    // FUNCION PARA LEER NUMEROS ENTEROS
    static int leerEntero() {

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero valido: ");
            }
        }
    }

    // FUNCION PARA LEER NUMEROS DECIMALES
    static double leerDecimal() {

        while (true) {
            try {
                return Double.parseDouble(
                    sc.nextLine().replace(",", ".")
                );
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }

    // FUNCION PARA CALCULAR LA DEFINITIVA
    static double calcularDefinitiva(int posicion) {

        double suma = 0;

        for (int i = 0; i < 3; i++) {
            suma += notas[posicion][i];
        }

        return suma / 3;
    }

    // FUNCION PARA DETERMINAR EL ESTADO
    static String determinarEstado(double nota) {

        if (nota >= 3.0) {
            return "Aprobado";
        } else if (nota >= 2.0) {
            return "Habilita";
        } else {
            return "Reprueba";
        }
    }

    // PROCEDIMIENTO PARA REGISTRAR ESTUDIANTES
    static void registrarEstudiante() {

        if (cantidad >= MAX) {
            System.out.println("No se pueden registrar mas estudiantes.");
            return;
        }

        int posicion = cantidad;

        System.out.println("\n--- REGISTRO DE ESTUDIANTE ---");

        System.out.print("Codigo: ");
        codigo[posicion] = sc.nextLine();

        System.out.print("Nombre: ");
        nombre[posicion] = sc.nextLine();

        // Registro y validacion de las tres notas
        for (int i = 0; i < 3; i++) {

            do {
                System.out.print("Ingrese la nota parcial " + (i + 1) + ": ");
                notas[posicion][i] = leerDecimal();

                if (notas[posicion][i] < 0 ||
                    notas[posicion][i] > 5) {

                    System.out.println("Error: la nota debe estar entre 0.0 y 5.0.");
                }

            } while (notas[posicion][i] < 0 ||
                     notas[posicion][i] > 5);
        }

        // Registro y validacion de asistencia
        do {
            System.out.print("Porcentaje de asistencia (0-100): ");
            asistencia[posicion] = leerDecimal();

            if (asistencia[posicion] < 0 ||
                asistencia[posicion] > 100) {

                System.out.println("Error: la asistencia debe estar entre 0 y 100.");
            }

        } while (asistencia[posicion] < 0 ||
                 asistencia[posicion] > 100);

        // Calcular definitiva y estado
        definitiva[posicion] = calcularDefinitiva(posicion);

        estado[posicion] = determinarEstado(definitiva[posicion]);

        cantidad++;

        System.out.println("\nEstudiante registrado correctamente.");
        System.out.printf("Definitiva: %.2f%n", definitiva[posicion]);
        System.out.println("Estado: " + estado[posicion]);
    }

    // PROCEDIMIENTO PARA MOSTRAR ESTUDIANTES
    static void mostrarEstudiantes() {

        if (cantidad == 0) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        System.out.println("\n--- LISTADO DE ESTUDIANTES ---");

        for (int i = 0; i < cantidad; i++) {

            System.out.println("\nCodigo: " + codigo[i]);
            System.out.println("Nombre: " + nombre[i]);

            for (int j = 0; j < 3; j++) {
                System.out.printf("Nota parcial %d: %.2f%n",
                                  j + 1, notas[i][j]);
            }

            System.out.printf("Asistencia: %.2f%%%n", asistencia[i]);
            System.out.printf("Definitiva: %.2f%n", definitiva[i]);
            System.out.println("Estado: " + estado[i]);
        }
    }

    // PROCEDIMIENTO PARA MOSTRAR EL PROMEDIO GENERAL
    static void mostrarPromedioGeneral() {

        if (cantidad == 0) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        double suma = 0;

        for (int i = 0; i < cantidad; i++) {
            suma += definitiva[i];
        }

        double promedio = suma / cantidad;

        System.out.printf("\nPromedio general del grupo: %.2f%n", promedio);
    }

    // PROCEDIMIENTO PARA IDENTIFICAR MEJOR Y PEOR PROMEDIO
    static void mostrarMejorYPeor() {

        if (cantidad == 0) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        int mejor = 0;
        int peor = 0;

        for (int i = 1; i < cantidad; i++) {

            if (definitiva[i] > definitiva[mejor]) {
                mejor = i;
            }

            if (definitiva[i] < definitiva[peor]) {
                peor = i;
            }
        }

        System.out.println("\n--- MEJOR PROMEDIO ---");
        System.out.println("Estudiante: " + nombre[mejor]);
        System.out.printf("Definitiva: %.2f%n", definitiva[mejor]);

        System.out.println("\n--- PEOR PROMEDIO ---");
        System.out.println("Estudiante: " + nombre[peor]);
        System.out.printf("Definitiva: %.2f%n", definitiva[peor]);
    }

}