package com.digitalers.enums;

public enum MessageEnum {
	INCORRECT_KEYS("Las claves no coinciden", "DarkRed"), INCORRECT_EMAIL("El Correo ya existe", "DarkRed"),
	INCORRECT_CREDENTIALS("Credenciales Incorrectas, por favor intente nuevamente", "DarkRed"),
	INACTIVE_USER("El usuario esta inactivo, por favor comuniquese con el administrador", "Tomato"),
	CLOSE_SESSION("Ha cerrado correctamente la sesion", "SeaGreen");

	String message;
	String color;

	MessageEnum(String message, String color) {
		this.message = message;
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public String getColor() {
		return color;
	}
}
