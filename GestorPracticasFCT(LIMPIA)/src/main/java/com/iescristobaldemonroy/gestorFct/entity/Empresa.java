package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EMPRESA database table.
 * 
 */
@Entity
@Table(name="EMPRESA")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cif;

	private String denominacion;

	private String gustos;

	private String telefono;

	//bi-directional many-to-one association to CentroTrabajo
	@OneToMany(mappedBy="empresa")
	private List<CentroTrabajo> centroTrabajos;

	//bi-directional many-to-one association to Representante
	@OneToMany(mappedBy="empresa")
	private List<Representante> representantes;

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

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
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

}