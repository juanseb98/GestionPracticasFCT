package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.Interesado;
import com.iescristobaldemonroy.gestorFct.repository.InteresadoRepository;

@Service
@Transactional
public class InteresadoServiceImpl implements InteresadoService {

	private InteresadoRepository repository;

	public InteresadoServiceImpl() {

	}

	@Autowired
	public InteresadoServiceImpl(InteresadoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List getAllInteresados() {
		List list = new ArrayList();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Interesado getInteresadoByTelefono(String telefono) {
		return repository.findByTelefono(telefono);
	}

	@Override
	public Interesado getInteresadoByEmpresa(String empresa) {
		return repository.findByEmpresa(empresa);
	}

	@Override
	public boolean save(Interesado interesado) {
		try {
			repository.save(interesado);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteInteresadoByEmpresa(String empresa) {
		try {
			repository.delete(repository.findByEmpresa(empresa));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteInteresadoByTelefono(String telefono) {
		try {
			repository.delete(repository.findByTelefono(telefono));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String nombreEmpresa) {
		Interesado interesad = repository.findByEmpresa(nombreEmpresa);
		if (interesad == null) {
			return false;
		}
		return true;
	}

	@Override
	public Interesado getInteresadoById(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

}
