package com.medmanager.model;

import java.util.Calendar;

public class TratamientoPaciente {
	private int tratamientoPaciente_id;
	private int tratamiento_id;
	private int medico_id;
	private int paciente_id;
	private String titulo;
	
	private String cis;
	private String cant_tumores;
	private String tamañoTumoral_cod;
	private String tipo_tumor_cod;
	private String grado_tumor;
	private String clasificacionTratamiento;
	private String riesgo;
	private String inicio_tumor;
	private Calendar fechaCirugiaRTUV;
	private Calendar fechaCero;
	private boolean estado_COD;	
	private String compromiso_uretral;
	
	public TratamientoPaciente() {
	}

	public TratamientoPaciente(int tratamientoPaciente_id, int tratamiento_id, int medico_id, int paciente_id,
			String titulo, String cis, String cant_tumores, String tamañoTumoral_cod, String tipo_tumor_cod,
			String grado_tumor, String clasificacionTratamiento, String riesgo, String inicio_tumor,
			Calendar fechaCirugiaRTUV, Calendar fechaCero, boolean estado_COD, String compromiso_uretral) {
		super();
		this.tratamientoPaciente_id = tratamientoPaciente_id;
		this.tratamiento_id = tratamiento_id;
		this.medico_id = medico_id;
		this.paciente_id = paciente_id;
		this.titulo = titulo;
		this.cis = cis;
		this.cant_tumores = cant_tumores;
		this.tamañoTumoral_cod = tamañoTumoral_cod;
		this.tipo_tumor_cod = tipo_tumor_cod;
		this.grado_tumor = grado_tumor;
		this.clasificacionTratamiento = clasificacionTratamiento;
		this.riesgo = riesgo;
		this.inicio_tumor = inicio_tumor;
		this.fechaCirugiaRTUV = fechaCirugiaRTUV;
		this.fechaCero = fechaCero;
		this.estado_COD = estado_COD;
		this.setCompromiso_uretral(compromiso_uretral);
	}

	public int getTratamientoPaciente_id() {
		return tratamientoPaciente_id;
	}

	public void setTratamientoPaciente_id(int tratamientoPaciente_id) {
		this.tratamientoPaciente_id = tratamientoPaciente_id;
	}

	public int getTratamiento_id() {
		return tratamiento_id;
	}

	public void setTratamiento_id(int tratamiento_id) {
		this.tratamiento_id = tratamiento_id;
	}

	public int getMedico_id() {
		return medico_id;
	}

	public void setMedico_id(int medico_id) {
		this.medico_id = medico_id;
	}

	public int getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(int paciente_id) {
		this.paciente_id = paciente_id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCis() {
		return cis;
	}

	public void setCis(String cis) {
		this.cis = cis;
	}

	public String getCant_tumores() {
		return cant_tumores;
	}

	public void setCant_tumores(String cant_tumores) {
		this.cant_tumores = cant_tumores;
	}

	public String getTamañoTumoral_cod() {
		return tamañoTumoral_cod;
	}

	public void setTamañoTumoral_cod(String tamañoTumoral_cod) {
		this.tamañoTumoral_cod = tamañoTumoral_cod;
	}

	public String getTipo_tumor_cod() {
		return tipo_tumor_cod;
	}

	public void setTipo_tumor_cod(String tipo_tumor_cod) {
		this.tipo_tumor_cod = tipo_tumor_cod;
	}

	public String getGrado_tumor() {
		return grado_tumor;
	}

	public void setGrado_tumor(String grado_tumor) {
		this.grado_tumor = grado_tumor;
	}

	public String getClasificacionTratamiento() {
		return clasificacionTratamiento;
	}

	public void setClasificacionTratamiento(String clasificacionTratamiento) {
		this.clasificacionTratamiento = clasificacionTratamiento;
	}

	public String getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}

	public String getInicio_tumor() {
		return inicio_tumor;
	}

	public void setInicio_tumor(String inicio_tumor) {
		this.inicio_tumor = inicio_tumor;
	}

	public Calendar getFechaCirugiaRTUV() {
		return fechaCirugiaRTUV;
	}

	public void setFechaCirugiaRTUV(Calendar fechaCirugiaRTUV) {
		this.fechaCirugiaRTUV = fechaCirugiaRTUV;
	}

	public Calendar getFechaCero() {
		return fechaCero;
	}

	public void setFechaCero(Calendar fechaCero) {
		this.fechaCero = fechaCero;
	}

	public boolean isEstado_COD() {
		return estado_COD;
	}

	public void setEstado_COD(boolean estado_COD) {
		this.estado_COD = estado_COD;
	}

	public String getCompromiso_uretral() {
		return compromiso_uretral;
	}

	public void setCompromiso_uretral(String compromiso_uretral) {
		this.compromiso_uretral = compromiso_uretral;
	}

	
}