package com.iescristobaldemonroy.gestorFct.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iescristobaldemonroy.gestorFct.entity.Notificacion;

public interface NotificacionService {
	public List getAllNotificacion();

	public Notificacion getNotificacionById(int id);
	
	public Notificacion getNotificacionByTipo(String tipo);

	public List getNotificacionByEstado(String nueva);

	public int getTotalNotificaciones(String bool);

	public int getTotalNotificaciones();

	public boolean save(Notificacion notificacion);

}