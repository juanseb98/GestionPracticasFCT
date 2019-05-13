package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.iescristobaldemonroy.gestorFct.constantes.Constantes;

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

	private String telefono;

	// bi-directional many-to-one association to Practica
	@OneToMany(mappedBy = "empresa")
	private List<Practica> practicas;

	// bi-directional many-to-one association to TutorLaboral
	@OneToMany(mappedBy = "empresa")
	private List<TutorLaboral> tutorLaborals;

	public Empresa() {
	}

	public Empresa(String cif, String denominacion) {
		super();
		this.cif = cif;
		this.denominacion = denominacion;
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

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
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
		practica.setEmpresa(this);

		return practica;
	}

	public Practica removePractica(Practica practica) {
		getPracticas().remove(practica);
		practica.setEmpresa(null);

		return practica;
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