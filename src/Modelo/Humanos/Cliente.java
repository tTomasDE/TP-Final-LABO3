package Modelo.Humanos;

import Modelo.Finanzas.Compra;

import java.util.ArrayList;

public class Cliente extends Persona {

    private double historialCompras;
    private ArrayList<Compra> compras;

    public Cliente (){
        super();
        this.historialCompras= 0;
        this.compras=new ArrayList<>();
    }

    public Cliente(String nombre, String apellido, String dni, double historialCompras) {
        super(nombre, apellido, dni);
        this.historialCompras = historialCompras;
        this.compras=new ArrayList<>();
    }

    public double getHistorialCompras() {
        return historialCompras;
    }

    public void agregarCompra(Compra e){
        this.compras.add(e);
    }

    public void setHistorialCompras(float historialCompras) {
        this.historialCompras = historialCompras;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "historialCompras=" + historialCompras + super.toString();
    }
}
