package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the INTERESADO database table.
 * 
 */
@Entity
@Table(name = "INTERESADO")
@NamedQuery(name = "Interesado.findAll", query = "SELECT i FROM Interesado i")
public class Interesado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private String nombre;

	private String nombreEmpresa;

	private String telefono;

	public Interesado() {
	}

	public Interesado(String email, String nombre, String nombreEmpresa, String telefono) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.nombreEmpresa = nombreEmpresa;
		this.telefono = telefono;
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

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}