package com.iescristobaldemonroy.gestorFct.form;

import com.iescristobaldemonroy.gestorFct.entity.CentroTrabajo;

public class EdicionCentroTrabajoForm {

	private int id;
	private String calle;
	private String codigoPostal;
	private String descripcion;
	private String email;
	private String escalera;
	private String letra;
	private String localidad;
	private String municipio;
	private String numero;
	private String piso;
	private String principal;
	private String provincia;
	private String telefono;
	private String tipoVia;
	private CentroTrabajo centroTrabajo;

	public void setCentroTrabajo(CentroTrabajo centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
		if (centroTrabajo != null) {
			this.id = centroTrabajo.getId();
			this.calle = centroTrabajo.getCalle();
			this.codigoPostal = centroTrabajo.getCodigoPostal();
			this.descripcion = centroTrabajo.getDescripcion();
			this.email = centroTrabajo.getEmail();
			this.escalera = centroTrabajo.getEscalera();
			this.letra = centroTrabajo.getLetra();
			this.localidad = centroTrabajo.getLocalidad();
			this.municipio = centroTrabajo.getMunicipio();
			this.numero = centroTrabajo.getNumero();
			this.piso = centroTrabajo.getPiso();
			this.principal = centroTrabajo.getPrincipal();
			this.provincia = centroTrabajo.getProvincia();
			this.telefono = centroTrabajo.getTelefono();
			this.tipoVia = centroTrabajo.getTipoVia();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CentroTrabajo getCentroTrabajo() {
		return centroTrabajo;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

}
