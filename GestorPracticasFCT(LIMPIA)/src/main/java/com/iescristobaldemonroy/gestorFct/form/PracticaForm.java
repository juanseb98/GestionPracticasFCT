package com.iescristobaldemonroy.gestorFct.form;

import java.util.Date;
import java.util.List;

import com.iescristobaldemonroy.gestorFct.entity.TutorLaboral;

public class PracticaForm {
	private List<TutorLaboral> listaTutoresLaborales;
	private double horasDia;
	private Date fechaInicio;
	private Date fechaFin;
	private String permanencia;
	private String tareas;
	private String tipoJornada;
	private String dniTutorLaboral;
	private String dniAlumno;
	private String cifEmpresa;
	private String dniTutorDocente;

	public List<TutorLaboral> getListaTutoresLaborales() {
		return listaTutoresLaborales;
	}

	public void setListaTutoresLaborales(List<TutorLaboral> listaTutoresDocentes) {
		this.listaTutoresLaborales = listaTutoresDocentes;
	}

	public double getHorasDia() {
		return horasDia;
	}

	public void setHorasDia(double horasDia) {
		this.horasDia = horasDia;
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

	public String getPermanencia() {
		return permanencia;
	}

	public void setPermanencia(String permanencia) {
		this.permanencia = permanencia;
	}

	public String getTareas() {
		return tareas;
	}

	public void setTareas(String tareas) {
		this.tareas = tareas;
	}

	public String getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public String getDniTutorLaboral() {
		return dniTutorLaboral;
	}

	public void setDniTutorLaboral(String dniTutorLaboral) {
		this.dniTutorLaboral = dniTutorLaboral;
	}

	public String getDniAlumno() {
		return dniAlumno;
	}

	public void setDniAlumno(String dniAlumno) {
		this.dniAlumno = dniAlumno;
	}

	public String getCifEmpresa() {
		return cifEmpresa;
	}

	public void setCifEmpresa(String cifEmpresa) {
		this.cifEmpresa = cifEmpresa;
	}

	public String getDniTutorDocente() {
		return dniTutorDocente;
	}

	public void setDniTutorDocente(String dniTutorDocente) {
		this.dniTutorDocente = dniTutorDocente;
	}

}
