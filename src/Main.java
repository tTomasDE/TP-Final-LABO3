import Modelo.Humanos.Cliente;
import Modelo.Finanzas.Compra;
import Modelo.Humanos.Empleado;
import Modelo.Local;
import Modelo.Mercaderia.Ropa;
import Modelo.Mercaderia.Talle;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Local loc=new Local();
        //cargarRopa(loc);
        loc.comprarUnaRopa(agregarRopa());
        /*mostrarRopa(loc.getStockRopa());
        guardarRopaEnArchivoBinario(loc.getStockRopa(), "Ropa.txt");
        mostrarRopaDesdeArchivoBinario("Ropa.txt");
    */}
    public static void menu(){

    int opcion;
    do {
        System.out.println("\nMenú:");
        System.out.println("[1] ");
        System.out.println("[2] ");
        System.out.println("[3] ");
        System.out.println("[4] Salir");
        System.out.print("Ingrese su opción: ");
        opcion = scanner.nextInt();
        switch (opcion) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            default:

                break;
        }
    } while (opcion != 4);
}
    public static Ropa agregarRopa(){
        System.out.println("Dime el stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Dime el tipo de prenda: ");
        String prenda = scanner.nextLine();
        System.out.println("Dime el talle (XS, S, M, L, XL, XXL): ");
        Talle talle = null;
        while (talle == null) {
            try {
                String talleInput = scanner.nextLine().toUpperCase();
                talle = Talle.valueOf(talleInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Talle inválido. Intenta de nuevo: (XS, S, M, L, XL, XXL)");
            }
        }System.out.println("Dime el precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Dime el color de la prenda: ");
        String color = scanner.nextLine();
        Ropa ropaAux=new Ropa(stock, prenda, talle, precio, color);
        return ropaAux;
    } //Cargar una sola prenda
    public static void cargarRopa(Local localAux){
        int op = 0;
        while(op==0){
            localAux.agregarRopaAlStock(agregarRopa());
            System.out.println("Desea agregar otra prenda?\n(Escribi 0 si queres)");
            op=scanner.nextInt();
        }
    }//Cargar un local de prendas
    public static void mostrarRopa(ArrayList<Ropa> ropaAux){
        if(ropaAux.isEmpty()){
            System.out.println("No hay ropa");
        }else{
            System.out.println("Lista de ropa:");
            for (Ropa ro: ropaAux){
                System.out.println(ro);
            }
        }
    }//Mostrar la ropa
    public static void guardarRopaEnArchivoBinario(ArrayList<Ropa> ropaAux, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(ropaAux);
            System.out.println("Ropa guardada en el archivo binario " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo binario: " + e.getMessage());
        }
    }

    public static void mostrarRopaDesdeArchivoBinario(String nombreArchivo) {
        ArrayList<Ropa> ropaAux = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ropaAux = (ArrayList<Ropa>) ois.readObject();
            System.out.println("Ropa cargada desde el archivo binario " + nombreArchivo + ":");
            mostrarRopa(ropaAux);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer del archivo binario: " + e.getMessage());
        }
    }
}


