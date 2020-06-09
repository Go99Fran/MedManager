package com.medmanager.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medmanager.model.Usuario;
import com.medmanager.security.Cryptography;
import com.medmanager.service.IPasoService;
import com.medmanager.service.ITratamientoService;
import com.medmanager.service.IUsuarioService;
import com.medmanager.service.impl.PasoService;
import com.medmanager.service.impl.TratamientoService;
import com.medmanager.service.impl.UsuarioService;

@WebServlet("/Resources/Actions/LogAction.do")
public class LogeoRegistroSERVLET extends HttpServlet {

	private final static String LOG_IN = "LOGIN";
	private final static String CARGAR_CONTRAS = "CARGAR_CONTRAS";
	private final static String REGISTRO = "REGISTRO";
	private final static String OLVIDO_CONTRASEÑAS = "OLVIDO_CONTRA";
	private final static String RECUPERO_CONTRASEÑA = "RECUPERO";
	private final static String REGISTRO_MEDICO = "REGISTRO_MEDICO";

	private IUsuarioService usuarioService;
	private ITratamientoService tratamientoService;
	private IPasoService pasosService;
	
	public LogeoRegistroSERVLET() {
		super();
	    usuarioService = new UsuarioService();
		tratamientoService = new TratamientoService();
		pasosService = new PasoService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ACCION = request.getParameter("ACCION");

		if (ACCION.equalsIgnoreCase(LOG_IN))
			LogIN(request, response);
		else if (ACCION.equalsIgnoreCase(REGISTRO))
			Registro(request, response, 1);
		else if (ACCION.equalsIgnoreCase(CARGAR_CONTRAS))
			CargarContras(request, response);
		else if (ACCION.equalsIgnoreCase(OLVIDO_CONTRASEÑAS))
			OlvidoContraseña(request, response);
		else if (ACCION.equalsIgnoreCase(RECUPERO_CONTRASEÑA))
			Registro(request, response, 2);
		else if (ACCION.equalsIgnoreCase(REGISTRO_MEDICO))
			RegistroMedico(request, response);
	}
	
	private void RegistroMedico(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int rsp = usuarioService.registrarMedico(request.getParameter("mail"),
				request.getParameter("password"), request.getParameter("nombre"), 
				request.getParameter("apellido"));
		
		if(0 == rsp) {
			request.getSession().setAttribute("errorMail", "No");
			request.getSession().setAttribute("Error", " ");
			response.sendRedirect("/MedManager/webapp/pages/login/login.jsp");
		}else if (1 == rsp){
			request.getSession().setAttribute("errorMail", "Si");
			response.sendRedirect("/MedManager/webapp/pages/login/registroMedico.jsp");
		}else {
			response.sendRedirect("/MedManager/webapp/pages/error/error.jsp");
		}
	}
	
	private void OlvidoContraseña(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		usuarioService.olvidoContraseña(request.getParameter("MAIL_OLVIDO"));
		request.setAttribute("Error", " ");
		response.sendRedirect("/MedManager/webapp/pages/login/mensaje_ver_mail.jsp");
	}

	private void Registro(HttpServletRequest request, HttpServletResponse response, int opcion)
			throws ServletException, IOException {
		
		String mail = "";
		try {
			String crypted = request.getParameter("m").replace(" ", "+");
			mail = Cryptography.decrypt(crypted, Cryptography.getKey());
		} catch (Exception e) {
			System.out.println("Error en Crypt registro");
			e.printStackTrace();
		}
		
		if(opcion == 1) {
			if(!usuarioService.esUsuarioConfirmado(mail)) {
				request.getSession().setAttribute("mail_validar", mail);
				response.sendRedirect("/MedManager/webapp/pages/login/registrar.jsp");
			}else {
				response.sendRedirect("/MedManager/webapp/pages/login/error_registro.jsp");
			}
		}else {
			request.getSession().setAttribute("mail_validar", mail);
			response.sendRedirect("/MedManager/webapp/pages/login/registrar.jsp");
		}
		
		
	}

	private void LogIN(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession(true).getAttribute("UsuarioLogeado") != null)
			if(((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getTipo() == 1)
				response.sendRedirect("/MedManager/webapp/pages/menu/medico/menu.jsp");
			else
				response.sendRedirect("/MedManager/webapp/pages/menu/paciente/menu.jsp");
		

		String mail = request.getParameter("mail");
		String password = request.getParameter("pass");
		
		Usuario usuario = usuarioService.logeoUsuario(mail.toLowerCase(), password);
		
		if (usuario != null) {

			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(60 * 60 * 24);
			request.setAttribute("Error", " ");
			session.setAttribute("UsuarioLogeado", usuario);	

			if (usuario.getTipo() == 1) {				
				
				session.setAttribute("FiltroPasos", "Todos");
				session.setAttribute("errorMail", "No");
				session.setAttribute("Pacientes", Arrays.asList(usuarioService.getPacientesActivosPorId(usuario.getUsuario_id())));
				session.setAttribute("Tratamientos", Arrays.asList(tratamientoService.getTratamientosActivosPorId(usuario.getUsuario_id())));
				
				response.sendRedirect("/MedManager/webapp/pages/menu/medico/menu.jsp");
			} else if (usuario.getTipo() == 0) {
				
				session.setAttribute("Pasos", Arrays.asList(pasosService.getPasosPorIdPaciente(String.valueOf(usuario.getUsuario_id()))));
				session.setAttribute("FiltroPasos", "Solo Pendientes");
				request.getSession().setAttribute("Scroll", request.getParameter("scrollPosition"));
				
				response.sendRedirect("/MedManager/webapp/pages/menu/paciente/menu.jsp");
			} else if (usuario.getTipo() == 2) {
				response.sendRedirect("/MedManager/webapp/pages/menu/MenuAdm/MenuAdm.jsp");
			}
		} else {
			request.getSession().setAttribute("Error", "El usuario o la contraseña no son correctos");
			response.sendRedirect("/MedManager/webapp/pages/login/login.jsp");
		}
	}

	private void CargarContras(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			usuarioService.validarUsuario(request.getParameter("mailV"), request.getParameter("password"));
		} catch (Exception e) {
			System.out.println("ErrorCargarContras");
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("Error", " ");
		response.sendRedirect("/MedManager/webapp/pages/login/login.jsp");
	}

}
