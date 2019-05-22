package com.iescristobaldemonroy.gestorFct.form;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Persona;

public class EdicionAlumnoForm {

	private Persona persona;
	private Alumno alumno;
	private String operacion;

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

}
