package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.Practica;
import com.iescristobaldemonroy.gestorFct.repository.PersonaRepository;
import com.iescristobaldemonroy.gestorFct.repository.PracticaRepository;

@Service
@Transactional
public class PracticaServiceImpl implements PracticaService {

	// Implementing Constructor based DI
	private PracticaRepository repository;

	public PracticaServiceImpl() {

	}

	@Autowired
	public PracticaServiceImpl(PracticaRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List getAllPracticas() {
		List<Practica> list = new ArrayList();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<Practica> getPracticaByEmpresa(String denominacion) {
		return repository.findByEmpresa(denominacion);
	}

	@Override
	public List<Practica> getPracticaByAlumno(String dni) {
		return repository.findByAlumno(dni);
	}

	@Override
	public List<Practica> getPracticaByTutorDocente(String dni) {
		return repository.findByTutorDocente(dni);
	}

	@Override
	public List<Practica> getPracticaByTutorLaboral(String dni) {
		return repository.findByTutorLaboral(dni);
	}

	@Override
	public List<Practica> getPracticaByFecha(Date fecha) {
		return repository.findByFecha(fecha);
	}

	@Override
	public boolean save(Practica practica) {
		try {
			repository.save(practica);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(Practica practica) {
		try {
			repository.delete(practica);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Practica getPracticaById(int id) {
		return repository.findById(id);
	}

}
