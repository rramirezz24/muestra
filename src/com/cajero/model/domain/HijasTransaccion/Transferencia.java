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

        if (!validarMonto() || !validarMontoMayorSaldo()) {
            return false;
        }

        // Descontar saldo de la cuenta origen
        cuenta.setSaldo(cuenta.getSaldo() - montoTransaccion);
        cuenta.agregarMovimiento(
                "Transferencia enviada a cuenta " + cuentaDestino.getNumeroCuenta(),
                montoTransaccion
        );

        // Sumar saldo a la cuenta destino
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + montoTransaccion);
        cuentaDestino.agregarMovimiento(
                "Transferencia recibida desde cuenta " + cuenta.getNumeroCuenta(),
                montoTransaccion
        );

        return true;
    }
}
