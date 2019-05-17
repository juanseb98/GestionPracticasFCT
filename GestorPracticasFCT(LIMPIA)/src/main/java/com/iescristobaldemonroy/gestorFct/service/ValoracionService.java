package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Valoracion;

public interface ValoracionService {
	public List<Valoracion> getAllValoraciones();

	public List<Valoracion> getValoracionByAlumno(String dni);

	public List<Valoracion> getValoracionByPractica(int id);

	public Valoracion getValoracionByPracticaAndPersona(int id, String dni);

	public List<Valoracion> getValoracionByEmpresa(String denominacion);

	public Boolean exist(String dni);

	public boolean save(Valoracion valoracion);

	boolean delete(Valoracion valoracion);

}
