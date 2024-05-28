import Modelo.Humanos.Cliente;
import Modelo.Finanzas.Compra;
import Modelo.Humanos.Empleado;
import Modelo.Local;
import Modelo.Mercaderia.Ropa;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
/*
        Empleado emp1 = new Empleado("Tomas","Denk","44145316",300.000,"09:00-18:30");

        Ropa rop1 = new Ropa(1,"Remera","M",15.500,"Roja");
        Ropa rop2 = new Ropa(15,"Pantalon","XL",35.500,"Azul");
        Ropa rop3 = new Ropa(12,"Buzo","L",55.500,"Verde");

        Local lo = new Local("Luro",2984,"03:00-10:00");

        lo.agregarEmpleado(emp1);

        lo.agregarRopaAlStock(rop1);
        lo.agregarRopaAlStock(rop1);
        lo.agregarRopaAlStock(rop1);
        lo.agregarRopaAlStock(rop2);
        lo.agregarRopaAlStock(rop3);

        System.out.println(lo.verificarDisponibilidad("M","Remera"));
*/
    Local loc=new Local();
    //cargarRopa(loc);
    //mostrarRopa(loc.getStockRopa());
    //guardarRopaEnArchivo(loc.getStockRopa(), "Ropa.txt");
    mostrarRopaDesdeArchivo("Ropa.txt");
    }
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
        System.out.println("Dime el talle: ");
        String talle = scanner.nextLine();
        System.out.println("Dime el precio: ");
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
    public static void guardarRopaEnArchivo(ArrayList<Ropa> ropaAux, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Ropa ro : ropaAux) {
                writer.write(ro.toCSV());
                writer.newLine();
            }
            System.out.println("Ropa guardada en el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }//Guardar la ropa en archivo
    public static void mostrarRopaDesdeArchivo(String nombreArchivo) {
        ArrayList<Ropa> ropaAux = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Ropa ro = Ropa.fromCSV(linea);
                ropaAux.add(ro);
            }
            System.out.println("Ropa cargada desde el archivo " + nombreArchivo + ":");
            mostrarRopa(ropaAux);
        } catch (IOException e) {
            System.err.println("Error al leer del archivo: " + e.getMessage());
        }
    }//Mostrar ropa desde el archivo
}

