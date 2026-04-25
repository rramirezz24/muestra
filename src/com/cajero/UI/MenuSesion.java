package com.cajero.UI;

import com.cajero.model.domain.Banco;
import com.cajero.model.domain.Cuenta;
import com.cajero.model.domain.Transaccion;
import com.cajero.model.domain.Usuario;
import com.cajero.model.domain.HijasTransaccion.Consignacion;
import com.cajero.model.domain.HijasTransaccion.Retiro;
import com.cajero.model.domain.HijasTransaccion.Transferencia;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuSesion {
    private Scanner sc;
    private Banco banco;

    public MenuSesion(Scanner sc, Banco banco) {
        this.sc = sc;
        this.banco = banco;
    }

    public void mostrarMenuSesion(Usuario usuarioActual, Cuenta cuentaActual) {
        boolean enSesion = true;
        while (enSesion) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║  Saldo: $" + String.format("%-20.2f", cuentaActual.getSaldo()) + "║");
            System.out.println("║  NroCuenta: " + String.format("%-17s", cuentaActual.getNumeroCuenta()) + "║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Consignar                ║");
            System.out.println("║  2. Retirar                  ║");
            System.out.println("║  3. Transferir               ║");
            System.out.println("║  4. Ver movimientos          ║");
            System.out.println("║  0. Cerrar sesión            ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Seleccione: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Monto a consignar: $");
                    double monto = leerDouble();
                    Transaccion t = new Consignacion(monto, cuentaActual);
                    t.ejecutar();
                }
                case 2 -> {
                    System.out.print("Monto a retirar: $");
                    double monto = leerDouble();
                    Transaccion t = new Retiro(monto, cuentaActual);
                    t.ejecutar();
                }
                case 3 -> {
                    System.out.print("Número de cuenta destino: ");
                    int nroDestino = leerEntero();
                    Cuenta cuentaDestino = banco.buscarCuenta(nroDestino);
                    if (cuentaDestino == null) {
                        System.out.println("Cuenta destino no encontrada.");
                        break;
                    }
                    if (nroDestino == cuentaActual.getNumeroCuenta()) {
                        System.out.println("No puede transferir a su propia cuenta.");
                        break;
                    }
                    System.out.print("Monto a transferir: $");
                    double monto = leerDouble();
                    Transaccion t = new Transferencia(monto, cuentaActual, cuentaDestino);
                    t.ejecutar();
                }
                case 4 -> {
                    ArrayList<String> historial = cuentaActual.getHistorial();
                    if (historial.isEmpty()) {
                        System.out.println("No hay movimientos registrados.");
                    } else {
                        System.out.println("\n--HISTORIAL DE MOVIMIENTOS--");
                        for (String mov : historial) {
                            System.out.println(mov);
                        }
                    }
                }
                case 0 -> {
                    System.out.println("Sesión cerrada. Hasta luego, " + usuarioActual.getNombre() + ".");
                    enSesion = false;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un monto válido: ");
            }
        }
    }
}
