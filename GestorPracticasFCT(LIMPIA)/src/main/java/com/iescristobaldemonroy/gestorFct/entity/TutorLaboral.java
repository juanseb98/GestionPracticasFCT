package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TUTOR_LABORAL database table.
 * 
 */
@Entity
@Table(name="TUTOR_LABORAL")
@NamedQuery(name="TutorLaboral.findAll", query="SELECT t FROM TutorLaboral t")
public class TutorLaboral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String email;

	private String telefono;

	//uni-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="cifEmpresa")
	private Empresa empresa;

	public TutorLaboral() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}