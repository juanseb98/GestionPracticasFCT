package com.iescristobaldemonroy.gestorFct.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the PRACTICA database table.
 * 
 */
@Entity
@Table(name = "PRACTICA")
@NamedQuery(name = "Practica.findAll", query = "SELECT p FROM Practica p")
public class Practica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double horasDia;

	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	private String permanencia;

	private String tareas;

	private String tipoJornada;

	// bi-directional many-to-one association to TutorLaboral
	@ManyToOne
	@JoinColumn(name = "dniTutorLaboral")
	private TutorLaboral tutorLaboral;

	// bi-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name = "dniAlumno")
	private Alumno alumno;

	// bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name = "cifEmpresa")
	private Empresa empresa;

	// bi-directional many-to-one association to TutorDocente
	@ManyToOne
	@JoinColumn(name = "dniTutorDocente")
	private TutorDocente tutorDocente;

	// bi-directional many-to-one association to Valoracion
	@OneToMany(mappedBy = "practica")
	private List<Valoracion> valoracions;

	// bi-directional many-to-one association to Valoracion
	@OneToMany(mappedBy = "practica")
	private List<ValoracionObligatoria> valoracionesObligatorias;

	public Practica() {
	}

	public List<ValoracionObligatoria> getValoracionesObligatorias() {
		return valoracionesObligatorias;
	}

	public void setValoracionesObligatorias(List<ValoracionObligatoria> valoracionesObligatorias) {
		this.valoracionesObligatorias = valoracionesObligatorias;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Valoracion> getValoracions() {
		return valoracions;
	}

	public void setValoracions(List<Valoracion> valoracions) {
		this.valoracions = valoracions;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHorasDia() {
		return this.horasDia;
	}

	public void setHorasDia(double horasDia) {
		this.horasDia = horasDia;
	}

	public String getPermanencia() {
		return this.permanencia;
	}

	public void setPermanencia(String permanencia) {
		this.permanencia = permanencia;
	}

	public String getTareas() {
		return this.tareas;
	}

	public void setTareas(String tareas) {
		this.tareas = tareas;
	}

	public String getTipoJornada() {
		return this.tipoJornada;
	}

	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public TutorLaboral getTutorLaboral() {
		return this.tutorLaboral;
	}

	public void setTutorLaboral(TutorLaboral tutorLaboral) {
		this.tutorLaboral = tutorLaboral;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TutorDocente getTutorDocente() {
		return this.tutorDocente;
	}

	public void setTutorDocente(TutorDocente tutorDocente) {
		this.tutorDocente = tutorDocente;
	}

}