package com.iescristobaldemonroy.gestorFct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;

@Repository
public interface PersonaContactoRepository
		extends JpaRepository<PersonaContacto, Long>, JpaSpecificationExecutor<PersonaContacto> {
	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM PersonaContacto o WHERE o.nombre = ?1")
	PersonaContacto findByNombre(String nombre);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM PersonaContacto o WHERE o.empresa = (SELECT e FROM Empresa e WHERE e.denominacion = ?1)")
	List findByEmpresa(String empresa);
}