package com.cajero.model.domain.HijasTransaccion;

import com.cajero.model.domain.Cuenta;
import com.cajero.model.domain.Transaccion;

public class Transferencia extends Transaccion {
    private final Cuenta cuentaDestino;

    @Override
    public boolean ejecutar() {
        //ESPACIO DONDE VAN A SOBREESCRIBIR EL METODO EJECUTAR DE LA CLASE ABSTRACTA TRANSACCIÓN (PRINCIPIO DE HERENCIA Y POLIMORFISMO)
    }
}
