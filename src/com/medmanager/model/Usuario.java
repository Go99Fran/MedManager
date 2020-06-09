package com.medmanager.model;

import java.util.Calendar;

public class Usuario {
	private String nombre;
	private String apellido;
	private String mail;
	private Calendar fechaNac;	
	private int usuario_id;
	private boolean confirmado;
	private boolean activo;
	private String matricula;
	private int medico_referente_id;
	private String tratamiento;

	/**
	 * 0 = Paciente
	 * 1 = Medico
	 * 2 = Administrador
	 */
	private int tipo;
	
	public Usuario() {}

	public Usuario(String nombre, String apellido, String mail, Calendar fechaNac, int tipo, int usuario_id,
			boolean confirmado, boolean activo, String matricula, int medico_referente_id, String tratamiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.fechaNac = fechaNac;
		this.tipo = tipo;
		this.usuario_id = usuario_id;
		this.confirmado = confirmado;
		this.activo = activo;
		this.medico_referente_id = medico_referente_id;
		this.setTratamiento(tratamiento);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Calendar getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Calendar fechaNac) {
		this.fechaNac = fechaNac;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}


	public int getTipo() {
		return tipo;
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getMedico_referente_id() {
		return medico_referente_id;
	}


	public void setMedico_referente_id(int medico_referente_id) {
		this.medico_referente_id = medico_referente_id;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	
}
