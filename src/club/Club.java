package club;

import java.util.ArrayList;

public class Club {

    public static final int MAXIMO_VIP = 3;

    private ArrayList<Socio> socios;

    public Club() {
        socios = new ArrayList<>();
    }

    public ArrayList<Socio> darSocios() {
        return socios;
    }

    public Socio buscarSocio(String cedula) {
        return socios.stream()
                .filter(s -> s.darCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    public void afiliarSocio(String cedula, String nombre, Socio.Tipo tipo) {
        try {
            if (buscarSocio(cedula) != null)
                throw new Exception("El socio ya existe");

            if (tipo == Socio.Tipo.VIP && contarSociosVIP() >= MAXIMO_VIP)
                throw new Exception("No se aceptan más socios VIP");

            socios.add(new Socio(cedula, nombre, tipo));

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public int contarSociosVIP() {
        return (int) socios.stream().filter(s -> s.darTipo() == Socio.Tipo.VIP).count();
    }

    public void agregarAutorizadoSocio(String cedula, String nombreAutorizado) {
        try {
            Socio socio = buscarSocio(cedula);
            if (socio == null)
                throw new Exception("No existe el socio");

            socio.agregarAutorizado(nombreAutorizado);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void registrarConsumo(String cedula, String persona, String concepto, double valor) {
        try {
            Socio socio = buscarSocio(cedula);

            if (socio == null)
                throw new Exception("No existe el socio");

            socio.registrarConsumo(persona, concepto, valor);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void pagarFacturaSocio(String cedula, int indice) {
        try {
            Socio socio = buscarSocio(cedula);
            if (socio == null)
                throw new Exception("No existe el socio");

            socio.pagarFactura(indice);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public void aumentarFondosSocio(String cedula, double valor) {
        try {
            Socio socio = buscarSocio(cedula);
            if (socio == null)
                throw new Exception("No existe el socio");

            socio.aumentarFondos(valor);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public double totalConsumosPorCedula(String cedula) {
        try {Socio socio = buscarSocio(cedula);
            if (socio == null) throw new Exception("No existe el socio");
            return socio.darFacturas()
                    .stream()
                    .mapToDouble(Factura::darValor)
                    .sum();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return 0;
    }
    public boolean sePuedeEliminarSocio(String cedula) {
        try {
            Socio socio = buscarSocio(cedula);
            if (socio == null) throw new Exception("No existe el socio");
            if (socio.darTipo() == Socio.Tipo.VIP) throw new Exception("No se puede eliminar socio VIP");
            if (!socio.darFacturas().isEmpty()) throw new Exception("Tiene facturas pendientes");
            if (socio.darAutorizados().size() > 1) throw new Exception("Tiene más de un autorizado");
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
    }
}