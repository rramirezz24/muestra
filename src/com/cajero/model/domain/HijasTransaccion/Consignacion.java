package com.cajero.model.domain.HijasTransaccion;

import com.cajero.model.domain.Cuenta;
import com.cajero.model.domain.Transaccion;

public class Consignacion extends Transaccion {


    @Override
    public boolean ejecutar() {
        public boolean ejecutar() {
        if (!validarMonto()) return false;
        cuenta.setSaldo(montoTransaccion+cuenta.getSaldo());
        cuenta.agregarMovimiento(id+1 + " - RETIRO - +" + montoTransaccion);
        System.out.println("Consignación de: $" + montoTransaccion + " realizada con éxito." +
                "\nSaldo actual: " + cuenta.getSaldo());
        return true;
}
