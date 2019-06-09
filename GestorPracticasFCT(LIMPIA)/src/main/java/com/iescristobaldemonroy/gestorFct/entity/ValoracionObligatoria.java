package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the VALORACION database table.
 * 
 */
@Entity
@Table(name = "VALORACION_OBLIGATORIA")
@NamedQuery(name = "ValoracionObligatoria.findAll", query = "SELECT v FROM ValoracionObligatoria v")
public class ValoracionObligatoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String fechaRealizacion;

	private String posiFormacion;

	private String cumpliPrograma;

	private String segimiento;

	private String posiLaboral;

	private String formacionAcorde;

	private String satisfaccionProfesor;

	private String valoracionGeneral;

	private String posibleMejora;

	private String destacadoFormacion;

	// bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name = "dniPersona")
	private Persona persona;

	// bi-directional many-to-one association to Practica
	@ManyToOne
	@JoinColumn(name = "idPractica")
	private Practica practica;

	public ValoracionObligatoria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(String fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public String getPosiFormacion() {
		return posiFormacion;
	}

	public void setPosiFormacion(String posiFormacion) {
		this.posiFormacion = posiFormacion;
	}

	public String getCumpliPrograma() {
		return cumpliPrograma;
	}

	public void setCumpliPrograma(String cumpliPrograma) {
		this.cumpliPrograma = cumpliPrograma;
	}

	public String getSegimiento() {
		return segimiento;
	}

	public void setSegimiento(String segimiento) {
		this.segimiento = segimiento;
	}

	public String getPosiLaboral() {
		return posiLaboral;
	}

	public void setPosiLaboral(String posiLaboral) {
		this.posiLaboral = posiLaboral;
	}

	public String getFormacionAcorde() {
		return formacionAcorde;
	}

	public void setFormacionAcorde(String formacionAcorde) {
		this.formacionAcorde = formacionAcorde;
	}

	public String getSatisfaccionProfesor() {
		return satisfaccionProfesor;
	}

	public void setSatisfaccionProfesor(String satisfaccionProfesor) {
		this.satisfaccionProfesor = satisfaccionProfesor;
	}

	public String getValoracionGeneral() {
		return valoracionGeneral;
	}

	public void setValoracionGeneral(String valoracionGeneral) {
		this.valoracionGeneral = valoracionGeneral;
	}

	public String getPosibleMejora() {
		return posibleMejora;
	}

	public void setPosibleMejora(String posibleMejora) {
		this.posibleMejora = posibleMejora;
	}

	public String getDestacadoFormacion() {
		return destacadoFormacion;
	}

	public void setDestacadoFormacion(String destacadoFormacion) {
		this.destacadoFormacion = destacadoFormacion;
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