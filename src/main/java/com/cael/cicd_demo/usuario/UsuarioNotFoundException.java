package com.cael.cicd_demo.usuario;

public class UsuarioNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 5767250318793353477L;

	public UsuarioNotFoundException(Long id) {
        super("Usuario no encontrado con id: " + id);
    }
}