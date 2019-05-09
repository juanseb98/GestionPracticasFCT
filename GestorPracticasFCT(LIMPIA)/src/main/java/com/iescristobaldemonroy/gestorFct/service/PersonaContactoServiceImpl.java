package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;
import com.iescristobaldemonroy.gestorFct.repository.PersonaContactoRepository;

@Service
@Transactional
public class PersonaContactoServiceImpl implements PersonaContactoService {

	// Implementing Constructor based DI
	private PersonaContactoRepository repository;

	public PersonaContactoServiceImpl() {

	}

	@Autowired
	public PersonaContactoServiceImpl(PersonaContactoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List getAllPersonaContacto() {
		List list = new ArrayList();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public PersonaContacto getPersonaContactoById(Long id) {
		PersonaContacto user = repository.findById(id).get();
		return user;
	}

	@Override
	public boolean save(PersonaContacto user) {
		try {
			repository.save(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deletePersonaContactoById(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

}
