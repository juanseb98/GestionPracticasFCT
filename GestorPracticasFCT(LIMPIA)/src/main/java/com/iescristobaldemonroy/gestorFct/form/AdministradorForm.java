package com.iescristobaldemonroy.gestorFct.form;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Administrador;

public class AdministradorForm {

	private String dni;
	private String contrasenia;
	private String nombre;
	private int numeroNotificaciones;
	private boolean log;
	private String notificacionSelected;

	public AdministradorForm() {
		this.log = false;
	}

	public AdministradorForm(Administrador admin) {
		this.dni = admin.getDni();
		this.contrasenia = admin.getContrasenia();
		this.nombre = admin.getPersona().getNombre();
		this.log = true;

	}

	public String getNotificacionSelected() {
		return notificacionSelected;
	}

	public void setNotificacionSelected(String notificacionSelected) {
		this.notificacionSelected = notificacionSelected;
	}

	public boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}

	public AdministradorForm(Administrador admin, int notificaciones) {
		this.dni = admin.getDni();
		this.contrasenia = admin.getContrasenia();
		this.nombre = admin.getPersona().getNombre();
		this.numeroNotificaciones = notificaciones;
		this.log = true;

	}

	public int getNumeroNotificaciones() {
		return numeroNotificaciones;
	}

	public void setNumeroNotificaciones(int numeroNotificaciones) {
		this.numeroNotificaciones = numeroNotificaciones;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
