package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Persona;
import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;
import com.iescristobaldemonroy.gestorFct.repository.AlumnoRepository;
import com.iescristobaldemonroy.gestorFct.repository.PersonaContactoRepository;
import com.iescristobaldemonroy.gestorFct.repository.PersonaRepository;

@Service
@Transactional
public class AlumnoServiceImp implements AlumnoService {

	// Implementing Constructor based DI
	private AlumnoRepository repository;
	private PersonaRepository rep;

	public AlumnoServiceImp() {

	}

	@Autowired
	public AlumnoServiceImp(AlumnoRepository repository, PersonaRepository rep) {
		super();
		this.repository = repository;
		this.rep = rep;
	}

	@Override
	public List getAllAlumno() {
		List list = new ArrayList();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Alumno getAlumnoByDni(String dni) {
		return repository.findByDni(dni);

	}

	@Override
	public boolean save(Alumno persona) {
		try {
			repository.save(persona);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(Alumno alumno) {
		try {
			rep.delete(rep.findByDni(alumno.getDni()));
			repository.delete(alumno);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String dni) {
		if (getAlumnoByDni(dni) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Alumno getAlumnoByNombre(String nombre) {
		Persona per = rep.findByNombre(nombre);
		return repository.findByDni(per.getDni());
	}

	@Override
	public List<Alumno> search(String dni, String nombre) {
		List<Alumno> lista = new ArrayList<Alumno>();

		if (!StringUtils.isEmpty(dni)) {
			if (!StringUtils.isEmpty(nombre)) {
				try {
					lista.add(rep.findByDniAndNombre(dni, nombre).getAlumno());
				} catch (NullPointerException e) {

				}
			} else {
				lista.add(repository.findByDni(dni));
			}

		} else if (!StringUtils.isEmpty(nombre)) {
			try {
				Persona per = rep.findByNombre(nombre);
				lista.add(rep.findByNombre(nombre).getAlumno());
			} catch (NullPointerException e) {

			}
		} else {
			lista = getAllAlumno();
		}

		return lista;
	}

}
