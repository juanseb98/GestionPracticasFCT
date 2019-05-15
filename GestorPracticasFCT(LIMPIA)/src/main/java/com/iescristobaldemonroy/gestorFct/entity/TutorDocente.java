package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to Practica
	@OneToMany(mappedBy="tutorDocente")
	private List<Practica> practicas;

	//bi-directional one-to-one association to Persona
	@OneToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	@JoinColumn(name="dni")
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

	public List<Practica> getPracticas() {
		return this.practicas;
	}

	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}

	public Practica addPractica(Practica practica) {
		getPracticas().add(practica);
		practica.setTutorDocente(this);

		return practica;
	}

	public Practica removePractica(Practica practica) {
		getPracticas().remove(practica);
		practica.setTutorDocente(null);

		return practica;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}