package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TUTOR_DOCENTE database table.
 * 
 */
@Entity
@Table(name="TUTOR_DOCENTE")
@NamedQuery(name="TutorDocente.findAll", query="SELECT t FROM TutorDocente t")
public class TutorDocente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String contrasenia;

	public TutorDocente() {
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