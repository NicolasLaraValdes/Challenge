import java.io.*;
import java.util.*;

public class NumeroAleatorio {

    private static final String FILE_PATH = "numeros.txt";
    private static final String FILE_SORTED_PATH = "numeros_ordenados.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("0 - Menu");
            System.out.println("1 - Generar nuevo archivo");
            System.out.println("2 - Leer el archivo generado");
            System.out.println("3 - Ordenar archivo");
            System.out.println("4 - Leer el archivo ordenado");
            System.out.println("5 - Buscar número en el archivo");
            System.out.println("6 - Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 0:
                    break;
                case 1:
                    generarNumerosAleatorios();
                    break;
                case 2:
                    leerArchivo(FILE_PATH);
                    break;
                case 3:
                    ordenarNumeros();
                    break;
                case 4:
                    leerArchivo(FILE_SORTED_PATH);
                    break;
                case 5:
                    System.out.print("Ingrese el número a buscar: ");
                    int numero = scanner.nextInt();
                    buscarNumero(numero);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void generarNumerosAleatorios() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                writer.write(random.nextInt(1000) + "\n");
            }
            writer.close();
            System.out.println("Números generados y guardados en " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error al generar números.");
        }
    }

    private static void leerArchivo(String filePath) {
        try {
            FileReader reader = new FileReader(filePath);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    private static void ordenarNumeros() {
        try {
            FileReader reader = new FileReader(FILE_PATH);
            Scanner fileScanner = new Scanner(reader);
            List<Integer> numeros = new ArrayList<>();
            while (fileScanner.hasNextInt()) {
                numeros.add(fileScanner.nextInt());
            }
            fileScanner.close();
            reader.close();

            Collections.sort(numeros);

            FileWriter writer = new FileWriter(FILE_SORTED_PATH);
            for (int numero : numeros) {
                writer.write(numero + "\n");
            }
            writer.close();
            System.out.println("Números ordenados y guardados en " + FILE_SORTED_PATH);
        } catch (IOException e) {
            System.out.println("Error al ordenar números.");
        }
    }

    private static void buscarNumero(int numeroBuscado) {
        try {
            FileReader reader = new FileReader(FILE_PATH);
            Scanner fileScanner = new Scanner(reader);
            boolean encontrado = false;
            while (fileScanner.hasNextInt()) {
                if (fileScanner.nextInt() == numeroBuscado) {
                    encontrado = true;
                    break;
                }
            }
            fileScanner.close();
            reader.close();

            if (encontrado) {
                System.out.println("Número " + numeroBuscado + " encontrado en el archivo.");
            } else {
                System.out.println("Número " + numeroBuscado + " no encontrado en el archivo.");
            }
        } catch (IOException e) {
            System.out.println("Error al buscar el número.");
        }
    }
}


