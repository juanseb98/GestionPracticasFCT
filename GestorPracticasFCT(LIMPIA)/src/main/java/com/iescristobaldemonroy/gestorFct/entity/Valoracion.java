package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the VALORACION database table.
 * 
 */
@Entity
@Table(name = "VALORACION")
@NamedQuery(name = "Valoracion.findAll", query = "SELECT v FROM Valoracion v")
public class Valoracion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String comentario;

	private int puntuacion;

	// bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name = "dniPersona")
	private Persona persona;

	// bi-directional many-to-one association to Practica
	@ManyToOne
	@JoinColumn(name = "idPractica")
	private Practica practica;

	public Valoracion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Practica getPractica() {
		return practica;
	}

	public void setPractica(Practica practica) {
		this.practica = practica;
	}

}