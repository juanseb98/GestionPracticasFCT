package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Empresa;
import com.iescristobaldemonroy.gestorFct.entity.Persona;

public interface PersonaService {
	public List getAllPersona();

	public Persona getPersonaByDni(String dni);

	public List<Persona> getPersonaByNombre(String nombre);

	public Persona getPersonaBydniAndNombre(String dni, String nombre);

	public boolean save(Persona persona);

	public boolean exist(String dni);

	boolean delete(Persona persona);

}
