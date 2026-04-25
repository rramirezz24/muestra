package com.cajero.UI;

import com.cajero.menuapp.MenuApp;
import com.cajero.model.domain.Banco;
import com.cajero.model.domain.Cuenta;
import com.cajero.model.domain.Usuario;

import java.util.Scanner;

public class MenuRegistro {
    private Scanner rm;
    private Banco banco;

    public MenuRegistro(Scanner rm, Banco banco) {
        this.rm = rm;
        this.banco = banco;
    }

    public void mostrarMenuRegistro(){
        System.out.println("\n--- REGISTRO DE NUEVO USUARIO ---");

        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = rm.nextLine();

        if (banco.existeUsuario(nombre)) {
            System.out.println("Ya existe un usuario con ese nombre. Intente con otro.");
            return;
        }

        System.out.print("Ingrese su ID: ");
        int id = rm.nextInt();

        System.out.print("Ingrese su contraseña: ");
        String contrasena = rm.next();

        // Crear usuario y cuenta
        Usuario nuevoUsuario = new Usuario(id, nombre, contrasena, true);
        Cuenta nuevaCuenta = new Cuenta(); // genera número aleatorio y saldo=0

        // Vincular cuenta al usuario
        nuevoUsuario.setNumeroCuenta(nuevaCuenta.getNumeroCuenta());

        // Registrar en el banco
        banco.registrarUsuario(nuevoUsuario);
        banco.registrarCuenta(nuevaCuenta);

        System.out.println("Registro exitoso. Su número de cuenta es: " + nuevaCuenta.getNumeroCuenta());
    }
}
