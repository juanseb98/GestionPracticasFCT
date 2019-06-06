package com.iescristobaldemonroy.gestorFct.form;

public class EditarCentroTrabajoForm {

	private String filtroCalle;
	private String filtroCodigoPostal;
	private String filtroNumero;
	private String filtroLocalidad;
	private String filtroMunicipio;
	private String filtroProvincia;
	private Boolean filtroPrincipal;
	private String operacion;

	public void limpiarFiltros() {
		this.filtroCalle = null;
		this.filtroCodigoPostal = null;
		this.filtroNumero = null;
		this.filtroLocalidad = null;
		this.filtroMunicipio = null;
		this.filtroProvincia = null;
		this.filtroPrincipal = null;
		this.operacion = "";
	}

	public String getFiltroCalle() {
		return filtroCalle;
	}

	public void setFiltroCalle(String filtroCalle) {
		this.filtroCalle = filtroCalle;
	}

	public String getFiltroCodigoPostal() {
		return filtroCodigoPostal;
	}

	public void setFiltroCodigoPostal(String filtroCodigoPostal) {
		this.filtroCodigoPostal = filtroCodigoPostal;
	}

	public String getFiltroNumero() {
		return filtroNumero;
	}

	public void setFiltroNumero(String filtroNumero) {
		this.filtroNumero = filtroNumero;
	}

	public String getFiltroLocalidad() {
		return filtroLocalidad;
	}

	public void setFiltroLocalidad(String filtroLocalidad) {
		this.filtroLocalidad = filtroLocalidad;
	}

	public String getFiltroMunicipio() {
		return filtroMunicipio;
	}

	public void setFiltroMunicipio(String filtroMunicipio) {
		this.filtroMunicipio = filtroMunicipio;
	}

	public String getFiltroProvincia() {
		return filtroProvincia;
	}

	public void setFiltroProvincia(String filtroProvincia) {
		this.filtroProvincia = filtroProvincia;
	}

	public Boolean getFiltroPrincipal() {
		return filtroPrincipal;
	}

	public void setFiltroPrincipal(Boolean filtroPrincipal) {
		this.filtroPrincipal = filtroPrincipal;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

}
