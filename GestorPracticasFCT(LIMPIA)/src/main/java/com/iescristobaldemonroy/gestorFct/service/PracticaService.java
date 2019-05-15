package com.iescristobaldemonroy.gestorFct.service;

import java.util.Date;
import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Practica;

public interface PracticaService {
	public List getAllPracticas();

	public List<Practica> getPracticaByEmpresa(String denominacion);

	public List<Practica> getPracticaByAlumno(String dni);

	public List<Practica> getPracticaByTutorDocente(String dni);

	public List<Practica> getPracticaByTutorLaboral(String dni);

	public List<Practica> getPracticaByFecha(Date fecha);

	public boolean save(Practica pratica);

	boolean delete(Practica practica);

}
