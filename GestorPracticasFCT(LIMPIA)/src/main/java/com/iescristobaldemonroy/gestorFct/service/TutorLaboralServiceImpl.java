package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.TutorLaboral;
import com.iescristobaldemonroy.gestorFct.repository.TutorLaboralRepository;

@Service
@Transactional
public class TutorLaboralServiceImpl implements TutorLaboralService {

	// Implementing Constructor based DI
	private TutorLaboralRepository repository;

	public TutorLaboralServiceImpl() {

	}

	@Autowired
	public TutorLaboralServiceImpl(TutorLaboralRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<TutorLaboral> getAllTutoresLaborales() {
		List<TutorLaboral> list = new ArrayList<TutorLaboral>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public TutorLaboral getTutorLaboralByDni(String dni) {
		return repository.findByDni(dni);
	}

	@Override
	public TutorLaboral getTutorLaboralByemail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public TutorLaboral getTutorLaboralByTelefono(String telefono) {
		return repository.findByTelefono(telefono);
	}

	@Override
	public List<TutorLaboral> getTutorLaboralByEmpresa(String denominacion) {
		return repository.findByEmpresa(denominacion);
	}

	@Override
	public boolean save(TutorLaboral tutorLaboral) {
		try {
			repository.save(tutorLaboral);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String dni) {
		if (getTutorLaboralByDni(dni) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(TutorLaboral tutorLaboral) {
		try {
			repository.delete(tutorLaboral);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
