package com.iescristobaldemonroy.gestorFct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Valoracion;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, String>, JpaSpecificationExecutor<Valoracion> {

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM Valoracion o WHERE o.persona = (SELECT a FROM Alumno a WHERE a.dni = ?1)")
	List<Valoracion> findByAlumno(String dni);

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM Valoracion o WHERE o.practica = (SELECT a FROM Pactica a WHERE a.empresa = (SELECT e FROM Empresa e WHERE e.denominacion = ?1))")
	List<Valoracion> findByPractica(String denominacion);
}