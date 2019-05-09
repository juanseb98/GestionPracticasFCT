package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the PERSONA_CONTACTO database table.
 * 
 */
@Entity
@Table(name = "PERSONA_CONTACTO")
@NamedQuery(name = "PersonaContacto.findAll", query = "SELECT p FROM PersonaContacto p")
public class PersonaContacto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private String nombre;

	private int telefono;

	// bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name = "cifEmpresa")
	private Empresa empresa;

	public PersonaContacto(String email, String nombre, int telefono, Empresa empresa) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.telefono = telefono;
		this.empresa = empresa;
	}

	public PersonaContacto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}