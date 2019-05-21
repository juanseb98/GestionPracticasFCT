package com.iescristobaldemonroy.gestorFct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>, JpaSpecificationExecutor<Persona> {

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM Persona o WHERE o.dni = ?1")
	Persona findByDni(String dni);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Persona o WHERE o.nombre = ?1")
	Persona findByNombre(String nombre);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Persona o WHERE o.dni = ?1 AND o.nombre = ?2")
	Persona findByDniAndNombre(String dni, String nombre);
}