package com.medmanager.model;

import java.util.Calendar;

public class Paso {
	private String nombre;
	private String descripcion;
	private int estado_cod;
	private Calendar fecha_accion_sugerida;
	private Calendar fecha_accion;
	private int idPaso;
	
	public Paso() {
	}
	
	public Paso(String nombre, String descripcion, int estado_cod, Calendar fecha_accion_sugerida,
			Calendar fecha_accion, int idPaso) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado_cod = estado_cod;
		this.fecha_accion_sugerida = fecha_accion_sugerida;
		this.fecha_accion = fecha_accion;
		this.setIdPaso(idPaso);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado_cod() {
		return estado_cod;
	}

	public void setEstado_cod(int estado_cod) {
		this.estado_cod = estado_cod;
	}

	public Calendar getFecha_accion_sugerida() {
		return fecha_accion_sugerida;
	}

	public void setFecha_accion_sugerida(Calendar fecha_accion_sugerida) {
		this.fecha_accion_sugerida = fecha_accion_sugerida;
	}

	public Calendar getFecha_accion() {
		return fecha_accion;
	}

	public void setFecha_accion(Calendar fecha_accion) {
		this.fecha_accion = fecha_accion;
	}

	public int getIdPaso() {
		return idPaso;
	}

	public void setIdPaso(int idPaso) {
		this.idPaso = idPaso;
	}
	
	
}
