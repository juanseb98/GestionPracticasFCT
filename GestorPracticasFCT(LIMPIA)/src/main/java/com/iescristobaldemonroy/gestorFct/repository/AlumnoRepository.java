package com.iescristobaldemonroy.gestorFct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long>, JpaSpecificationExecutor<Alumno> {

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM Alumno o WHERE o.dni = ?1")
	Alumno findByDni(String dni);

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM Alumno o WHERE o.anioEstudio = ?1")
	List<Alumno> findbyAnio(String anio);

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o.anioEstudio FROM Alumno o GROUP BY o.anioEstudio ORDER BY o.anioEstudio DESC")
	List<String> findanioEstudio();

}