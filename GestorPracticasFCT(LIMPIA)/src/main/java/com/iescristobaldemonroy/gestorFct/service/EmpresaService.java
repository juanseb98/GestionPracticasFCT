package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Empresa;

public interface EmpresaService {
	public List getAllEmpresa();

	public Empresa getEmpresaByCif(String cif);
	
	public List<Empresa> search(String cif, String denominacion);

	public boolean save(Empresa persona);

	public boolean deleteEmpresaBycif(String cif);

	public boolean exist(String denominacion);

}
