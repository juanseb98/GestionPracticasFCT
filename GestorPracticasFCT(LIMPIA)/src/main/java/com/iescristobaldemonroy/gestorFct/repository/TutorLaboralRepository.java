package com.iescristobaldemonroy.gestorFct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.TutorLaboral;

@Repository
public interface TutorLaboralRepository
		extends JpaRepository<TutorLaboral, Long>, JpaSpecificationExecutor<TutorLaboral> {

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM TutorLaboral o WHERE o.dni = ?1")
	TutorLaboral findByDni(String dni);

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM TutorLaboral o WHERE o.email = ?1")
	TutorLaboral findByEmail(String email);

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM TutorLaboral o WHERE o.telefono = ?1")
	TutorLaboral findByTelefono(String telefono);

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM TutorLaboral o WHERE o.empresa = (SELECT e FROM Empresa e WHERE e.denominacion = ?1)")
	List<TutorLaboral> findByEmpresa(String denominacion);
}