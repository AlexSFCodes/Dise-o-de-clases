package club;

public class Factura {

    private String concepto;
    private double valor;
    private String nombre;

    public Factura(String nombre, String concepto, double valor) {
        this.nombre = nombre;
        this.concepto = concepto;
        this.valor = valor;
    }

    public String darConcepto() {
        return concepto;
    }

    public double darValor() {
        return valor;
    }

    public String darNombre() {
        return nombre;
    }

    public String toString() {
        return concepto + "   $" + valor + "   (" + nombre + ")";
    }
}