package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Valoracion;

public interface ValoracionService {
	public List<Valoracion> getAllValoraciones();

	public List<Valoracion> getValoracionByAlumno(String dni);

	public List<Valoracion> getValoracionByPractica(String denominacion);

	public boolean save(Valoracion valoracion);

	boolean delete(Valoracion valoracion);

}
