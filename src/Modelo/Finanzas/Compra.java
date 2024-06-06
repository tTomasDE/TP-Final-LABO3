package Modelo.Finanzas;

import Modelo.Humanos.Cliente;
import Modelo.Humanos.Empleado;
import Modelo.Local;
import Modelo.Mercaderia.Ropa;

import java.io.Serializable;
import java.util.*;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.lowagie.text.Image;

public class Compra implements Serializable {

    private String ordenDeCompra;
    private HashMap<Ropa, Integer> itemsComprados;
    private double total;
    private String fechaDeCompra;

    public Compra() {
    }
    public Compra(HashMap<Ropa, Integer> itemsComprados) {
        this.ordenDeCompra=calcularOrdenDeCompra();
        this.itemsComprados = itemsComprados;
        this.total = calcularTotal();
        this.fechaDeCompra=calcularFecha();
    }
    public String getOrdenDeCompra (){
        return this.ordenDeCompra;
    }
    public HashMap<Ropa, Integer> getItemsComprados() {
        return itemsComprados;
    }
    public String imprimirItemsComprados() {
        StringBuilder info = new StringBuilder("\n");
        for (Map.Entry<Ropa, Integer> entry : itemsComprados.entrySet()) {
            Ropa prenda = entry.getKey();
            int cantidad = entry.getValue();
            if (prenda.isDisponibilidad()) {
                info.append(prenda.toStringParaListaDeCompra());
                if (cantidad > 1) {
                    info.append(" x").append(cantidad).append("\n");
                } else {
                    info.append("\n");
                }
            }
        }
        return info.toString();
    }
    public double getTotal() {
        return total;
    }
    public String getFechaDeCompra() {
        return fechaDeCompra;
    }
    public double calcularTotal() {
        double total = 0;

        for (Map.Entry<Ropa, Integer> entry : itemsComprados.entrySet()) {
            Ropa prenda = entry.getKey();
            int cantidad = entry.getValue();
            total += prenda.getPrecio() * cantidad;
        }

        return total;
    }
    private String calcularOrdenDeCompra (){
        return UUID.randomUUID().toString();
    }
    private String calcularFecha(){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fechaActual.format(formatter);
        return fechaFormateada;
    }
    public void agregarItems(Ropa ro) {
        int cantidad = itemsComprados.getOrDefault(ro, 0);
        itemsComprados.put(ro, cantidad + 1);
    }
    public void eliminarItem(Ropa item) {
        int cantidad = itemsComprados.getOrDefault(item, 0);
        if (cantidad > 1) {
            itemsComprados.put(item, cantidad - 1);
        } else if (cantidad == 1) {
            itemsComprados.remove(item);
        }
    }
    public void crearPDF (Local local, Cliente cliente){


        String currentDir = System.getProperty("user.dir");
        String folderPath = currentDir + "/Comprobantes";
        String filePath = folderPath + "/"+this.fechaDeCompra+"---"+cliente.getApellido()+"_"+cliente.getNombre()+"---"+getOrdenDeCompra()+".pdf";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Document document = new Document();

        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            PdfContentByte cb = writer.getDirectContent();

            try {
                String imagePath = currentDir + "/logo.png";
                Image logo = Image.getInstance(imagePath);
                logo.setAbsolutePosition(466, 750);
                logo.scaleToFit(80, 80);
                document.add(logo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            document.add(new Paragraph("LOCAL VENTA DE ROPA\nDireccion: "+local.getDireccion()+" "+local.getAltura()+"\nHorarios: "+local.getHorarios()+"\n\n"));

            cb.setLineWidth(1f);
            cb.moveTo(36, 740);
            cb.lineTo(559, 740);
            cb.stroke();

            document.add(new Paragraph("                                                              RECIBO DE PAGO\n\n"));

            cb.setLineWidth(1f);
            cb.moveTo(36, 700);
            cb.lineTo(559, 700);
            cb.stroke();

            document.add(new Paragraph("\nOrden de compra: "+getOrdenDeCompra()+"\n\n"+"Fecha: " + this.fechaDeCompra + "\n\nCliente: "+cliente.getApellido()+" "+cliente.getNombre()+"\n\n"));

            cb.setLineWidth(1f);
            cb.moveTo(36, 575);
            cb.lineTo(559, 575);
            cb.stroke();

            document.add(new Paragraph("Items Comprados:\n\n"+imprimirItemsComprados()));

            cb.setLineWidth(1f);
            float x = 36;
            float y = 540;
            float width = 523;
            float height = 450;
            cb.rectangle(x, y - height, width, height);
            cb.stroke();

            cb.setLineWidth(1f);
            cb.moveTo(36, 140);
            cb.lineTo(559, 140);
            cb.stroke();
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Paragraph(" TOTAL: $"+calcularTotal()), 36, 120, 0);
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
