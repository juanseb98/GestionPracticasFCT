package com.iescristobaldemonroy.gestorFct.form;

public class TestForm {

	private String filtroDni;
	private String filtroNombre;
	private String Operacion;

	public void limpiarFiltros() {
		this.filtroDni = null;
		this.filtroNombre = null;
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

	public String getOperacion() {
		return Operacion;
	}

	public void setOperacion(String operacion) {
		Operacion = operacion;
	}

}
