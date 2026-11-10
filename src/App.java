import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    static final int MAX = 25;

    static String[] codigo = new String[MAX];
    static String[] nombre = new String[MAX];
    static double[] asistencia = new double[MAX];
    static double[] definitiva = new double[MAX];
    static String[] estado = new String[MAX];

    static double[][] notas = new double[MAX][3];

    static int cantidad = 0;

    public static void main(String[] args) {
        menuCafeteria();
    }

    /* EJERCICIO #1: Sistema de control de turnos para una IPS*/
    public static void menuIps() {
        int opcion = 0, contPacientes = 0;

        Object[][] pacientes = new Object[30][5];

        do {
            System.out.println(
                    "\nBienvenido al sistema de control por turnos de la IPS!\nPor favor, seleccione una opcion:\n1. Registrar pacientes.\n2. Mostrar listado general.\n3. Buscar paciente por documento.\n4. Mostrar pacientes por tipo de atencion.\n5. Identificar paciente con mayor prioridad.\n6. Salir.");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    if (contPacientes >= 30)
                        System.out.println("\nYa no hay cupos disponibles.");
                    else {
                        pacientes[contPacientes] = registrarPaciente(pacientes, contPacientes);
                        System.out.println("\nPaciente ingresado con exito!");
                        contPacientes++;
                    }
                    break;
                case 2:
                    listarPacientes(pacientes, contPacientes);
                    break;
                case 3:
                    if (contPacientes > 0) {
                        String nDocumento;

                        System.out.print("\nIngrese el numero de documento a buscar: ");
                        nDocumento = sc.nextLine();

                        Object[] paciente = buscarPaciente(pacientes, nDocumento, contPacientes);

                        if (paciente != null) {
                            System.out.println("\n===================================");
                            System.out.println("Paciente Encontrado:");
                            System.out.println("Numero de documento: " + paciente[0]);
                            System.out.println("Nombre completo: " + paciente[1]);
                            System.out.println("Edad: " + paciente[2]);
                            System.out.println("Tipo de Atencion: " + paciente[3]);
                            System.out.println("Nivel de prioridad: " + paciente[4]);
                            System.out.println("===================================");
                        } else
                            System.out.println("\nPaciente no encontrado.");
                    } else
                        System.out.println("\nNo hay pacientes ingresados.");
                    break;
                case 4:
                    contarPacientesTipo(pacientes, contPacientes);
                    break;
                case 5:
                    determinarPacientePrioritario(pacientes, contPacientes);
                    break;
                case 6:
                    System.out.println("\nMuchas gracias por usar nuestros servicios");
                    break;
                default:
                    System.out.println("\nOpcion no valida.");
                    break;
            }
        } while (opcion != 6);
    }

    public static Object[] registrarPaciente(Object[][] pacientes, int contPacientes) {
        String nDocumento, nombre, tipoAtencion;
        int edad, nivelPrioridad;
        Object[] paciente = new Object[5];

        while (true) {
            System.out.print("\nIngrese el numero del documento del paciente: ");
            nDocumento = sc.nextLine();

            if (buscarPaciente(pacientes, nDocumento, contPacientes) == null)
                break;
            else
                System.out.println("Ya existe un usuario con ese numero de documento. Ingrese un documento diferente.");
        }

        System.out.print("Ingrese el nombre completo del paciente: ");
        nombre = sc.nextLine();

        while (true) {
            System.out.print("Ingrese la edad del paciente: ");
            edad = sc.nextInt();

            if (edad > 0)
                break;
            else
                System.out.println("Edad ingresada no valida. Ingresela nuevamente");
        }
        sc.nextLine();

        while (true) {
            System.out.print("Ingrese el tipo de atencion (Urgencias, Consulta General, Prioritaria): ");
            tipoAtencion = sc.nextLine();

            if (tipoAtencion.equalsIgnoreCase("Urgencias") || tipoAtencion.equalsIgnoreCase("Consulta General")
                    || tipoAtencion.equalsIgnoreCase("Prioritaria"))
                break;
            else
                System.out.println("Tipo de atencion no valido. Ingreselo nuevamente.");
        }

        while (true) {
            System.out.print("Ingrese el nivel de prioridad del 1 al 5: ");
            nivelPrioridad = sc.nextInt();

            if (nivelPrioridad >= 1 && nivelPrioridad < 6)
                break;
            else
                System.out.println("Nivel de prioridad no permitido. Ingreselo nuevamente.");
        }
        sc.nextLine();

        paciente[0] = nDocumento;
        paciente[1] = nombre;
        paciente[2] = edad;
        paciente[3] = tipoAtencion;
        paciente[4] = nivelPrioridad;

        return paciente;
    }

    public static void listarPacientes(Object[][] pacientes, int contPacientes) {
        if (contPacientes > 0) {
            for (int i = 0; i < contPacientes; i++) {
                System.out.println("\n===================================");
                System.out.println("Paciente #" + (i + 1));
                System.out.println("Numero de documento: " + pacientes[i][0]);
                System.out.println("Nombre completo: " + pacientes[i][1]);
                System.out.println("Edad: " + pacientes[i][2]);
                System.out.println("Tipo de Atencion: " + pacientes[i][3]);
                System.out.println("Nivel de prioridad: " + pacientes[i][4]);
                System.out.println("===================================");
            }
        } else
            System.out.println("\nNo hay pacientes ingresados.");
    }

    public static Object[] buscarPaciente(Object[][] pacientes, String nDocumento, int contPacientes) {
        for (int i = 0; i < contPacientes; i++)
            if (pacientes[i][0].equals(nDocumento))
                return pacientes[i];
        return null;
    }

    public static void contarPacientesTipo(Object[][] pacientes, int contPacientes) {
        if (contPacientes > 0) {
            int contUrgencias = 0, contConsultaGeneral = 0, contPrioritaria = 0;

            for (int i = 0; i < contPacientes; i++) {
                if (pacientes[i][3].toString().equalsIgnoreCase("Urgencias"))
                    contUrgencias++;
                else if (pacientes[i][3].toString().equalsIgnoreCase("Consulta General"))
                    contConsultaGeneral++;
                else if (pacientes[i][3].toString().equalsIgnoreCase("Prioritaria"))
                    contPrioritaria++;
            }

            System.out.println("\n===================================");
            System.out.println("Pacientes por tipo de atencion");
            System.out.println("Urgencias: " + contUrgencias);
            System.out.println("Consulta General: " + contConsultaGeneral);
            System.out.println("Prioritaria: " + contPrioritaria);
            System.out.println("===================================");
        } else
            System.out.println("\nNo hay pacientes ingresados.");
    }

    public static void determinarPacientePrioritario(Object[][] pacientes, int contPacientes) {
        if (contPacientes > 0) {
            int prioridadMasAlta = 0, tipAtencionMasAlta = 0, contPaciPrioritarios = 0;

            for (int i = 0; i < contPacientes; i++) {
                if ((int) pacientes[i][4] > prioridadMasAlta)
                    prioridadMasAlta = (int) pacientes[i][4];
            }

            for (int j = 0; j < contPacientes; j++) {
                if ((int) pacientes[j][4] == prioridadMasAlta) {
                    int jerarquia = obtenerJerarquiaAtencion(pacientes[j][3].toString());

                    if (jerarquia > tipAtencionMasAlta)
                        tipAtencionMasAlta = jerarquia;
                }
            }

            for (int x = 0; x < contPacientes; x++) {
                if ((int) pacientes[x][4] == prioridadMasAlta
                        && obtenerJerarquiaAtencion(pacientes[x][3].toString()) == tipAtencionMasAlta) {

                    contPaciPrioritarios++;

                    System.out.println("\n===================================");
                    System.out.println("Paciente Prioritario #" + contPaciPrioritarios);
                    System.out.println("Numero de documento: " + pacientes[x][0]);
                    System.out.println("Nombre completo: " + pacientes[x][1]);
                    System.out.println("Edad: " + pacientes[x][2]);
                    System.out.println("Tipo de Atencion: " + pacientes[x][3]);
                    System.out.println("Nivel de prioridad: " + pacientes[x][4]);
                    System.out.println("===================================");
                }
            }

        } else {
            System.out.println("\nNo hay pacientes ingresados.");
        }
    }

    public static int obtenerJerarquiaAtencion(String tipoAtencion) {
        if (tipoAtencion.equalsIgnoreCase("Urgencias"))
            return 3;
        else if (tipoAtencion.equalsIgnoreCase("Prioritaria"))
            return 2;
        else
            return 1;
    }

    /* EJERCICIO #2: Sistema de control de inventario para una cafetería universitaria*/
    public static void menuCafeteria() {
        InventarioCafeteria.ejecutar(sc);
    }

    /* EJERCICIO #3: Sistema de control académico para seguimiento de notas*/
    public static void menuNotas() {

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

    public static int leerEntero() {

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero valido: ");
            }
        }
    }

    public static double leerDecimal() {

        while (true) {
            try {
                return Double.parseDouble(
                        sc.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }

    public static double calcularDefinitiva(int posicion) {

        double suma = 0;

        for (int i = 0; i < 3; i++) {
            suma += notas[posicion][i];
        }

        return suma / 3;
    }

    public static String determinarEstado(double nota) {

        if (nota >= 3.0) {
            return "Aprobado";
        } else if (nota >= 2.0) {
            return "Habilita";
        } else {
            return "Reprueba";
        }
    }

    public static void registrarEstudiante() {

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

        do {
            System.out.print("Porcentaje de asistencia (0-100): ");
            asistencia[posicion] = leerDecimal();

            if (asistencia[posicion] < 0 ||
                    asistencia[posicion] > 100) {

                System.out.println("Error: la asistencia debe estar entre 0 y 100.");
            }

        } while (asistencia[posicion] < 0 ||
                asistencia[posicion] > 100);

        definitiva[posicion] = calcularDefinitiva(posicion);

        estado[posicion] = determinarEstado(definitiva[posicion]);

        cantidad++;

        System.out.println("\nEstudiante registrado correctamente.");
        System.out.printf("Definitiva: %.2f%n", definitiva[posicion]);
        System.out.println("Estado: " + estado[posicion]);
    }

    public static void mostrarEstudiantes() {

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

    public static void mostrarPromedioGeneral() {

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

    public static void mostrarMejorYPeor() {

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
