package com.iescristobaldemonroy.gestorFct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Representante;

@Repository
public interface RepresentanteRepository
		extends JpaRepository<Representante, String>, JpaSpecificationExecutor<Representante> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Representante o WHERE o.dni = ?1")
	Representante findByDni(String id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Representante o WHERE o.empresa =(SELECT e FROM Empresa e WHERE e.denominacion = ?1)")
	Representante findByEmpresa(String denominacion);

}
