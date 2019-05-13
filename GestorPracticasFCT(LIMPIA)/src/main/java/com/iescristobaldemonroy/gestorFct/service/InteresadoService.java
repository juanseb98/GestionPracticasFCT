package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Interesado;

public interface InteresadoService {
	public List getAllInteresados();

	public Interesado getInteresadoById(int id);

	public Interesado getInteresadoByTelefono(String telefono);

	public Interesado getInteresadoByEmpresa(String empresa);

	public boolean save(Interesado interesado);

	public boolean deleteInteresadoByEmpresa(String empresa);

	public boolean deleteInteresadoByTelefono(String telefono);

	public boolean exist(String nombreEmpresa);

}
