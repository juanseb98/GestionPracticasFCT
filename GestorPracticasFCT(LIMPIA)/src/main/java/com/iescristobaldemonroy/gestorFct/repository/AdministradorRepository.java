package com.iescristobaldemonroy.gestorFct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Administrador;
import com.iescristobaldemonroy.gestorFct.entity.Interesado;

@Repository
public interface AdministradorRepository
		extends JpaRepository<Administrador, String>, JpaSpecificationExecutor<Administrador> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Administrador o WHERE o.dni = ?1")
	Administrador findByDni(String dni);

}
