package com.cajero.menuapp;

import com.cajero.UI.MenuLogin;
import com.cajero.UI.MenuPrincipal;
import com.cajero.UI.MenuRegistro;
import com.cajero.UI.MenuSesion;
import com.cajero.model.domain.*;
import com.cajero.model.domain.HijasTransaccion.Consignacion;
import com.cajero.model.domain.HijasTransaccion.Retiro;
import com.cajero.model.domain.HijasTransaccion.Transferencia;

import java.util.ArrayList;
import java.util.Scanner;

//Menuapp se encarga de toda la logica y la interacción con el usuario
//Controla los flujos de cada movimiento llamando a metodos de otras clases
public class MenuApp {

    private final Scanner rm;
    private final Banco banco;
    private final Autenticacion autenticacion;

    private Usuario usuarioActual;
    private Cuenta cuentaActual;
    private boolean sesionActiva;

    private MenuLogin menuLogin;
    private MenuPrincipal menuPrincipal ;
    private MenuRegistro menuRegistro;
    private MenuSesion menuSesion;

    public MenuApp() {
        this.rm = new Scanner(System.in);
        this.banco = new Banco();
        this.autenticacion = new Autenticacion();
        this.sesionActiva = false;

        this.menuPrincipal = new MenuPrincipal();
        this.menuRegistro = new MenuRegistro(rm, banco);
        this.menuLogin = new MenuLogin(rm, banco, autenticacion);
        this.menuSesion = new MenuSesion(rm,banco);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // PUNTO DE ENTRADA
    // ─────────────────────────────────────────────────────────────────────────

    //Inicia la app y con el ciclo la mantiene activa
    public void iniciar() {
        boolean ejecutando = true;
        while (ejecutando) {
            ejecutarMenuPrincipal();
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> flujoIniciarSesion() ;
                case 2 -> flujoRegistrarse();
                case 0 -> {
                    System.out.println("¡Hasta luego!");
                    ejecutando = false;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        rm.close();
    }

    // FLUJOS PRINCIPALES

    //Flujo de registro de Usuario
    private void flujoRegistrarse() {
        menuRegistro.mostrarMenuRegistro();
    }

    //Flujo de inicio de Sesión
    private void flujoIniciarSesion() {
        Usuario u = menuLogin.mostrarMenuLogin();
        if (u != null){
            Cuenta c = banco.buscarCuenta(u.getNumeroCuenta());
            menuSesion.mostrarMenuSesion(u,c);
        }
    }


    // FLUJOS DE TRANSACCIONES


    //Flujo para consignar la cuenta
    private void flujoConsignar() {
       //ESPACIO DONDE VAN A HACER EL FLUJO DE CONSIGNAR
    }

    //Flujo para retirar dinero de la cuenta
    private void flujoRetirar() {
        //ESPACIO DONDE VAN A HACER EL FLUJO DE RETIRAR
    }

    //Flujo para transferir dinero a otra cuenta
    private void flujoTransferir() {
        System.out.println("Ingrese el monto a Transferir.");
        double monto = leerDouble();
        System.out.println("Ingrese la cuenta de destino.");
        int cuentaDestino = leerEntero();
        Transaccion t = new Transferencia(monto,cuentaActual,banco.buscarCuenta(cuentaDestino));
    }

    //Flujo para consultar movimientos
    private void flujoConsultarMovimientos() {
        ArrayList<Movimiento> historial = cuentaActual.getHistorial();
        if(historial.isEmpty()){
            System.out.println("No hay movimientos registrados.");
            return;
        }
        System.out.println("--HISTORIAL DE MOVIMIENTOS--\n");
        for(Movimiento movimiento : historial){
            System.out.println(movimiento);
        }
        return;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // MENÚS (VISTAS)
    // ─────────────────────────────────────────────────────────────────────────

    private void ejecutarMenuPrincipal() {
        menuPrincipal.mostrarMenuPrincipal();
    }


    // UTILIDADES

    private void cerrarSesion() {
        sesionActiva = false;
        usuarioActual = null;
        cuentaActual = null;
    }

    //leer entero de manera segura
    public int leerEntero() {
        while (true) {
            try {
                int valor = Integer.parseInt(rm.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un número válido: ");
            }
        }
    }

    //leer double de manera segura
    public double leerDouble() {
        while (true) {
            try {
                double valor = Double.parseDouble(rm.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un monto válido: ");
            }
        }
    }
}
