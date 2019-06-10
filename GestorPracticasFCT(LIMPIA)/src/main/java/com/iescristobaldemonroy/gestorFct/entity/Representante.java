package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the REPRESENTANTE database table.
 * 
 */
@Entity
@Table(name = "REPRESENTANTE")
@NamedQuery(name = "Representante.findAll", query = "SELECT r FROM Representante r")
public class Representante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	// bi-directional many-to-one association to Empresa
	@ManyToOne()
	@JoinColumn(name = "cifEmpresa")
	private Empresa empresa;

	// bi-directional one-to-one association to Persona
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	@JoinColumn(name = "dni")
	private Persona persona;

	public Representante() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}