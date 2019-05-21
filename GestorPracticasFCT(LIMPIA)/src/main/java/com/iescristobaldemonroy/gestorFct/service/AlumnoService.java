package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;

public interface AlumnoService {
	public List getAllAlumno();

	public Alumno getAlumnoByDni(String dni);

	public Alumno getAlumnoByNombre(String nombre);

	public List<Alumno> search(String dni, String nombre);

	public boolean save(Alumno alumno);

	public boolean exist(String dni);

	boolean delete(Alumno alumno);

}
