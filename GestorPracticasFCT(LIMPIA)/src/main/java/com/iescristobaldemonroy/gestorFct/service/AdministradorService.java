package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Administrador;

public interface AdministradorService {
	public List getAllAdministradores();

	public Administrador getAdministradorByDni(String dni);

	public boolean save(Administrador alumno);

	public boolean exist(String dni);

	boolean delete(Administrador alumno);

}
