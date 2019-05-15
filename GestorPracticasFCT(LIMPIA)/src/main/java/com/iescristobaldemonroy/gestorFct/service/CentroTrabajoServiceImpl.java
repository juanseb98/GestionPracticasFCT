package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.CentroTrabajo;
import com.iescristobaldemonroy.gestorFct.repository.CentroTrabajoRepository;

@Service
public class CentroTrabajoServiceImpl implements CentroTrabajoService {

	private CentroTrabajoRepository repository;

	public CentroTrabajoServiceImpl() {

	}

	@Autowired
	public CentroTrabajoServiceImpl(CentroTrabajoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<CentroTrabajo> getAllCentroTrabajos() {
		List<CentroTrabajo> list = new ArrayList<CentroTrabajo>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<CentroTrabajo> getCentroTrabajoByMunicipio(String municipio) {
		return repository.findByMunicipio(municipio);
	}

	@Override
	public CentroTrabajo getCentroTrabajoPrincipalByEmpresa(String denominacion) {
		return repository.findByPrincipalEmpresa(denominacion);
	}

	@Override
	public CentroTrabajo getCentroTrabajoByTelefono(String telefono) {
		return repository.findByTelefono(telefono);
	}

	@Override
	public CentroTrabajo getCentroTrabajoByDireccion(String calle, String numero) {
		return repository.findByDireccion(calle, numero);
	}

	@Override
	public boolean save(CentroTrabajo centroTrabajo) {
		try {
			repository.save(centroTrabajo);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteCentroTrabajoByTelefono(String telefono) {
		try {
			repository.delete(getCentroTrabajoByTelefono(telefono));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String denominacion) {
		List<CentroTrabajo> emp = repository.findByEmpresa(denominacion);
		if (emp.size() < 1) {
			return false;
		}
		return true;
	}

}
