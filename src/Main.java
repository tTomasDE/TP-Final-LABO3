import Modelo.Humanos.Cliente;
import Modelo.Finanzas.Compra;
import Modelo.Humanos.Empleado;
import Modelo.Local;
import Modelo.Mercaderia.Ropa;
import Modelo.Mercaderia.Talle;

import java.io.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        menu();
}
    public static void menu(){
    Local loc = null;
    int opcion;
    do {
        System.out.println("\nMenú:");
        System.out.println("[1] Cargar local: ");
        System.out.println("[2] Cargar personas: ");
        System.out.println("[3] Cargar ropa");
        System.out.println("[4] Salir");
        System.out.print("Ingrese su opción: ");
        opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                loc=cargarLocal();
                break;
            case 2:
                int opcionAux = scanner.nextInt();
                do {
                    System.out.println("\nMenú de Personas:");
                    System.out.println("[1] Cargar Empleado: ");
                    System.out.println("[2] Cargar Cliente: ");
                    System.out.println("[3] Volver al Menu: ");
                    switch (opcionAux) {
                        case 1:
                            assert loc != null;
                            loc.agregarEmpleado(agregarEmpleado());
                            break;
                        case 2:
                            assert loc != null;
                            loc.agregarCliente(agregarCliente());
                            break;
                        default:
                            break;
                    }
                }while(opcionAux != 3);
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
        String talleAux = scanner.nextLine().toUpperCase();
        Talle talle = Talle.valueOf(talleAux);
        System.out.println("Dime el precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Dime el color de la prenda: ");
        String color = scanner.nextLine();
        Ropa ropaAux = new Ropa(stock, prenda, talle, precio, color);
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

    public static Empleado agregarEmpleado(){

        System.out.println("Dime el apellido");
        String apellido = scanner.nextLine();

        System.out.println("Dime el nombre");
        String nombre = scanner.nextLine();

        System.out.println("Dime el dni");
        String dni = scanner.nextLine();

        System.out.println("Dime la disponibilidad: ");
        boolean disponible = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println("Dime el horario: ");
        String horario = scanner.nextLine();

        System.out.println("Dime el salario: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();

        Empleado emp = new Empleado(nombre, apellido, dni, salario, horario);

        return emp;
    }

    public static Cliente agregarCliente(){

        System.out.println("Dime el apellido");
        String apellido = scanner.nextLine();

        System.out.println("Dime el nombre");
        String nombre = scanner.nextLine();

        System.out.println("Dime el dni");
        String dni = scanner.nextLine();

        System.out.println("Dime el historial de compra: ");
        double historial = scanner.nextDouble();
        scanner.nextLine();

        Cliente cliente = new Cliente (nombre, apellido, dni, historial);

        return cliente;
    }

    public static Local cargarLocal(){

        System.out.println("Decime la calle del local: ");
        String direccion = scanner.nextLine();

        System.out.println("Decime la altura: ");
        int altura = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Decime el horario de atencion: ");
        String horario = scanner.nextLine();

        Local local = new Local(direccion, altura, horario);

        return local;
    }

    public static Compra cargarCompra(Local local){

        Iterator<Empleado> it = local.getEmpleados().iterator();
        Empleado empleado = null;
        if (it.hasNext()) {
            empleado = it.next();
        } else {
            // x si no hay empleados en el local
            throw new IllegalStateException("No hay empleados disponibles en el local.");
        }
        Compra compra= new Compra(local.getStockRopa(), empleado);

        return compra;
    }

    public static void comprobante(Local local, Compra compra)
    {
        Iterator<Cliente> it = local.getClientes().iterator();
        Cliente cliente = null;
        if (it.hasNext()) {
            cliente = it.next();
        } else {
            // x si no hay Cliente en el local
            throw new IllegalStateException("No hay clientes disponibles en el local.");
        }

        compra.crearPDF(local, cliente);

    }


}


