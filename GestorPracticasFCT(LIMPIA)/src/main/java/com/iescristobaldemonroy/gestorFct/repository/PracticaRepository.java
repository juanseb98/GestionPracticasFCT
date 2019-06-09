package com.iescristobaldemonroy.gestorFct.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Practica;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Integer>, JpaSpecificationExecutor<Practica> {

	@Transactional(readOnly = true)
	@Query("SELECT p FROM Practica p WHERE p.id = ?1")
	Practica findById(int id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Practica o WHERE o.empresa =(SELECT e FROM Empresa e WHERE e.denominacion = ?1)")
	List<Practica> findByEmpresa(String denominacion);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Practica o WHERE o.alumno =(SELECT e FROM Alumno e WHERE e.dni = ?1)")
	List<Practica> findByAlumno(String dni);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Practica o WHERE o.tutorDocente =(SELECT e FROM TutorDocente e WHERE e.dni = ?1)")
	List<Practica> findByTutorDocente(String dni);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Practica o WHERE o.tutorLaboral =(SELECT e FROM TutorLaboral e WHERE e.dni = ?1)")
	List<Practica> findByTutorLaboral(String dni);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Practica o WHERE o.fechaInicio = ?1")
	List<Practica> findByFechaInicio(Date fecha);
}