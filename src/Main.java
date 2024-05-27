import Modelo.Humanos.Cliente;
import Modelo.Finanzas.Compra;
import Modelo.Humanos.Empleado;
import Modelo.Local;
import Modelo.Mercaderia.Ropa;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

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
    public static void agregarRopa(){
        System.out.println("Dime el stock:");
        System.out.println("Dime el tipo de prenda:");
        System.out.println("Dime el stock");
        System.out.println("Dime el stock");
        System.out.println("Dime el stock");
    }
}

