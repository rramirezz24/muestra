package com.cajero.model.domain;

import java.util.ArrayList;

public class Cuenta {

    private int numeroCuenta;
    private double saldo;

    // HISTORIAL DE MOVIMIENTOS
    private ArrayList<String> historial = new ArrayList<>();

    //metodo para agregar Movimientos al ArrayList Historial
    public void agregarMovimiento(String movimiento){
        historial.add(movimiento);
    }

    public Cuenta() {
        this.numeroCuenta = (int) (Math.random() * 900000) + 100000;
        this.saldo = 0;
        this.historial = new ArrayList<>();
    }

    public Cuenta(int numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.historial = new ArrayList<>();
    }

    // Getters y Setters
    public int getNumeroCuenta() { return numeroCuenta; }

    public double getSaldo() { return saldo; }

    public void setSaldo(double saldo) { this.saldo = saldo; }

    public <Movimiento> ArrayList<Movimiento> getHistorial() {
        
        return (ArrayList<Movimiento>) historial;
    }
}
