package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Empresa;
import com.iescristobaldemonroy.gestorFct.entity.Persona;
import com.iescristobaldemonroy.gestorFct.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private EmpresaRepository repository;

	public EmpresaServiceImpl() {

	}

	@Autowired
	public EmpresaServiceImpl(EmpresaRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List getAllEmpresa() {
		List list = new ArrayList();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Empresa getEmpresaByCif(String cif) {
		Empresa empresa = repository.findByCif(cif);
		return empresa;
	}

	@Override
	public boolean save(Empresa persona) {
		try {
			repository.save(persona);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteEmpresaBycif(String cif) {
		try {
			repository.delete(repository.findByCif(cif));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String denominacion) {
		Empresa emp = repository.findByDenominacion(denominacion);
		if (emp == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<Empresa> search(String cif, String denominacion) {
		List<Empresa> lista = new ArrayList<Empresa>();

		if (!StringUtils.isEmpty(cif)) {
			if (!StringUtils.isEmpty(denominacion)) {
				try {
					lista.add(repository.findByCifAndDenominacion(cif, denominacion));
				} catch (NullPointerException e) {

				}
			} else {
				lista.add(repository.findByCif(cif));
			}

		} else if (!StringUtils.isEmpty(denominacion)) {
			try {
				Empresa emp = repository.findByDenominacion(denominacion);

				lista.add(emp);

			} catch (NullPointerException e) {

			}
		} else {
			lista = getAllEmpresa();
		}

		return lista;
	}

}
