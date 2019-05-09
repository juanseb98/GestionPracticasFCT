package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CENTRO_TRABAJO database table.
 * 
 */
@Entity
@Table(name="CENTRO_TRABAJO")
@NamedQuery(name="CentroTrabajo.findAll", query="SELECT c FROM CentroTrabajo c")
public class CentroTrabajo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String calle;

	private int codigoPostal;

	private String descripcion;

	private String email;

	private int escalera;

	private String letra;

	private String localidad;

	private String municipio;

	private int numero;

	private int piso;

	private byte principal;

	private String provincia;

	private int telefono;

	private String tipoVia;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="cifEmpresa")
	private Empresa empresa;

	public CentroTrabajo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEscalera() {
		return this.escalera;
	}

	public void setEscalera(int escalera) {
		this.escalera = escalera;
	}

	public String getLetra() {
		return this.letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPiso() {
		return this.piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public byte getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(byte principal) {
		this.principal = principal;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getTipoVia() {
		return this.tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}