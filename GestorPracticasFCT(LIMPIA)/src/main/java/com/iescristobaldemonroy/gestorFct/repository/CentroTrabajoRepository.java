package com.iescristobaldemonroy.gestorFct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.CentroTrabajo;

@Repository
public interface CentroTrabajoRepository
		extends JpaRepository<CentroTrabajo, String>, JpaSpecificationExecutor<CentroTrabajo> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM CentroTrabajo o WHERE LOWER(o.provincia) = LOWER(?1)")
	List<CentroTrabajo> findByProvincia(String provincia);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM CentroTrabajo o WHERE LOWER(o.municipio) = LOWER(?1)")
	List<CentroTrabajo> findByMunicipio(String municipio);

	@Transactional(readOnly = true)
	@Query("SELECT o FROM CentroTrabajo o WHERE LOWER(o.calle) = LOWER(?1) and o.numero = ?2")
	CentroTrabajo findByDireccion(String calle, String numero);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM CentroTrabajo o WHERE o.empresa = (SELECT e FROM Empresa e WHERE e.denominacion = ?1)")
	List<CentroTrabajo> findByEmpresa(String denominacion);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM CentroTrabajo o WHERE o.empresa = (SELECT e FROM Empresa e WHERE e.denominacion = ?1) and o.principal = 'S'")
	CentroTrabajo findByPrincipalEmpresa(String denominacion);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM CentroTrabajo o WHERE o.telefono = ?1")
	CentroTrabajo findByTelefono(String telefono);

}
