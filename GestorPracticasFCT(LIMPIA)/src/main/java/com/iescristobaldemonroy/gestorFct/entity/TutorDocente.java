package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TUTOR_DOCENTE database table.
 * 
 */
@Entity
@Table(name = "TUTOR_DOCENTE")
@NamedQuery(name = "TutorDocente.findAll", query = "SELECT t FROM TutorDocente t")
public class TutorDocente extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String contrasenia;

	// bi-directional many-to-one association to Practica
	@OneToMany(mappedBy = "tutorDocente")
	private List<Practica> practicas;

	// uni-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name = "dni")
	private Persona persona;

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

	public List<Practica> getPracticas1() {
		return this.practicas;
	}

	public void setPracticas1(List<Practica> practicas1) {
		this.practicas = practicas1;
	}

	public Practica addPracticas(Practica practicas) {
		getPracticas1().add(practicas);
		practicas.setTutorDocente(this);

		return practicas;
	}

	public Practica removePracticas1(Practica practicas1) {
		getPracticas1().remove(practicas1);
		practicas1.setTutorDocente(null);

		return practicas1;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}