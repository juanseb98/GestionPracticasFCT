package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the NOTIFICACION database table.
 * 
 */
@Entity
@Table(name = "NOTIFICACION")
@NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n")
public class Notificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String mensaje;

	private String nueva;

	private String tipo;

	public Notificacion() {
	}

	public Notificacion(String mensaje, String nueva, String tipo) {
		super();
		this.mensaje = mensaje;
		this.nueva = nueva;
		this.tipo = tipo;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNueva() {
		return this.nueva;
	}

	public void setNueva(String nueva) {
		this.nueva = nueva;
	}

	public String getTipo() {
		return this.tipo.toString();
	}

	public void setTipo(String tipo) {
		this.tipo = TipoNotificacion.valueOf(tipo).toString();
	}

}