package com.iescristobaldemonroy.gestorFct.form;

public class EditarAlumnoForm {

	private String filtroDni;
	private String filtroNombre;
	private String filtroAnio;
	private String operacion;

	public void limpiarFiltros() {
		this.filtroDni = null;
		this.filtroNombre = null;
		this.filtroAnio = null;
		this.operacion = "";
	}

	public String getFiltroAnio() {
		return filtroAnio;
	}

	public void setFiltroAnio(String filtroAnio) {
		this.filtroAnio = filtroAnio;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getFiltroDni() {
		return filtroDni;
	}

	public void setFiltroDni(String filtroDni) {
		this.filtroDni = filtroDni;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

}
