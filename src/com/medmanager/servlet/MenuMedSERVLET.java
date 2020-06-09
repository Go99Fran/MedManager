package com.medmanager.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medmanager.model.Usuario;
import com.medmanager.service.IPacienteService;
import com.medmanager.service.IPasoService;
import com.medmanager.service.ITratamientoService;
import com.medmanager.service.IUsuarioService;
import com.medmanager.service.impl.PacienteService;
import com.medmanager.service.impl.PasoService;
import com.medmanager.service.impl.TratamientoService;
import com.medmanager.service.impl.UsuarioService;

/**
 * @author FGonzalez
 */
@WebServlet("/Resources/Actions/MenuAction.do")
public class MenuMedSERVLET extends HttpServlet {

	private final static String ELIMINAR_PACIENTE = "ELIMINAR_PACIENTE";
	private final static String VER_TRATAMIENTO = "VER_TRATAMIENTO";
	private final static String BAJA_PACIENTE = "BAJA_PACIENTE";
	private final static String BUSCAR_PACIENTE = "BUSCAR_PACIENTE";
	private final static String LOG_OUT = "LOGOUT";
	private final static String ALTA_PACIENTE_1 = "ALTA_PACIENTE_1";
	private final static String ALTA_TRATAMIENTO = "ALTA_PACIENTE_2";
	private final static String ALTA_VISTA_TRATAMIENTO = "ALTA_VISTA_TRATAMIENTO";
	private final static String GUARDAR_TRATAMIENTO = "GUARDAR_TRATAMIENTO";
	private final static String CAMBIAR_FILTROS = "CAMBIAR_FILTROS";
	private final static String INACTIVAR_TRATAMIENTO = "INACTIVAR_TRATAMIENTO";

	private IUsuarioService usuarioService;
	private ITratamientoService tratamientoService;
	private IPasoService pasosService;
	private IPacienteService pacienteService;
	
    public MenuMedSERVLET() {
        super();
        usuarioService = new UsuarioService();
		tratamientoService = new TratamientoService();
		pasosService = new PasoService();
		pacienteService = new PacienteService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ACCION = request.getParameter("ACCION");
		System.out.println("Accion a realizar: [" + ACCION + "]");
		
		if (ACCION.equalsIgnoreCase(ALTA_PACIENTE_1))
			IniciarAlta(request, response);
		else if(ACCION.equalsIgnoreCase(ALTA_TRATAMIENTO))
			AltaTratamientoPaciente(request, response);
		else if(ACCION.equalsIgnoreCase(ELIMINAR_PACIENTE))
			EliminarPaciente(request, response);
		else if(ACCION.equalsIgnoreCase(ALTA_VISTA_TRATAMIENTO))
			AltaVistaTratamiento(request, response);
		else if(ACCION.equalsIgnoreCase(GUARDAR_TRATAMIENTO))
			GuardarTratamiento(request, response);
		else if(ACCION.equalsIgnoreCase(VER_TRATAMIENTO))
			VerTratamiento(request, response, 0);
		else if(ACCION.equalsIgnoreCase(BAJA_PACIENTE))
			EliminarPaciente(request, response);
		else if(ACCION.equalsIgnoreCase(BUSCAR_PACIENTE)) 
			BuscarPaciente(request, response);
		else if (ACCION.equalsIgnoreCase(LOG_OUT))
			LogOUT(request, response);
		else if (ACCION.equalsIgnoreCase(CAMBIAR_FILTROS))
			CambiarFiltroPasos(request, response);
		else if (ACCION.equalsIgnoreCase(INACTIVAR_TRATAMIENTO))
			InactivarTratamiento(request, response);
	}
	
