package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ADMINISTRADOR database table.
 * 
 */
@Entity
@Table(name = "ADMINISTRADOR")
@NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
public class Administrador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String contrasenia;

	// bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name = "dni")
	private Persona persona;

	public Administrador() {
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

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}