package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;

public interface PersonaContactoService {

	public List getAllPersonaContacto();

	public PersonaContacto getPersonaContactoById(Long id);

	public boolean save(PersonaContacto persona);

	public boolean deletePersonaContactoById(Long id);

}
