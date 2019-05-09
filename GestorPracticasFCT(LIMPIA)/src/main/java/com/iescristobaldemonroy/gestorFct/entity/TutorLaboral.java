package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TUTOR_LABORAL database table.
 * 
 */
@Entity
@Table(name = "TUTOR_LABORAL")
@NamedQuery(name = "TutorLaboral.findAll", query = "SELECT t FROM TutorLaboral t")
public class TutorLaboral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String email;

	private int telefono;

	// bi-directional many-to-one association to Practica
	@OneToMany(mappedBy = "tutorLaboral")
	private List<Practica> practicas;

	// bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name = "cifEmpresa")
	private Empresa empresa;

	// bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name = "dni")
	private Persona persona;

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

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public List<Practica> getPracticas() {
		return this.practicas;
	}

	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}

	public Practica addPractica(Practica practica) {
		getPracticas().add(practica);
		practica.setTutorLaboral(this);

		return practica;
	}

	public Practica removePractica(Practica practica) {
		getPracticas().remove(practica);
		practica.setTutorLaboral(null);

		return practica;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}