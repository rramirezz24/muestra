package com.cajero.model.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Banco {

    private Map<String, Usuario> usuarios;
    private Map<Integer, Cuenta> cuentas;
    private Scanner rm;

    public Banco() {
        this.usuarios = new HashMap<>();
        this.cuentas = new HashMap<>();
    }

   //registrar nuevo usuario y enviarlo al Hashmap usuarios
    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNombre(), usuario);
    }



    //registra una nueva cuenta y la envia al HashMap cuentas
    public void registrarCuenta(Cuenta cuenta) {
        cuentas.put(cuenta.getNumeroCuenta(), cuenta);
    }

    //Busca un usuario por el nombre en el HashMap usuarios
    public Usuario buscarUsuario(String nombre) {
        return usuarios.get(nombre);
    }

    //Busca una cuenta por el nro de Cuenta en el HashMap
    public Cuenta buscarCuenta(int numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }

    //Busca en el mapa usuarios si el usuario ya existe
    public boolean existeUsuario(String nombre) {
        return usuarios.containsKey(nombre);
    }
}
