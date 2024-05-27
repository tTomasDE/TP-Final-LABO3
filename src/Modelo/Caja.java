package Modelo;

import java.util.HashSet;

public class Caja {

    private double cajaInicial;//Con cuanto arranca la caja en ese dia
    private double recaudacion;

    private HashSet<Compra> comprasDelDia;

    public Caja() {
        this.recaudacion=0;
        this.comprasDelDia= new HashSet<>();
    }

    public double getRecaudacion() {
        return recaudacion;
    }

    public double getCajaInicial() {
        return cajaInicial;
    }

    public void setCajaInicial(double cajaInicial) {
        this.cajaInicial = cajaInicial;
    }

}
