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
//////////////////////////////////////GETTER Y SETTERS///////////////////////////////////////
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
/////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////METODOS//////////////////////////////////////////////


    @Override
    public String toString() {
        return "Ropa{" +
                "stock=" + stock +
                ", tipo='" + tipo + '\'' +
                ", talle='" + talle + '\'' +
                ", precio=" + precio +
                ", colorRopa='" + colorRopa + '\'' +
                '}';
    }

    public String toCSV() {
        return stock + "," + tipo + "," + talle + "," + precio + "," + colorRopa;
    }

    public static Ropa fromCSV(String csv) {
        String[] parts = csv.split(",");
        int stock = Integer.parseInt(parts[0]);
        String prenda = parts[1];
        String talle = parts[2];
        double precio = Double.parseDouble(parts[3]);
        String color = parts[4];
        return new Ropa(stock, prenda, talle, precio, color);
    }

    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;

        if(obj!=null){
            if(obj instanceof Ropa){
                Ropa aux = (Ropa) obj;
                if(aux.getTipo().equalsIgnoreCase(getTipo()) && aux.getTalle().equalsIgnoreCase(getTalle()) && aux.getColorRopa().equalsIgnoreCase(getColorRopa())){
                    sonIguales=true;
                }
            }
        }

        return sonIguales;
    }
}
