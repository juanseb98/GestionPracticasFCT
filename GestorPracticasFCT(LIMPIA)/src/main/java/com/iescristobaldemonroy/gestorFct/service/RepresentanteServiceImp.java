package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.Representante;
import com.iescristobaldemonroy.gestorFct.repository.RepresentanteRepository;

@Service
@Transactional
public class RepresentanteServiceImp implements RepresentanteService {

	// Implementing Constructor based DI
	private RepresentanteRepository repository;

	public RepresentanteServiceImp() {

	}

	@Autowired
	public RepresentanteServiceImp(RepresentanteRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Representante> getAllRepresentantes() {
		List<Representante> list = new ArrayList<Representante>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Representante getRepresentanteByDni(String dni) {
		return repository.findByDni(dni);

	}

	@Override
	public boolean save(Representante persona) {
		try {
			repository.save(persona);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(Representante representante) {
		try {
			repository.delete(representante);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String dni) {
		if (getRepresentanteByDni(dni) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Representante getRepresentanteByEmpresa(String denominacion) {
		return repository.findByEmpresa(denominacion);
	}

}
