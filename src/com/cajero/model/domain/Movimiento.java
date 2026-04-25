package com.cajero.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {

    private String descripcion;
    private String fecha;
    private double monto;
    private double saldoPosterior;

    public Movimiento(String descripcion, double monto, double saldoPosterior) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.saldoPosterior = saldoPosterior;
        this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return fecha + " | " + descripcion + " | Monto: $" + monto + " | Saldo: $" + saldoPosterior;
    }
}

class CuentaBancaria {
    private String numeroCuenta;
    private double saldo;
    private List<Movimiento> movimientos;

    public CuentaBancaria(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.movimientos = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
        movimientos.add(new Movimiento("Depósito", monto, saldo));
    }

    public boolean retirar(double monto) {
        if (monto > saldo) {
            System.out.println("❌ Fondos insuficientes.");
            return false;
        }
        saldo -= monto;
        movimientos.add(new Movimiento("Retiro", monto, saldo));
        return true;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }
}

class Cajero {
    private CuentaBancaria cuenta;

    public Cajero(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    public void realizarDeposito(double monto) {
        cuenta.depositar(monto);
        System.out.println("✔ Depósito realizado.");
    }

    public void realizarRetiro(double monto) {
        if (cuenta.retirar(monto)) {
            System.out.println("✔ Retiro realizado.");
        }
    }

    public void mostrarHistorial() {
        System.out.println("\n📄 HISTORIAL DE MOVIMIENTOS:");
        for (Movimiento m : cuenta.getMovimientos()) {
            System.out.println(m);
        }
    }

    public void mostrarSaldo() {
        System.out.println("💰 Saldo actual: $" + cuenta.getSaldo());
    }
}

public class Main {
    public static void main(String[] args) {

        CuentaBancaria cuenta = new CuentaBancaria("12345", 100000);
        Cajero cajero = new Cajero(cuenta);

        cajero.realizarDeposito(50000);
        cajero.realizarRetiro(20000);
        cajero.realizarDeposito(15000);
        cajero.realizarRetiro(100000);

        cajero.mostrarSaldo();
        cajero.mostrarHistorial();
    }
}