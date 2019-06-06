package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.CentroTrabajo;

public interface CentroTrabajoService {
	public List<CentroTrabajo> getAllCentroTrabajos();

	public List<CentroTrabajo> getCentroTrabajoByMunicipio(String municipio);

	public List<CentroTrabajo> getCentroTrabajoByEmpresa(String cif);

	public List<CentroTrabajo> search(String cif, String calle, String codigoPostal, String Localidad, String Municipio,
			Boolean principal);

	public List<String> getLocalidades();

	public List<String> getLocaMunicipio();

	public CentroTrabajo getCentroTrabajoPrincipalByEmpresa(String denominacion);

	public CentroTrabajo getCentroTrabajoByTelefono(String telefono);

	public CentroTrabajo getCentroTrabajoByDireccion(String calle, String numero);

	public boolean save(CentroTrabajo centroTrabajo);

	public boolean deleteCentroTrabajoByTelefono(String telefono);

	public boolean exist(String denominacion);

}
