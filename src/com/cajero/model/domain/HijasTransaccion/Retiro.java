package com.cajero.model.domain.HijasTransaccion;

import com.cajero.model.domain.Cuenta;
import com.cajero.model.domain.Transaccion;

public class Retiro extends Transaccion {
    public Retiro(double montoTransaccion, Cuenta cuenta) {
        super(montoTransaccion, cuenta);
    }

    @Override
    public boolean ejecutar() {
        if (!validarMonto()) return false;
        if (!validarMontoMayorSaldo()) return false;
        cuenta.setSaldo(cuenta.getSaldo()-montoTransaccion);
        cuenta.agregarMovimiento(id+1 + "- RETIRO - -" + montoTransaccion);
        System.out.println("Retiro de: $" + montoTransaccion + " se ha realizado con éxito." +
                "\n Saldo actual: " + cuenta.getSaldo());
        return true;

    }
}
