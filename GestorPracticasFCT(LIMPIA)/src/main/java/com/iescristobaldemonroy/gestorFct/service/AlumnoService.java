package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;

public interface AlumnoService {
	public List getAllAlumno();

	public Alumno getAlumnoByDni(String dni);

	public List<Alumno> getAlumnoByNombre(String nombre);

	public List<Alumno> search(String dni, String nombre, String anioEstudio);

	public boolean save(Alumno alumno);

	public boolean exist(String dni);

	boolean delete(Alumno alumno);

	List<String> getAnios();

}
