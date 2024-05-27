package Modelo.Humanos;

public class Cliente extends Persona {

    private double historialCompras;

    public Cliente (){
        super();
        this.id=0;
        this.historialCompras= 0;
    }

    public Cliente(String nombre, String apellido, String dni, double historialCompras) {
        super(nombre, apellido, dni);
        this.historialCompras = historialCompras;
    }

    public double getHistorialCompras() {
        return historialCompras;
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
