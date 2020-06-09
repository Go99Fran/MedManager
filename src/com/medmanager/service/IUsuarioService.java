package com.medmanager.service;

import com.medmanager.model.Usuario;

public interface IUsuarioService {

	public Usuario[] getPacientesActivosPorId(int usuario_id);	

	public boolean existeMail(String mail);
	
	public boolean esUsuarioConfirmado(String mail);
	
	public Usuario logeoUsuario(String mail, String password);
	
	public void validarUsuario(String mail, String password) throws Exception;

	public void olvidoContraseña(String mail);

	public int registrarMedico(String mail, String password, String nombre, String apellido);
}
