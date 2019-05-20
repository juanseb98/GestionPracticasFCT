package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.Notificacion;
import com.iescristobaldemonroy.gestorFct.repository.NotificacionRepository;

@Service
@Transactional
public class NotificacionServiceImpl implements NotificacionService {

	private NotificacionRepository repository;

	public NotificacionServiceImpl() {

	}

	@Autowired
	public NotificacionServiceImpl(NotificacionRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List getAllNotificacion() {
		List list = new ArrayList();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Notificacion getNotificacionByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}

	@Override
	public boolean save(Notificacion notificacion) {
		try {
			repository.save(notificacion);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean delete(Notificacion notificacion) {
		try {
			repository.delete(notificacion);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List getNotificacionByEstado(String nueva) {
		List list = new ArrayList();
		repository.findByEstado(nueva).forEach(e -> list.add(e));
		return list;
	}

	@Override
	public int getTotalNotificaciones() {
		int contador;
		List<Notificacion> notificaciones = repository.findAll();
		contador = notificaciones.size();
		return contador;
	}

	@Override
	public int getTotalNotificaciones(String bool) {
		int contador;
		List<Notificacion> notificaciones = repository.findByEstado(bool);
		contador = notificaciones.size();
		return contador;
	}

}
