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

	private String nombre;

	// uni-directional one-to-one association to Administrador
	@OneToOne
	@JoinColumn(name = "dni")
	private Administrador administrador;

	// uni-directional one-to-one association to Alumno
	@OneToOne
	@JoinColumn(name = "dni")
	private Alumno alumno;

	// uni-directional one-to-one association to Representante
	@OneToOne
	@JoinColumn(name = "dni")
	private Representante representante;

	// uni-directional one-to-one association to TutorDocente
	@OneToOne
	@JoinColumn(name = "dni")
	private TutorDocente tutorDocente;

	// uni-directional one-to-one association to TutorLaboral
	@OneToOne
	@JoinColumn(name = "dni")
	private TutorLaboral tutorLaboral;

	// bi-directional many-to-one association to Valoracion
	@OneToMany(mappedBy = "persona")
	private List<Valoracion> valoraciones;

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

	public List<Valoracion> getValoraciones() {
		return this.valoraciones;
	}

	public void setValoraciones(List<Valoracion> valoracions) {
		this.valoraciones = valoracions;
	}

	public Valoracion addValoracion(Valoracion valoracion) {
		getValoraciones().add(valoracion);
		valoracion.setPersona(this);

		return valoracion;
	}

	public Valoracion removeValoracion(Valoracion valoracion) {
		getValoraciones().remove(valoracion);
		valoracion.setPersona(null);

		return valoracion;
	}

}