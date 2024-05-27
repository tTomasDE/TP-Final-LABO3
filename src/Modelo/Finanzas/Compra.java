package Modelo.Finanzas;

import Modelo.Humanos.Cliente;
import Modelo.Humanos.Empleado;
import Modelo.Mercaderia.Ropa;

import java.util.ArrayList;
import java.util.UUID;

public class Compra {

    private String ordenDeCompra;
    private Cliente cliente;
    private ArrayList<Ropa> itemsComprados;
    private double total;
    private Empleado empleadoAtencion;

    public Compra(Cliente cliente, ArrayList<Ropa> itemsComprados, Empleado empleadoAtencion) {
        this.ordenDeCompra=calcularOrdenDeCompra();
        this.cliente = cliente;
        this.itemsComprados = itemsComprados;
        this.total = calcularTotal();
        this.empleadoAtencion = empleadoAtencion;
    }


    public double calcularTotal (){
        double total=0;

        for (Ropa ro : this.itemsComprados){
            total+=ro.getPrecio();
        }

        return total;
    }

    private String calcularOrdenDeCompra (){
        return UUID.randomUUID().toString();
    }

    public String getOrdenDeCompra (){
        return this.ordenDeCompra;
    }

    public String getItemsComprados(){
        String info="";
        for(Ropa ro : this.itemsComprados){
            info+=ro.getTipo()+", "+ro.getTalle()+", "+ro.getColorRopa()+" | "+ro.getPrecio()+"\n";
        }
        return info;
    }

    public String getEmpleadoAtencion(){
        return this.empleadoAtencion.getNombre()+" "+this.empleadoAtencion.getApellido();
    }

    public String obtenerComprobante(){
        return "\n\n-------------------------------------------------------\n" +
                "                  COMPROBANTE                \n" +
                "--------------------------------------------------------\n" +
                "Orden de compra nro: "+ getOrdenDeCompra() + "\n"+
                "Cliente: "+this.cliente.getNombre()+" "+this.cliente.getApellido()+
                "\n\nItems Comprados: \n"+getItemsComprados()+
                "\n------------------------------------------------------\n"+
                "\nTOTAL: "+calcularTotal()+
                "\nEmpleado: "+getEmpleadoAtencion()+
                "\n-------------------------------------------------------\n\n";
    }

}
