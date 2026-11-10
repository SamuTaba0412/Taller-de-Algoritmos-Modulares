import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        menuIps();
    }

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
                if ((int) pacientes[x][4] == prioridadMasAlta && obtenerJerarquiaAtencion(pacientes[x][3].toString()) == tipAtencionMasAlta) {

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
}
