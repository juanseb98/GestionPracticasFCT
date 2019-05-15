package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ALUMNO database table.
 * 
 */
@Entity
@Table(name="ALUMNO")
@NamedQuery(name="Alumno.findAll", query="SELECT a FROM Alumno a")
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String contrasenia;

	//bi-directional one-to-one association to Persona
	@OneToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	@JoinColumn(name="dni")
	private Persona persona;

	//bi-directional many-to-one association to Practica
	@OneToMany(mappedBy="alumno")
	private List<Practica> practicas;

	public Alumno() {
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

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Practica> getPracticas() {
		return this.practicas;
	}

	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}

	public Practica addPractica(Practica practica) {
		getPracticas().add(practica);
		practica.setAlumno(this);

		return practica;
	}

	public Practica removePractica(Practica practica) {
		getPracticas().remove(practica);
		practica.setAlumno(null);

		return practica;
	}

}