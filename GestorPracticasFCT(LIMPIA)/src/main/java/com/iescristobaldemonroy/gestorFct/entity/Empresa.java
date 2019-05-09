package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the EMPRESA database table.
 * 
 */
@Entity
@Table(name = "EMPRESA")
@NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cif;

	private String denominacion;

	private String gustos;

	private int telefono;

	// bi-directional many-to-one association to CentroTrabajo
	@OneToMany(mappedBy = "empresa")
	private List<CentroTrabajo> centroTrabajos;

	// bi-directional many-to-one association to PersonaContacto
	@OneToMany(mappedBy = "empresa")
	private List<PersonaContacto> personaContactos;

	// bi-directional many-to-one association to Practica
	@OneToMany(mappedBy = "empresa")
	private List<Practica> practicas;

	// bi-directional many-to-one association to Representante
	@OneToMany(mappedBy = "empresa")
	private List<Representante> representantes;

	// bi-directional many-to-one association to TutorLaboral
	@OneToMany(mappedBy = "empresa")
	private List<TutorLaboral> tutorLaborals;

	public Empresa(String cif, String denominacion) {
		super();
		this.cif = cif;
		this.denominacion = denominacion;
	}

	public Empresa() {
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getGustos() {
		return this.gustos;
	}

	public void setGustos(String gustos) {
		this.gustos = gustos;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public List<CentroTrabajo> getCentroTrabajos() {
		return this.centroTrabajos;
	}

	public void setCentroTrabajos(List<CentroTrabajo> centroTrabajos) {
		this.centroTrabajos = centroTrabajos;
	}

	public CentroTrabajo addCentroTrabajo(CentroTrabajo centroTrabajo) {
		getCentroTrabajos().add(centroTrabajo);
		centroTrabajo.setEmpresa(this);

		return centroTrabajo;
	}

	public CentroTrabajo removeCentroTrabajo(CentroTrabajo centroTrabajo) {
		getCentroTrabajos().remove(centroTrabajo);
		centroTrabajo.setEmpresa(null);

		return centroTrabajo;
	}

	public List<PersonaContacto> getPersonaContactos() {
		return this.personaContactos;
	}

	public void setPersonaContactos(List<PersonaContacto> personaContactos) {
		this.personaContactos = personaContactos;
	}

	public PersonaContacto addPersonaContacto(PersonaContacto personaContacto) {
		getPersonaContactos().add(personaContacto);
		personaContacto.setEmpresa(this);

		return personaContacto;
	}

	public PersonaContacto removePersonaContacto(PersonaContacto personaContacto) {
		getPersonaContactos().remove(personaContacto);
		personaContacto.setEmpresa(null);

		return personaContacto;
	}

	public List<Practica> getPracticas() {
		return this.practicas;
	}

	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}

	public Practica addPractica(Practica practica) {
		getPracticas().add(practica);
		practica.setEmpresa(this);

		return practica;
	}

	public Practica removePractica(Practica practica) {
		getPracticas().remove(practica);
		practica.setEmpresa(null);

		return practica;
	}

	public List<Representante> getRepresentantes() {
		return this.representantes;
	}

	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}

	public Representante addRepresentante(Representante representante) {
		getRepresentantes().add(representante);
		representante.setEmpresa(this);

		return representante;
	}

	public Representante removeRepresentante(Representante representante) {
		getRepresentantes().remove(representante);
		representante.setEmpresa(null);

		return representante;
	}

	public List<TutorLaboral> getTutorLaborals() {
		return this.tutorLaborals;
	}

	public void setTutorLaborals(List<TutorLaboral> tutorLaborals) {
		this.tutorLaborals = tutorLaborals;
	}

	public TutorLaboral addTutorLaboral(TutorLaboral tutorLaboral) {
		getTutorLaborals().add(tutorLaboral);
		tutorLaboral.setEmpresa(this);

		return tutorLaboral;
	}

	public TutorLaboral removeTutorLaboral(TutorLaboral tutorLaboral) {
		getTutorLaborals().remove(tutorLaboral);
		tutorLaboral.setEmpresa(null);

		return tutorLaboral;
	}

}