package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PERSONA database table.
 * 
 */
@Entity
@Table(name="PERSONA")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String nombre;

	//bi-directional one-to-one association to Administrador
	@OneToOne(mappedBy="persona")
	private Administrador administrador;

	//bi-directional one-to-one association to Alumno
	@OneToOne(mappedBy="persona")
	private Alumno alumno;

	//bi-directional one-to-one association to Representante
	@OneToOne(mappedBy="persona")
	private Representante representante;

	//bi-directional one-to-one association to TutorDocente
	@OneToOne(mappedBy="persona")
	private TutorDocente tutorDocente;

	//bi-directional one-to-one association to TutorLaboral
	@OneToOne(mappedBy="persona")
	private TutorLaboral tutorLaboral;

	public Persona() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Administrador getAdministrador() {
		return this.administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Representante getRepresentante() {
		return this.representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public TutorDocente getTutorDocente() {
		return this.tutorDocente;
	}

	public void setTutorDocente(TutorDocente tutorDocente) {
		this.tutorDocente = tutorDocente;
	}

	public TutorLaboral getTutorLaboral() {
		return this.tutorLaboral;
	}

	public void setTutorLaboral(TutorLaboral tutorLaboral) {
		this.tutorLaboral = tutorLaboral;
	}

}