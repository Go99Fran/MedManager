package com.medmanager.service.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.medmanager.bdd.facade.BD_Pacientes;
import com.medmanager.bdd.facade.BD_Usuarios;
import com.medmanager.model.Usuario;
import com.medmanager.security.Cryptography;
import com.medmanager.service.IUsuarioService;
import com.medmanager.util.CalendarUtil;
import com.medmanager.util.MailRecupero;

@WebService(endpointInterface="com.medmanager.service.IUsuarioService")
public class UsuarioService implements IUsuarioService {

	BD_Pacientes bd_pacientes;
	BD_Usuarios bd_usuarios;
	
	public UsuarioService() {
		bd_pacientes = new BD_Pacientes();
		bd_usuarios = new BD_Usuarios();
	}
	
	@Override
	public Usuario[] getPacientesActivosPorId(int usuario_id) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			ResultSet rs = bd_pacientes.getPacientesPorIdMedico(usuario_id);		
			
			while(rs.next()) {
				usuarios.add(new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("mail"),
							CalendarUtil.ParseString(rs.getString("fecha_nacimiento")), Integer.parseInt(rs.getString("tipo_usuario_cod")),
							Integer.parseInt(rs.getString("usuario_id")),
							Boolean.parseBoolean(rs.getString("confirmado")), Boolean.parseBoolean(rs.getString("activo")),
							checkString(rs.getString("matricula")), Integer.parseInt(checkNumeric(rs.getString("medico_ref_id"))), bd_pacientes.getTratamiento(Integer.parseInt(rs.getString("usuario_id")))));		
			}
		}catch(Exception e) {
			System.out.println("USUARIO_SERVICE: getUsuariosActivosPorId");
		}
		
		Usuario[] usuariosResp = usuarios.toArray(new Usuario[usuarios.size()]);

		return usuariosResp;
	}

	@Override
	public boolean existeMail(String mail) {
		boolean existe = false;

		try {
			ResultSet rs = bd_usuarios.existeMail(mail);
			
			if(rs.next()) {
				existe = true;
			}
			
		}catch(Exception e) {
			System.out.println("USUARIO_SERVICE: existeMail");
			e.printStackTrace();
		}
		
		return existe;
	}
	
	@Override
	public boolean esUsuarioConfirmado(String mail) {
		boolean confirmado = false;

		try {
			
			ResultSet rs = bd_usuarios.esUsuarioConfirmado(mail);
			
			if(rs.next()) {
				if(rs.getString("confirmado").equalsIgnoreCase("t"))
					confirmado = true;
			}
			
		}catch(Exception e) {
			System.out.println("USUARIO_SERVICE: esUsuarioConfirmado");
			e.printStackTrace();
		}
		
		return confirmado;
	}

	@Override
	public Usuario logeoUsuario(String mail, String password) {
		try {
			ResultSet rs = bd_usuarios.LogeoUsuario(mail.toLowerCase(), password);
			if(rs.next()) {
					if(password.equalsIgnoreCase(Cryptography.decrypt(rs.getString("clave"), Cryptography.getKey())))
						return new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("mail"),
							CalendarUtil.ParseString(rs.getString("fecha_nacimiento")), Integer.parseInt(rs.getString("tipo_usuario_cod")),
							Integer.parseInt(rs.getString("usuario_id")),
							Boolean.parseBoolean(rs.getString("confirmado")), Boolean.parseBoolean(rs.getString("activo")),
							checkString(rs.getString("matricula")), Integer.parseInt(checkNumeric(rs.getString("medico_ref_id"))), "Cancer de Vejiga");
					else
						return null;
				}else {
					return null;
				}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("USUARIO_SERVICE: logeoUsuario");
			return null;
		}		
	}
	private String checkString(String string) {
		if(string == null)
			return " ";
		else
			return string;
	}
	private String checkNumeric(String string) {
		if(string == null)
			return "-1";
		else
			return string;
	}
	
	@Override
	public void validarUsuario(String mail, String password) throws Exception {
		bd_usuarios.ValidarUsuario(mail, password);
	}

	@Override
	public void olvidoContraseña(String mail) {
		try {
			@SuppressWarnings("unused")
			MailRecupero mailRec = new MailRecupero(mail);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("USUARIO_SERVICE: olvidoContraseña");
		}
	}

	@Override
	public int registrarMedico(String mail, String password, String nombre, String apellido) {
		if(!existeMail(mail)) {
			try {
				ResultSet rs_id = bd_usuarios.getNextUsuarioId();
				String id = "0";
				
				try {
					if(rs_id.next())
						id = String.valueOf(Integer.parseInt(rs_id.getString(1)) + 1);
				}catch(Exception e) {}
				

				
				bd_usuarios.registrarMedico(mail, password, nombre, apellido, true, true, id);
				return 0;
			} catch (Exception e) {
				System.out.println("USUARIO_SERVICE: registrarMedico");
				e.printStackTrace();
				return 2;
			}
			
		}else {
			return 1;
		}
		
	}
}
