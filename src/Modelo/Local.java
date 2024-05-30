package Modelo;

import Modelo.Excepciones.eSinStock;
import Modelo.Finanzas.Caja;
import Modelo.Finanzas.Compra;
import Modelo.Humanos.Empleado;
import Modelo.Mercaderia.Ropa;
import Modelo.Mercaderia.Talle;

import java.util.ArrayList;
import java.util.HashSet;

public class Local {

    private String direccion;
    private int altura;
    private String horarios;
    private Caja caja;
    private ArrayList<Ropa> stockRopa;
    private HashSet<Empleado> empleados;

    public Local() {
        this.direccion = "";
        this.altura = 0;
        this.horarios = "";
        this.caja=new Caja();
        this.stockRopa=new ArrayList<>();
        this.empleados= new HashSet<>();
    }

    public Local(String direccion, int altura, String horarios) {
        this.direccion = direccion;
        this.altura = altura;
        this.horarios = horarios;
        this.caja=new Caja();
        this.stockRopa=new ArrayList<>();
        this.empleados= new HashSet<>();
    }
///////////////////////////GETTERS Y SETTERS///////////////////////////////////////////////
    public String getDireccion() {
        return direccion;
    }

    public int getAltura() {
        return altura;
    }

    public String getHorarios() {
        return horarios;
    }

    public ArrayList<Ropa> getStockRopa() {
        return stockRopa;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

//////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////METODOS////////////////////////////////////////////////////


    public void agregarEmpleado(Empleado e){
        this.empleados.add(e);
    }

    public void agregarRopaAlStock (Ropa r){
        this.stockRopa.add(r);
    }

    public void procesarCompra(Compra c){
    }

    public void comprarUnaRopa(Ropa ropa){
        try{
            ropa.validarStock(ropa.getStock());
            ropa.bajarUnStock();
            caja.agregarDinero(ropa.getPrecio());
        }catch (eSinStock e){
            e.printStackTrace();
        }
    }
    public void bajarUnEmpleado(Empleado emp){
        emp.setDisponible(false);
    }
    public void subirUnEmpleado(Empleado emp){
        emp.setDisponible(true);
    }



    public boolean verificarDisponibilidad (Talle talle, String tipo){
        boolean encontrado = false;

        for(Ropa ropa : this.stockRopa){
            if(ropa.getTalle()==talle && ropa.getTipo().equalsIgnoreCase(tipo)){
                if(ropa.getStock()>0){
                    encontrado=true;
                }
            }
        }

        return encontrado;
    }
}
