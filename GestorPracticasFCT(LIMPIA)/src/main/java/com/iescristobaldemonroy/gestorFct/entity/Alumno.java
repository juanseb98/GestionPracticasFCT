package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ALUMNO database table.
 * 
 */
@Entity
@Table(name = "ALUMNO")
@NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a")
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String contrasenia;

	public Alumno() {
	}

	public Alumno(String dni, String contrasenia) {
		this.dni = dni;
		this.contrasenia = contrasenia;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}