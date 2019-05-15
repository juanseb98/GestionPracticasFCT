package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Representante;

public interface RepresentanteService {
	public List<Representante> getAllRepresentantes();

	public Representante getRepresentanteByDni(String dni);

	public Representante getRepresentanteByEmpresa(String denominacion);

	public boolean save(Representante representante);

	public boolean exist(String dni);

	boolean delete(Representante alumno);

}
