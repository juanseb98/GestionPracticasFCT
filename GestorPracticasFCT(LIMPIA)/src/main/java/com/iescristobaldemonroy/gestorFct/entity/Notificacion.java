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
	private int id;

	private String mensaje;

	private String nueva;

	private String tipo;

	// uni-directional many-to-one association to Interesado
	@ManyToOne
	@JoinColumn(name = "idInteresado")
	private Interesado interesado;

	// uni-directional many-to-one association to Valoracion
	@ManyToOne
	@JoinColumn(name = "idValoracion")
	private Valoracion valoracion;

	public Notificacion() {
	}

	public Notificacion(String mensaje, String nueva, String tipo, Interesado interesado, Valoracion valoracion) {
		this.mensaje = mensaje;
		this.nueva = nueva;
		this.tipo = tipo;
		this.interesado = interesado;
		this.valoracion = valoracion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Interesado getInteresado() {
		return this.interesado;
	}

	public void setInteresado(Interesado interesado) {
		this.interesado = interesado;
	}

	public Valoracion getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}

}