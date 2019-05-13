package com.iescristobaldemonroy.gestorFct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Interesado;

@Repository
public interface InteresadoRepository extends JpaRepository<Interesado, String>, JpaSpecificationExecutor<Interesado> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Interesado o WHERE o.id = ?1")
	Interesado findById(int id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Interesado o WHERE o.nombre = ?1")
	Interesado findByNombre(String nombre);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Interesado o WHERE LOWER(o.nombreEmpresa) = LOWER(?1)")
	Interesado findByEmpresa(String nombreEmpresa);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Interesado o WHERE o.telefono = ?1")
	Interesado findByTelefono(String telefono);

}
