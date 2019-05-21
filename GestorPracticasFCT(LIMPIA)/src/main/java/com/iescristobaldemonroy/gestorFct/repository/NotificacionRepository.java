package com.iescristobaldemonroy.gestorFct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iescristobaldemonroy.gestorFct.entity.Notificacion;

@Repository
public interface NotificacionRepository
		extends JpaRepository<Notificacion, Long>, JpaSpecificationExecutor<Notificacion> {

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	@Query(value = "SELECT o FROM Notificacion o WHERE o.tipo = ?1")
	Notificacion findByTipo(String tipo);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Notificacion o WHERE o.nueva = ?1")
	List<Notificacion> findByEstado(String nuevo);

	@Transactional(readOnly = true)
	@Query(value = "SELECT o FROM Notificacion o WHERE o.id = ?1")
	Notificacion findById(int id);

}