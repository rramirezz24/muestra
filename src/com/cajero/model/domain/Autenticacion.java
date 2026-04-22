package com.cajero.model.domain;

import java.time.LocalDateTime;

public class Autenticacion {

    private static final int MAX_INTENTOS = 3;
    private static final int HORAS_BLOQUEO = 24;

    private int intentos;
    private boolean bloqueado;
    private LocalDateTime tiempoBloqueo;

    public Autenticacion() {
        this.intentos = 0;
        this.bloqueado = false;
        this.tiempoBloqueo = null;
    }

    //login con usuario y contraseña
    public boolean login(Usuario usuario, String contrasena) {
        if (estaBloqueado()) {
            System.out.println("Cuenta bloqueada. Intente después de " + HORAS_BLOQUEO + " horas.");
            return false;
        }
        if (usuario.getContrasena().equals(contrasena)) {
            resetIntentos();
            System.out.println("¡Inicio de sesión exitoso! Bienvenido, " + usuario.getNombre() + ".");
            return true;
        } else {
            registrarIntento();
            int restantes = MAX_INTENTOS - intentos;
            if (restantes > 0) {
                System.out.println("Contraseña incorrecta. Intentos restantes: " + restantes);
            }
            return false;
        }
    }

    //Verifica si la cuenta actualmente esta bloqueada y si ya paso el plazo de 24h, desbloquearla y hacer el reset de intentos
    public boolean estaBloqueado() {
        if (bloqueado && tiempoBloqueo != null) {
            if (LocalDateTime.now().isAfter(tiempoBloqueo.plusHours(HORAS_BLOQUEO))) {
                desbloquear();
                System.out.println("El bloqueo ha expirado. Ya puede iniciar sesión.");
                return false;
            }
            return true;
        }
        return false;
    }

    // ─── Métodos privados auxiliares ──────────────────────────────────────────

    private void registrarIntento() {
        intentos++;
        if (intentos >= MAX_INTENTOS) {
            bloquear();
        }
    }

    private void bloquear() {
        bloqueado = true;
        tiempoBloqueo = LocalDateTime.now();
        System.out.println("Cuenta bloqueada por exceso de intentos. Intente nuevamente en "
                + HORAS_BLOQUEO + " horas.");
    }

    private void desbloquear() {
        bloqueado = false;
        intentos = 0;
        tiempoBloqueo = null;
    }

    private void resetIntentos() {
        intentos = 0;
    }
}
