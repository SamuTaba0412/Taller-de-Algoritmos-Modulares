import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        menuIps();
    }

    public static void menuIps() {
        int opcion = 0, contPacientes = 4;

        Object[][] pacientes = new Object[30][5];

        pacientes[0] = new Object[] { "1001234567", "Carlos Ramirez", 35, "Urgencias", 5 };
        pacientes[1] = new Object[] { "1007654321", "Maria Fernanda Lopez", 22, "Consulta General", 2 };
        pacientes[2] = new Object[] { "1009876543", "Andres Felipe Torres", 48, "Prioritaria", 4 };
        pacientes[3] = new Object[] { "1017923676", "Samuel Tabares Patiño", 21, "Consulta General", 1};

        do {
            System.out.println(
                    "\nBienvenido al sistema de control por turnos de la IPS!\nPor favor, seleccione una opcion:\n1. Registrar pacientes.\n2. Mostrar listado general.\n3. Buscar paciente por documento.\n4. Mostrar pacientes por tipo de atencion.\n5. Identificar paciente con mayor prioridad.\n6. Salir.");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    if (contPacientes == 30)
                        System.out.println("\nYa no hay cupos disponibles.");
                    else {
                        pacientes[contPacientes] = registrarPaciente(pacientes, contPacientes);
                        contPacientes++;
                    }
                    break;
                case 2:
                    listarPacientes(pacientes, contPacientes);
                    System.out.println("\nPaciente ingresado con exito!");
                    break;
                case 3:
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
                    break;
                case 4:
                    break;
                case 5:
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

            if (buscarPaciente(pacientes, nDocumento, contPacientes) == null) break;
            else System.out.println("Ya existe un usuario con ese numero de documento. Ingrese un documento diferente.");
        }

        System.out.print("Ingrese el nombre completo del paciente: ");
        nombre = sc.nextLine();

        System.out.print("Ingrese la edad del paciente: ");
        edad = sc.nextInt();
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
        Object[] pacienteEncontrado = {};

        if (contPacientes > 0) {
            for (int i = 0; i < contPacientes; i++) {
                if (pacientes[i][0].equals(nDocumento)) {
                    pacienteEncontrado = pacientes[i];
                    break;
                }
            }
        } else
            System.out.println("\nNo hay pacientes ingresados.");

        return pacienteEncontrado.length > 0 ? pacienteEncontrado : null;
    }
}