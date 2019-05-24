package com.iescristobaldemonroy.gestorFct.form;

public class EditarEmpresaForm {
	private String filtroCif;
	private String filtroDenominacion;
	private String operacion;

	public void limpiarFiltros() {
		this.filtroCif = null;
		this.filtroDenominacion = null;
		this.operacion = "";
	}

	public String getFiltroCif() {
		return filtroCif;
	}

	public void setFiltroCif(String filtroCif) {
		this.filtroCif = filtroCif;
	}

	public String getFiltroDenominacion() {
		return filtroDenominacion;
	}

	public void setFiltroDenominacion(String filtroDenominacion) {
		this.filtroDenominacion = filtroDenominacion;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

}
