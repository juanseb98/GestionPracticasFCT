package com.iescristobaldemonroy.gestorFct.form;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Practica;

public class AlumnoForm {
	private String nombre;
	private String dni;
	private String contrasenia;
	private List<Practica> practicas;
	private String practicaSelected;

	public AlumnoForm() {
	}

	public AlumnoForm(String nombre, String dni, String contrasenia, List<Practica> practicas,
			String practicaSelected) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.contrasenia = contrasenia;
		this.practicas = practicas;
		this.practicaSelected = practicaSelected;
	}

	public AlumnoForm(Alumno alumno) {
		this.nombre = alumno.getPersona().getNombre();
		this.dni = alumno.getDni();
		this.contrasenia = alumno.getContrasenia();
		this.practicas = alumno.getPracticas();
	}

	public String getPracticaSelected() {
		return practicaSelected;
	}

	public void setPracticaSelected(String practicaSelected) {
		this.practicaSelected = practicaSelected;
	}

	public List<Practica> getPracticas() {
		return practicas;
	}

	public void setPracticas(List<Practica> practias) {
		this.practicas = practias;
	}

	public void addPractica(Practica practias) {
		this.practicas.add(practias);
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

}
