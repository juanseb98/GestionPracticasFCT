package com.iescristobaldemonroy.gestorFct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String>, JpaSpecificationExecutor<Empresa> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Empresa o WHERE o.cif = ?1")
	Empresa findByCif(String cif);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Empresa o WHERE LOWER(o.denominacion) = LOWER(?1)")
	Empresa findByDenominacion(String code);

}
