package Modelo;

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

    public String getDireccion() {
        return direccion;
    }

    public int getAltura() {
        return altura;
    }

    public String getHorarios() {
        return horarios;
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

    public void agregarEmpleado(Empleado e){
        this.empleados.add(e);
    }

    public void agregarRopaAlStock (Ropa r){
        this.stockRopa.add(r);
    }

    public void procesarCompra(Compra c){

    }
}
