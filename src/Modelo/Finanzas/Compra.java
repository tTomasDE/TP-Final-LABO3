package Modelo.Finanzas;

import Modelo.Humanos.Cliente;
import Modelo.Humanos.Empleado;
import Modelo.Mercaderia.Ropa;

import java.util.ArrayList;
import java.util.UUID;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            info+=ro.getTipo()+", "+ro.getTalle()+", "+ro.getColorRopa()+" | $"+ro.getPrecio()+"\n";
        }
        return info;
    }

    public String getEmpleadoAtencion(){
        return this.empleadoAtencion.getNombre()+" "+this.empleadoAtencion.getApellido();
    }

    public void crearPDF (){
        LocalDate fechaActual = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        String currentDir = System.getProperty("user.dir");
        String folderPath = currentDir + "/Comprobantes";
        String filePath = folderPath + "/"+fechaFormateada+"---"+this.cliente.getApellido()+"_"+this.cliente.getNombre()+".pdf";


        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String contenido = "NOMBRE DEL LOCAL" +
                "\nDireccion: Av Independencia 2940" +
                "\nHorarios: 09:00-18:00" +
                "\n\n----------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                                 RECIBO DE PAGO                \n" +
                "----------------------------------------------------------------------------------------------------------------------------------\n\n" +
                "Fecha: " + fechaFormateada + "\n\n" +
                "Orden de compra nro: "+getOrdenDeCompra()+"\n\n" +
                "Cliente: "+this.cliente.getApellido()+" "+this.cliente.getNombre()+"\n\n" +"----------------------------------------------------------------------------------------------------------------------------------"+
                "\n\nItems Comprados: \n\n" +
                getItemsComprados()
                +
                "\n\n\n\n\n\n\n\n----------------------------------------------------------------------------------------------------------------------------------\n" +
                "\nTOTAL: $"+this.calcularTotal()+"\n" +
                "\nEmpleado: Juan García\n" +
                "\n----------------------------------------------------------------------------------------------------------------------------------\n\n";

        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(filePath));


            document.open();


            document.add(new Paragraph(contenido));


            document.close();

            if (Desktop.isDesktopSupported()) {
                try {
                    File pdfFile = new File(filePath);
                    if (pdfFile.exists()) {
                        Desktop.getDesktop().open(pdfFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

}
