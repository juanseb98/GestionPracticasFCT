package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.TutorDocente;
import com.iescristobaldemonroy.gestorFct.repository.TutorDocenteRepository;

@Service
@Transactional
public class TutorDocenteServiceImpl implements TutorDocenteService {

	private TutorDocenteRepository repository;

	public TutorDocenteServiceImpl() {

	}

	@Autowired
	public TutorDocenteServiceImpl(TutorDocenteRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List getAllTutoresDocentes() {
		List<TutorDocente> list = new ArrayList<TutorDocente>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public TutorDocente getTutorDocenteByDni(String dni) {
		return repository.findByDni(dni);
	}

	@Override
	public boolean save(TutorDocente tutorDocente) {
		try {
			repository.save(tutorDocente);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String dni) {
		if (getTutorDocenteByDni(dni) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(TutorDocente tutorDocente) {
		try {
			repository.delete(tutorDocente);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
