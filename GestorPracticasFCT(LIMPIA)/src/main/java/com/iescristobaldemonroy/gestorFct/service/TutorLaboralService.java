package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.TutorLaboral;

public interface TutorLaboralService {
	public List<TutorLaboral> getAllTutoresLaborales();

	public TutorLaboral getTutorLaboralByDni(String dni);

	public TutorLaboral getTutorLaboralByemail(String email);

	public TutorLaboral getTutorLaboralByTelefono(String telefono);

	public List<TutorLaboral> getTutorLaboralByEmpresa(String cif);

	public boolean save(TutorLaboral tutorLaboral);

	public boolean exist(String dni);

	boolean delete(TutorLaboral tutorLaboral);

}
