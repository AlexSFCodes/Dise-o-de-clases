package club;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Club club = new Club();
        int op = 0;

        do {
            System.out.println("===== MENÚ =====");
            System.out.println("1. Afiliar socio");
            System.out.println("2. Agregar autorizado");
            System.out.println("3. Registrar consumo");
            System.out.println("4. Pagar factura");
            System.out.println("5. Aumentar fondos");
            System.out.println("6. Total consumos por cédula");
            System.out.println("7. Verificar si se puede eliminar socio");
            System.out.println("8. Salir");
            System.out.print("Opción: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese un número válido.");
                continue;
            }

            try {
                switch (op) {
                    case 1:
                        System.out.print("Cédula: ");
                        String c1 = sc.nextLine();
                        System.out.print("Nombre: ");
                        String n1 = sc.nextLine();
                        System.out.print("Tipo (VIP/REGULAR): ");
                        String t = sc.nextLine();
                        club.afiliarSocio(c1, n1, Socio.Tipo.valueOf(t));
                        break;
                    case 2:
                        System.out.print("Cédula del socio: ");
                        String c2 = sc.nextLine();
                        System.out.print("Nombre autorizado: ");
                        String n2 = sc.nextLine();
                        club.agregarAutorizadoSocio(c2, n2);
                        break;

                    case 3:
                        System.out.print("Cédula del socio: ");
                        String c3 = sc.nextLine();
                        System.out.print("Nombre persona: ");
                        String np = sc.nextLine();
                        System.out.print("Concepto: ");
                        String cp = sc.nextLine();
                        System.out.print("Valor: ");
                        double v = Double.parseDouble(sc.nextLine());
                        club.registrarConsumo(c3, np, cp, v);
                        break;

                    case 4:
                        System.out.print("Cédula del socio: ");
                        String c4 = sc.nextLine();
                        System.out.print("Índice factura: ");
                        int idx = Integer.parseInt(sc.nextLine());
                        club.pagarFacturaSocio(c4, idx);
                        break;

                    case 5:
                        System.out.print("Cédula del socio: ");
                        String c5 = sc.nextLine();
                        System.out.print("Monto: ");
                        double m = Double.parseDouble(sc.nextLine());
                        club.aumentarFondosSocio(c5, m);
                        break;

                    case 6:
                        System.out.print("Cédula del socio: ");
                        String c7 = sc.nextLine();
                        double total = club.totalConsumosPorCedula(c7);
                        System.out.println("Total consumos: $" + total);
                        break;
                    case 7:
                        System.out.print("Cédula del socio: ");
                        String c8 = sc.nextLine();
                        boolean sePuede = club.sePuedeEliminarSocio(c8);
                        System.out.println("¿Se puede eliminar? " + sePuede);
                        break;
                    case 8:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (op != 8);
    }
}