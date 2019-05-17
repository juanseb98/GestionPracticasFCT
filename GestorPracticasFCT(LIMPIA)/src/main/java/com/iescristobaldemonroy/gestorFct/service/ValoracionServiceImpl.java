package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.TutorLaboral;
import com.iescristobaldemonroy.gestorFct.entity.Valoracion;
import com.iescristobaldemonroy.gestorFct.repository.ValoracionRepository;

@Service
@Transactional
public class ValoracionServiceImpl implements ValoracionService {

	// Implementing Constructor based DI
	private ValoracionRepository repository;

	public ValoracionServiceImpl() {

	}

	@Autowired
	public ValoracionServiceImpl(ValoracionRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Valoracion> getAllValoraciones() {
		List<Valoracion> list = new ArrayList<Valoracion>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<Valoracion> getValoracionByAlumno(String dni) {
		return repository.findByPersona(dni);
	}

	@Override
	public List<Valoracion> getValoracionByPractica(int id) {
		return repository.findByPractica(id);
	}

	@Override
	public boolean save(Valoracion valoracion) {
		try {
			repository.save(valoracion);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(Valoracion valoracion) {
		try {
			repository.delete(valoracion);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<Valoracion> getValoracionByEmpresa(String denominacion) {
		return repository.findByEmpresa(denominacion);
	}

	@Override
	public Valoracion getValoracionByPracticaAndPersona(int id, String dni) {
		return repository.findByPracticaAlumno(id, dni);
	}

	@Override
	public Boolean exist(String dni) {
		if (repository.findByPersona(dni) == null) {
			return false;
		} else {
			return true;
		}
	}

}
