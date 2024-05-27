package Modelo.Mercaderia;

public class Ropa {

    private int stock;

    private String tipo;

    private String talle;

    private double precio;

    private String colorRopa;

    public Ropa() {
        this.stock = 0;
        this.tipo = "";
        this.talle = "";
        this.precio = 0;
        this.colorRopa = "";
    }

    public Ropa(int stock, String tipo, String talle, double precio, String colorRopa) {
        this.stock = stock;
        this.tipo = tipo;
        this.talle = talle;
        this.precio = precio;
        this.colorRopa = colorRopa;
    }

    public int getStock() {
        return stock;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTalle() {
        return talle;
    }

    public double getPrecio() {
        return precio;
    }

    public String getColorRopa() {
        return colorRopa;
    }


}
