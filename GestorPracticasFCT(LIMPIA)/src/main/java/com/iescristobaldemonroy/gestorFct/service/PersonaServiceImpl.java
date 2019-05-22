package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.Persona;
import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;
import com.iescristobaldemonroy.gestorFct.repository.PersonaContactoRepository;
import com.iescristobaldemonroy.gestorFct.repository.PersonaRepository;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {

	// Implementing Constructor based DI
	private PersonaRepository repository;

	public PersonaServiceImpl() {

	}

	@Autowired
	public PersonaServiceImpl(PersonaRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List getAllPersona() {
		List list = new ArrayList();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Persona getPersonaByDni(String dni) {
		return repository.findByDni(dni);

	}

	@Override
	public List<Persona> getPersonaByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}

	@Override
	public boolean save(Persona persona) {
		try {
			repository.save(persona);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(Persona persona) {
		try {
			repository.delete(persona);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String dni) {
		if (getPersonaByDni(dni) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Persona getPersonaBydniAndNombre(String dni, String nombre) {
		return repository.findByDniAndNombre(dni, nombre);
	}

}
