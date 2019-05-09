package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.Notificacion;

public interface NotificacionService {
	public List getAllNotificacion();

	public Notificacion getNotificacionByTipo(String tipo);

	public List getNotificacionByEstado(String nueva);

	public boolean save(Notificacion notificacion);

}