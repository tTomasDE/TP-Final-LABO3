package Modelo;

import java.util.HashSet;

public class Caja {

    private double recaudacion;

    private HashSet<Compra> comprasDelDia;

    public Caja() {
        this.recaudacion=0;
        this.comprasDelDia= new HashSet<>();
    }

    public double getRecaudacion() {
        return recaudacion;
    }


}
