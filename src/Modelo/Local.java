package Modelo;

import Modelo.Excepciones.eSinStock;
import Modelo.Finanzas.Caja;
import Modelo.Finanzas.Compra;
import Modelo.Humanos.Cliente;
import Modelo.Humanos.Empleado;
import Modelo.Mercaderia.Ropa;
import Modelo.Mercaderia.Talle;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Local implements Serializable{
    private static final long serialVersionUID = -862208884450743488L;
    private String direccion;
    private int altura;
    private String horarios;
    private Caja caja;
    private ArrayList<Ropa> stockRopa;
    private HashSet<Empleado> empleados;
    private HashSet<Cliente> clientes;

    public Local() {
        this.direccion = "";
        this.altura = 0;
        this.horarios = "";
        this.caja=new Caja();
        this.stockRopa=new ArrayList<>();
        this.empleados= new HashSet<>();
        this.clientes= new HashSet<>();
    }
    public Local(String direccion, int altura, String horarios) {
        this.direccion = direccion;
        this.altura = altura;
        this.horarios = horarios;
        this.caja=new Caja();
        this.stockRopa=new ArrayList<>();
        this.empleados= new HashSet<>();
        this.clientes= new HashSet<>();
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
    public HashSet<Empleado> getEmpleados() {
        return empleados;
    }
    public HashSet<Cliente> getClientes() {
        return clientes;
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////METODOS////////////////////////////////////////////////////

    public String imprimirInformacionDelLocal (){
        return "Direccion: "+getDireccion()+" "+getAltura()+"\nHorarios: "+getHorarios()+"\n";
    }
    private int obtenerUltimoIdEmpleado() {
        int maxId = 0;
        for (Empleado emp : empleados) {
            if (emp.getId() > maxId) {
                maxId = emp.getId();
            }
        }
        return maxId;
    }
    public void agregarEmpleado(Empleado e){
        e.setId(obtenerUltimoIdEmpleado()+1);
        this.empleados.add(e);
    }
    public String imprimirEmpleados(){
        String info="";

        for(Empleado emp : this.empleados){
            if (emp.isDisponible()){
                info+= emp.toString()+"\n";
            }
        }
        return info;
    }
    public String imprimirEmpleadosDadosDeBaja(){
        String info="";

        for(Empleado emp : this.empleados){
            if (!emp.isDisponible()){
                info+= emp.toString()+"\n";
            }
        }
        return info;
    }
    private int obtenerUltimoIdCliente() {
        int maxId = 0;
        for (Cliente cli : clientes) {
            if (cli.getId() > maxId) {
                maxId = cli.getId();
            }
        }
        return maxId;
    }
    public void agregarCliente(Cliente c){
        c.setId(obtenerUltimoIdCliente()+1);
        this.clientes.add(c);
    }
    private int obtenerUltimoIdRopa() {
        int maxId = 0;
        for (Ropa ropa : this.stockRopa) {
            if (ropa.getId() > maxId) {
                maxId = ropa.getId();
            }
        }
        return maxId;
    }
    public boolean buscarIdPorRopa (int ID){
        boolean rta=false;
        for(Ropa ropa : this.stockRopa){
            if(ropa.getId()==ID){
                rta=true;
            }
        }
        return rta;
    }
    public Ropa buscarRopaPorId(int id) {
        Ropa ropaEncontrada = null;

        for (Ropa rop : stockRopa) {
            if (rop.getId() == id) {
                ropaEncontrada = rop;
            }
        }

        return ropaEncontrada;
    }
    public void agregarRopaAlStock (Ropa r){
        boolean encontrada = false;
        for (Ropa ropaExistente : stockRopa) {
            if (ropaExistente.equals(r)) {
                ropaExistente.setStock(ropaExistente.getStock() + r.getStock());
                encontrada = true;
            }
        }
        if (!encontrada) {
            r.setId(obtenerUltimoIdRopa()+1);
            stockRopa.add(r);
        }
    }
    public String mostrarStockRopa() {
        String info="";
        int i=0;
        for(Ropa ro : this.stockRopa){
            if(ro.isDisponibilidad()){
            info+=ro.toString();
        }
        }
        return info;
    }
    public String mostrarStockRopaNoDisponible() {
        String info="";
        int i=0;
        for(Ropa ro : this.stockRopa){
            if(!ro.isDisponibilidad()){
                info+=ro.toString();
            }
        }
        return info;
    }
    public void comprarUnaRopa(Ropa ropa){
        try{
            ropa.validarStock(ropa.getStock());
            ropa.bajarUnStock();
            caja.agregarDinero(ropa.getPrecio());
            caja.agregarRecaudacion(ropa.getPrecio());
        }catch (eSinStock e){
            e.printStackTrace();
        }
    }
    public void agregarRecaudacion(double total){
        this.caja.agregarRecaudacion(total);

    }
    public void retirarDinero(double retirar){
        this.caja.retirarDinero(retirar);
    }
    public String imprimirRetiros(){
        return this.caja.obtenerRetirosPorFecha();
    }
    public double getRecaudacion(){
        return this.caja.getRecaudacion();
    }
    public void darDeBajaEmpleado(int ID){
        for(Empleado emp : this.empleados){
            if(emp.isDisponible() && emp.getId()==ID){
                emp.setDisponible(false);
            }
        }
    }
    public void darDeAltaEmpleado(int ID){
        for(Empleado emp : this.empleados){
            if(!emp.isDisponible() && emp.getId()==ID){
                emp.setDisponible(true);
            }
        }
    }
    public void editarNombreCompletoEmpleado(int ID,String nombre, String Apellido){
        for(Empleado emp : this.empleados){
            if(emp.isDisponible() && ID==emp.getId()){
                emp.setNombre(nombre);
                emp.setApellido(Apellido);
            }
        }
    }
    public void editarSalarioEmpleado(int ID,double salario){
        for(Empleado emp : this.empleados){
            if(emp.isDisponible() && ID==emp.getId()){
                emp.setSalario(salario);

            }
        }
    }
    public void editarHorariosEmpleado(int ID,String Horario){
        for(Empleado emp : this.empleados){
            if(emp.isDisponible() && ID==emp.getId()){
                emp.setHorarios(Horario);

            }
        }
    }
    public boolean hayEmpleadosDadosDeBaja() {
        boolean hayDadosDeBaja = false;
        for (Empleado empleado : empleados) {
            if (!empleado.isDisponible()) {
                hayDadosDeBaja = true;
            }
        }
        return hayDadosDeBaja;
    }
    public boolean hayRopaDadaDeBaja() {
        boolean hayDadosDeBaja = false;
        for (Ropa ropa : this.stockRopa) {
            if (!ropa.isDisponibilidad()) {
                hayDadosDeBaja = true;
            }
        }
        return hayDadosDeBaja;
    }
    public boolean buscarIdEmpleado (int ID){
        boolean rta=false;
        for(Empleado emp : this.empleados){
            if(emp.getId()==ID){
                rta=true;
            }
        }
        return rta;
    }
    public Cliente buscarClientePorDni(String DNI){
        Cliente cliente=null;
        for (Cliente cli : this.clientes){
            if(cli.getDni().equals(DNI)){
                cliente=cli;
            }
        }
        return cliente;
    }
    public String imprimirClientes(){
        String info="";
        for(Cliente cli : this.clientes){
            info+=cli.toString()+"\n";
        }
        return info;
    }
    public String filtrarRopaPorTipo(String tipo){
        String info="";
        int i=0;
        for(Ropa ro : this.stockRopa){
            if(ro.getTipo().equalsIgnoreCase(tipo)) {
                info += "[" + i + "] " + ro.getTipo() + ", " + ro.getTalle() + ", " + ro.getColorRopa() + " | $" + ro.getPrecio() + "\n";
                i++;
            }
        }
        return info;
    }
    public String filtrarRopaPorTalle(Talle talle){
        String info="";
        int i=0;
        for(Ropa ro : this.stockRopa){
            if(ro.getTalle().equals(talle)) {
                info += "[" + i + "] " + ro.getTipo() + ", " + ro.getTalle() + ", " + ro.getColorRopa() + " | $" + ro.getPrecio() + "\n";
                i++;
            }
        }
        return info;
    }
    public String filtrarRopaPorColor(String color){
        String info="";
        int i=0;
        for(Ropa ro : this.stockRopa){
            if(ro.getColorRopa().equalsIgnoreCase(color)) {
                info += "[" + i + "] " + ro.getTipo() + ", " + ro.getTalle() + ", " + ro.getColorRopa() + " | $" + ro.getPrecio() + "\n";
                i++;
            }
        }
        return info;
    }
    public void darDeBajaRopa(int ID){
        for(Ropa ropa : this.stockRopa){
            if(ropa.isDisponibilidad() && ropa.getId()==ID){
                ropa.setDisponibilidad(false);
            }
        }
    }
    public void darDeAltaRopa(int ID){
        for(Ropa ropa : this.stockRopa){
            if(!ropa.isDisponibilidad() && ropa.getId()==ID){
                ropa.setDisponibilidad(true);
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////MANEJO ARCHIVOS////////////////////////////////////////////////////

    public void AgregarLocalAlArchivo(){
        ObjectOutputStream objectOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Informacion_Local.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);


                objectOutputStream.writeObject(this);


        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public Local ObtenerLocalDelArchivo(){
        Local lo = null;
        ObjectInputStream objectInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("Informacion_Local.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            lo = (Local) objectInputStream.readObject();

            if (lo == null) {
                lo.stockRopa = new ArrayList<>();
                lo.empleados = new HashSet<>();
                lo.clientes = new HashSet<>();
            }

        } catch (EOFException ex) {

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {

            ex.printStackTrace();
        } catch (ClassNotFoundException e) {

        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException ex) {

            }
        }
        return lo;
    }
    public void AgregarRopaAlArchivo (){
        ObjectOutputStream objectOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Stock_De_Ropa.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Ropa ro : this.stockRopa) {
                objectOutputStream.writeObject(ro);
            }

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public void ObtenerRopaDelArchivo (){
        ObjectInputStream objectInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("Stock_De_Ropa.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            this.stockRopa.clear();

            while (true) {
                Ropa ropa = (Ropa) objectInputStream.readObject();
                this.stockRopa.add(ropa);

            }


        } catch (EOFException ex)
        {

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                objectInputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public void AgregarEmpleadosAlArchivo (){
        ObjectOutputStream objectOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Registro_Empleados.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Empleado emp : this.empleados) {
                objectOutputStream.writeObject(emp);
            }

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public void ObtenerEmpleadosDelArchivo (){
        ObjectInputStream objectInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("Registro_Empleados.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            this.empleados.clear();

            while (true) {
                Empleado emp = (Empleado) objectInputStream.readObject();
                this.empleados.add(emp);

            }


        } catch (EOFException ex)
        {

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                objectInputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public void AgregarClientesAlArchivo (){
        ObjectOutputStream objectOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Registro_Clientes.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Cliente cli : this.clientes) {
                objectOutputStream.writeObject(cli);
            }

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public void ObtenerClientesDelArchivo() {
        ObjectInputStream objectInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("Registro_Clientes.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            this.clientes.clear();

            while (true) {
                Cliente cliente = (Cliente) objectInputStream.readObject();
                this.clientes.add(cliente);
            }
        } catch (EOFException ex) {

        } catch (FileNotFoundException ex) {

            try {
                FileOutputStream fileOutputStream = new FileOutputStream("Registro_Clientes.dat");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
