package com.cajero.UI;

import com.cajero.model.domain.Autenticacion;
import com.cajero.model.domain.Banco;
import com.cajero.model.domain.Usuario;

import java.util.Scanner;

public class MenuLogin {
    private Scanner rm;
    private Banco banco;
    private Autenticacion autenticacion;

    public MenuLogin(Scanner rm, Banco banco, Autenticacion autenticacion) {
        this.rm = rm;
        this.banco = banco;
        this.autenticacion = autenticacion;
    }

    public Usuario mostrarMenuLogin() {
        System.out.println("\n=== INICIO DE SESIÓN ===");
        if (autenticacion.estaBloqueado()) {
            System.out.println("Su cuenta está bloqueada. Intente después de 24 horas.");
            return null;
        }

        System.out.print("Nombre de usuario: ");
        String nombre = rm.nextLine();

        Usuario encontrado = banco.buscarUsuario(nombre);
        if (encontrado == null) {
            System.out.println("Usuario no encontrado.");
            return null;
        }

        System.out.print("Contraseña: ");
        String contrasena = rm.nextLine();

        boolean loginExitoso = autenticacion.login(encontrado, contrasena);

        if (loginExitoso) {
            return encontrado; // retorna el usuario para que MenuApp llame a MenuSesion
        }
        return null;
    }
}
