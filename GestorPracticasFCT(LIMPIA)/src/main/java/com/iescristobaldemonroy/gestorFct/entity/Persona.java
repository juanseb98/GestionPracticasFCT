package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the PERSONA database table.
 * 
 */
@Entity
@Table(name = "PERSONA")
@NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String contrasenia;

	@Column(name = "DTYPE")
	private String dtype;

	private String nombre;

	// bi-directional one-to-one association to Administrador
	@OneToOne(mappedBy = "persona", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private Administrador administrador;

	// bi-directional one-to-one association to Alumno
	@OneToOne(mappedBy = "persona", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private Alumno alumno;

	// bi-directional one-to-one association to Representante
	@OneToOne(mappedBy = "persona", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private Representante representante;

	// bi-directional one-to-one association to TutorDocente
	@OneToOne(mappedBy = "persona", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private TutorDocente tutorDocente;

	// bi-directional one-to-one association to TutorLaboral
	@OneToOne(mappedBy = "persona", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private TutorLaboral tutorLaboral;

	// bi-directional many-to-one association to Valoracion
	@OneToMany(mappedBy = "persona")
	private List<Valoracion> valoracions;

	public Persona() {
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

	public String getDtype() {
		return this.dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
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

	public List<Valoracion> getValoracions() {
		return this.valoracions;
	}

	public void setValoracions(List<Valoracion> valoracions) {
		this.valoracions = valoracions;
	}

	public Valoracion addValoracion(Valoracion valoracion) {
		getValoracions().add(valoracion);
		valoracion.setPersona(this);

		return valoracion;
	}

	public Valoracion removeValoracion(Valoracion valoracion) {
		getValoracions().remove(valoracion);
		valoracion.setPersona(null);

		return valoracion;
	}

}