	private void InactivarTratamiento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		tratamientoService.inactivarTratamientoPorIdTratamiento((String)request.getSession().getAttribute("IdTratamiento"));
		Reload(request, response);
		response.sendRedirect("/MedManager/webapp/pages/menu/medico/menu.jsp");
	}
	
	private void AltaVistaTratamiento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("IdPaciente", request.getParameter("IdPaciente"));
		response.sendRedirect("/MedManager/webapp/pages/menu/medico/addTratamiento.jsp");
	}
	
	private void LogOUT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("/MedManager/webapp/pages/login/login.jsp");
	}
	
	private void BuscarPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("BusquedaPacienteMenuMed", request.getParameter("buscar"));
		response.sendRedirect("/MedManager/webapp/pages/menu/medico/menu.jsp");
	}

	private void VerTratamiento(HttpServletRequest request, HttpServletResponse response, int op)
			throws ServletException, IOException {
		if(op == 0) {
			request.getSession().setAttribute("Riesgo", pacienteService.getRiesgoPaciente(request.getParameter("IdTratamiento")));
			request.getSession().setAttribute("IdTratamiento", request.getParameter("IdTratamiento"));
			request.getSession().setAttribute("PasosPaciente", Arrays.asList(pasosService.getPasosPorIdTratamiento(request.getParameter("IdTratamiento"))));
		}else {
			request.getSession().setAttribute("PasosPaciente", Arrays.asList(pasosService.getPasosPorIdTratamiento(((String)request.getSession().getAttribute("IdTratamiento")))));
		}
		
		response.sendRedirect("/MedManager/webapp/pages/menu/medico/VerTratamientoPaciente.jsp");

	}
	
	private void EliminarPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPaciente = request.getParameter("IdPaciente");
		pacienteService.bajaPaciente(idPaciente);
		Reload(request, response);
		
		response.sendRedirect("/MedManager/webapp/pages/menu/medico/menu.jsp");

	}
	
	private void IniciarAlta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!usuarioService.existeMail(request.getParameter("mail"))) {
			request.getSession().setAttribute("errorMail", "No");
			int id_usuario = pacienteService.altaPaciente(request.getParameter("nombre"), 
					request.getParameter("mail"),
					request.getParameter("fecha"),
					request.getParameter("apellido"), 
					String.valueOf(((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getUsuario_id()));

			Reload(request, response);
			
			if(id_usuario != -1 && request.getParameter("CrearTratamiento").equals("Si")) {
				request.getSession().setAttribute("IdPaciente", String.valueOf(id_usuario));
				response.sendRedirect("/MedManager/webapp/pages/menu/medico/addTratamiento.jsp");
			}else {
				request.getSession().setAttribute("verPacientes", "si");
				response.sendRedirect("/MedManager/webapp/pages/menu/medico/menu.jsp");
			}	
		}else {
			request.getSession().setAttribute("errorMail", "Si");
			response.sendRedirect("/MedManager/webapp/pages/menu/medico/addPaciente.jsp");
		}
	}
	
	private void AltaTratamientoPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String inicio = request.getParameter("inicio");
		String clasif = request.getParameter("clasif");
		String cantidad = request.getParameter("cantTumores");

		if(inicio.contains("año"))
			inicio = inicio.replace("ñ", "�");

		if(inicio.contains("é"))
			inicio = inicio.replace("é", "e");
			
		request.getSession().setAttribute("inicio", inicio);
		request.getSession().setAttribute("clasif", clasif);
		request.getSession().setAttribute("fechaC", request.getParameter("fechaC"));
		request.getSession().setAttribute("titulo", request.getParameter("titulo"));
		request.getSession().setAttribute("cantTumores", cantidad);
		request.getSession().setAttribute("tamanoTumores", request.getParameter("tamanoTumores"));
		request.getSession().setAttribute("tumor", request.getParameter("tumor"));
		request.getSession().setAttribute("grado", request.getParameter("grado"));
		request.getSession().setAttribute("cis", request.getParameter("cis"));
		request.getSession().setAttribute("compromiso", request.getParameter("compromisoUretral"));
		
		request.getSession().setAttribute("riesgo", tratamientoService.getClasificacionRiesgo(request.getParameter("cis"), request.getParameter("cantTumores"), request.getParameter("tamanoTumores"), request.getParameter("tumor"), request.getParameter("grado"), inicio, clasif, request.getParameter("compromisoUretral")));
		
		response.sendRedirect("/MedManager/webapp/pages/menu/medico/verNuevoTratamiento.jsp");
	}
	
	private void GuardarTratamiento(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			
			String riesgo = request.getParameter("riesgoFinal");
			if(riesgo.equalsIgnoreCase("Riesgo Intermedio"))
				riesgo = "BCG Intra Vesical";
			
			int id_tratamiento_paciente = tratamientoService.addTratamientoById(
				riesgo,
				(String)request.getSession().getAttribute("compromiso"), 
				(String)request.getSession().getAttribute("fechaC"), 
				(String)request.getSession().getAttribute("titulo"),
				(String)request.getSession().getAttribute("cantTumores"), 
				(String)request.getSession().getAttribute("tamanoTumores"), 
				(String)request.getSession().getAttribute("tumor"), 
				(String)request.getSession().getAttribute("grado"),
				(String)request.getSession().getAttribute("inicio"), 
				(String)request.getSession().getAttribute("clasif"), 
				String.valueOf(((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getUsuario_id()), 
				(String)request.getSession().getAttribute("cis"),
				Integer.parseInt(String.valueOf(request.getSession().getAttribute("IdPaciente"))));
		
		
			Reload(request, response);
		
			request.getSession().removeAttribute("inicio");
			request.getSession().removeAttribute("clasif");
			request.getSession().removeAttribute("fechaC");
			request.getSession().removeAttribute("titulo");
			request.getSession().removeAttribute("cantTumores");
			request.getSession().removeAttribute("tamanoTumores");
			request.getSession().removeAttribute("tumor");
			request.getSession().removeAttribute("grado");
			request.getSession().removeAttribute("cis");
			
			request.getSession().setAttribute("Riesgo", (String)request.getSession().getAttribute("riesgo"));
			request.getSession().removeAttribute("riesgo");
			request.getSession().setAttribute("IdTratamiento", String.valueOf(id_tratamiento_paciente));
			
			VerTratamiento(request, response, 1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERROR SERVLET GUARDARTRATAMIENTO");
		}
	}

	private void Reload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id_medico = ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getUsuario_id();

		request.getSession().setAttribute("Pacientes", Arrays.asList(usuarioService.getPacientesActivosPorId(id_medico)));
		request.getSession().setAttribute("Tratamientos", Arrays.asList(tratamientoService.getTratamientosActivosPorId(id_medico)));		
	}

	private void CambiarFiltroPasos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(((String)request.getSession().getAttribute("FiltroPasos")).equalsIgnoreCase("Solo Pendientes")) {
			request.getSession().setAttribute("FiltroPasos", "Todos");
			response.sendRedirect("/MedManager/webapp/pages/menu/medico/VerTratamientoPaciente.jsp");
		}else {
			request.getSession().setAttribute("FiltroPasos", "Solo Pendientes");
			response.sendRedirect("/MedManager/webapp/pages/menu/medico/VerTratamientoPaciente.jsp");
		}
	}
}
