package club;

import java.util.ArrayList;

public class Socio {

    public enum Tipo { VIP, REGULAR }

    public static final double FONDOS_INICIALES_REGULARES = 50;
    public static final double FONDOS_INICIALES_VIP = 100;
    public static final double MONTO_MAXIMO_REGULARES = 1000;
    public static final double MONTO_MAXIMO_VIP = 5000;

    private String cedula;
    private String nombre;
    private double fondos;
    private Tipo tipoSubscripcion;
    private ArrayList<Factura> facturas;
    private ArrayList<String> autorizados;

    public Socio(String cedula, String nombre, Tipo tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoSubscripcion = tipo;
        this.fondos = (tipo == Tipo.VIP) ? FONDOS_INICIALES_VIP : FONDOS_INICIALES_REGULARES;
        this.facturas = new ArrayList<>();
        this.autorizados = new ArrayList<>();
    }

    public String darCedula() { return cedula; }
    public String darNombre() { return nombre; }
    public double darFondos() { return fondos; }
    public Tipo darTipo() { return tipoSubscripcion; }
    public ArrayList<Factura> darFacturas() { return facturas; }
    public ArrayList<String> darAutorizados() { return autorizados; }

    private boolean existeAutorizado(String nombre) {
        return autorizados.contains(nombre);
    }

    private boolean autorizadoTieneFactura(String nombre) {
        return facturas.stream().anyMatch(f -> f.darNombre().equals(nombre));
    }

    public void aumentarFondos(double valor) {
        try {
            if (tipoSubscripcion == Tipo.VIP && fondos + valor > MONTO_MAXIMO_VIP)
                throw new Exception("Se excede el límite VIP");
            if (tipoSubscripcion == Tipo.REGULAR && fondos + valor > MONTO_MAXIMO_REGULARES)
                throw new Exception("Se excede el límite REGULAR");

            fondos += valor;

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void registrarConsumo(String nombre, String concepto, double valor) {
        try {
            if (valor > fondos)
                throw new Exception("Fondos insuficientes");

            facturas.add(new Factura(nombre, concepto, valor));

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void agregarAutorizado(String nombre) {
        try {
            if (existeAutorizado(nombre))
                throw new Exception("El autorizado ya existe");

            if (fondos <= 0)
                throw new Exception("No hay fondos para agregar un autorizado");

            autorizados.add(nombre);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void eliminarAutorizado(String nombre) {
        try {
            if (autorizadoTieneFactura(nombre))
                throw new Exception("El autorizado tiene facturas pendientes");

            autorizados.remove(nombre);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void pagarFactura(int indice) {
        try {
            Factura f = facturas.get(indice);

            if (f.darValor() > fondos)
                throw new Exception("Fondos insuficientes para pagar");

            fondos -= f.darValor();
            facturas.remove(indice);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public String toString() {
        return cedula + " - " + nombre;
    }
}

