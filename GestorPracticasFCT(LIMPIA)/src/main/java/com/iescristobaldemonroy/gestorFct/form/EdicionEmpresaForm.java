package com.iescristobaldemonroy.gestorFct.form;

import com.iescristobaldemonroy.gestorFct.entity.Empresa;

public class EdicionEmpresaForm {
	private String cif;
	private String denominacion;
	private String gustos;
	private String telefono;

	private Empresa empresa;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getGustos() {
		return gustos;
	}

	public void setGustos(String gustos) {
		this.gustos = gustos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.cif = empresa.getCif();
		this.denominacion = empresa.getDenominacion();
		this.gustos = empresa.getGustos();
		this.telefono = empresa.getTelefono();

		this.empresa = empresa;
	}

}
