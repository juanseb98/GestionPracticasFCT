package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.TutorDocente;

public interface TutorDocenteService {
	public List getAllTutoresDocentes();

	public TutorDocente getTutorDocenteByDni(String dni);

	public boolean save(TutorDocente tutorDocente);

	public boolean exist(String dni);

	boolean delete(TutorDocente tutorDocente);

}
