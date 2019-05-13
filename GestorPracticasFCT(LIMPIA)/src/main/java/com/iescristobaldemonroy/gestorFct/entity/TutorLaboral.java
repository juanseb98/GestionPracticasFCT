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

	private String telefono;

	// bi-directional many-to-one association to Practica
	@OneToMany(mappedBy = "tutorLaboral")
	private List<Practica> practicas;

	// bi-directional many-to-one association to Empresa
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cifEmpresa")
	private Empresa empresa;

	// uni-directional one-to-one association to Persona
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

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Practica> getPracticas1() {
		return this.practicas;
	}

	public void setPracticas1(List<Practica> practicas1) {
		this.practicas = practicas1;
	}

	public Practica addPracticas1(Practica practicas) {
		getPracticas1().add(practicas);
		practicas.setTutorLaboral(this);

		return practicas;
	}

	public Practica removePracticas1(Practica practicas1) {
		getPracticas1().remove(practicas1);
		practicas1.setTutorLaboral(null);

		return practicas1;
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