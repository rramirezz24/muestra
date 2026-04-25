package com.cajero.model.domain;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Transaccion  {
    protected int id;
    protected double montoTransaccion;
    protected String fecha;
    protected Cuenta cuenta;
    
    public Transaccion(double montoTransaccion, Cuenta cuenta) {
        this.id = 0;
        this.montoTransaccion = montoTransaccion;
        this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.cuenta = cuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract boolean ejecutar();

    protected boolean validarMonto(){
     if (montoTransaccion<=0){
         System.out.println("El monto debe de ser mayor a $0");
         return false;
     }
     return true;
    }

    protected boolean validarMontoMayorSaldo(){
        if (montoTransaccion > cuenta.getSaldo()){
            System.out.println("Saldo insuficiente");
            return false;
        }
        return true;
    }
}
