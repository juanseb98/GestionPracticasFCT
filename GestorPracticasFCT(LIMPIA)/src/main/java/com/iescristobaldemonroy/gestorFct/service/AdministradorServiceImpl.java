package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.Administrador;
import com.iescristobaldemonroy.gestorFct.repository.AdministradorRepository;

@Service
@Transactional
public class AdministradorServiceImpl implements AdministradorService {

	private AdministradorRepository repository;
	private PersonaService personaService;

	public AdministradorServiceImpl() {

	}

	@Autowired
	public AdministradorServiceImpl(AdministradorRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List getAllAdministradores() {
		List list = new ArrayList();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Administrador getAdministradorByDni(String dni) {
		return repository.findByDni(dni);
	}

	@Override
	public boolean save(Administrador administrador) {
		try {
			repository.save(administrador);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String dni) {
		if (getAdministradorByDni(dni) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Administrador alumno) {
		try {
			personaService.delete(personaService.getPersonaByDni(alumno.getDni()));
			repository.delete(alumno);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
