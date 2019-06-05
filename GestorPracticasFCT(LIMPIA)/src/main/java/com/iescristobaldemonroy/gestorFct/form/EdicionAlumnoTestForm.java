package com.iescristobaldemonroy.gestorFct.form;

import com.iescristobaldemonroy.gestorFct.entity.Persona;

public class EdicionAlumnoTestForm {

	private String nombre;
	private String dni;
	private String contrasenia;
	private String email;
	private String anioEstudio;
	private String telefono;
	private Persona persona;

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
		if (persona != null) {
			this.nombre = persona.getNombre();
			this.dni = persona.getDni();
			this.contrasenia = persona.getAlumno().getContrasenia();
			this.email = persona.getAlumno().getEmail();
			this.telefono = persona.getAlumno().getTelefono();
			this.anioEstudio = persona.getAlumno().getAnioEstudio();
		}
	}

	public String getAnioEstudio() {
		return anioEstudio;
	}

	public void setAnioEstudio(String anioEstudio) {
		this.anioEstudio = anioEstudio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
