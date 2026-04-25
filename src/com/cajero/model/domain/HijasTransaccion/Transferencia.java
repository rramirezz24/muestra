package com.cajero.model.domain.HijasTransaccion;

import com.cajero.model.domain.Cuenta;
import com.cajero.model.domain.Transaccion;

public class Transferencia extends Transaccion {

    private final Cuenta cuentaDestino;

    public Transferencia(double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        super(monto, cuentaOrigen);
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public boolean ejecutar() {

        if(!validarMonto()) return false;
        if (!validarMontoMayorSaldo()) return false;
        cuenta.setSaldo(cuenta.getSaldo()-montoTransaccion);
        cuentaDestino.setSaldo(cuenta.getSaldo()+montoTransaccion);
        cuenta.agregarMovimiento(id+1 + "- TRANSFERENCIA A: " + cuentaDestino.getNumeroCuenta() + "- -" + montoTransaccion);
        System.out.println("Transferencia de: $" + montoTransaccion + " realizada con Ã©xito" + "\ndestino: " + cuentaDestino.getNumeroCuenta());
        return true;
    }
}
