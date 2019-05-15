package com.iescristobaldemonroy.gestorFct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.TutorDocente;

@Repository
public interface TutorDocenteRepository
		extends JpaRepository<TutorDocente, Long>, JpaSpecificationExecutor<TutorDocente> {

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM TutorDocente o WHERE o.dni = ?1")
	TutorDocente findByDni(String dni);
}