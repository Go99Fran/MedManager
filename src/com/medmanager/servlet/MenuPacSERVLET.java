package com.medmanager.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medmanager.service.IPasoService;
import com.medmanager.service.impl.PasoService;

/**
 * @author FGonzalez
 */
@WebServlet("/Resources/Actions/MenuPacAction.do")
public class MenuPacSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private final static String LOG_OUT = "LogOut";
	private final static String PASO_CHECK = "CheckPaso";
	private final static String PASO_ANULAR = "AnularPaso";
	private final static String PASO_DESHACER = "DeshacerPaso";
	private final static String CAMBIAR_FILTRO = "CambiarFiltros";

	private IPasoService pasosService;

    public MenuPacSERVLET() {
        super();
     	pasosService = new PasoService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ACCION = request.getParameter("Action");
		System.out.println("Accion [" + ACCION + "]");
		
		if(ACCION.equalsIgnoreCase(LOG_OUT))
			LogOut(request, response);
		else if(ACCION.equalsIgnoreCase(PASO_CHECK))
			PasoCheck(request, response);
		else if(ACCION.equalsIgnoreCase(PASO_ANULAR))
			PasoAnular(request, response);
		else if(ACCION.equalsIgnoreCase(CAMBIAR_FILTRO))
			CambiarFiltroPasos(request, response);
		else if(ACCION.equalsIgnoreCase(PASO_DESHACER))
			PasoDeshacer(request, response);
	}
	
	private void PasoDeshacer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pasosService.accionPaso(request.getParameter("IdPaciente"), request.getParameter("PasoID"), 1);
		
		Reload(request, response, request.getParameter("IdPaciente"));
		response.sendRedirect("/MedManager/webapp/pages/menu/paciente/menu.jsp");
	}
	
	private void CambiarFiltroPasos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(((String)request.getSession().getAttribute("FiltroPasos")).equalsIgnoreCase("Solo Pendientes")) {
			request.getSession().setAttribute("FiltroPasos", "Todos");
			response.sendRedirect("/MedManager/webapp/pages/menu/paciente/menu.jsp");
		}else {
			request.getSession().setAttribute("FiltroPasos", "Solo Pendientes");
			response.sendRedirect("/MedManager/webapp/pages/menu/paciente/menu.jsp");
		}
	}

	private void PasoCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		pasosService.accionPaso(request.getParameter("IdPaciente"), request.getParameter("PasoID"), 2);
		
		Reload(request, response, request.getParameter("IdPaciente"));
		response.sendRedirect("/MedManager/webapp/pages/menu/paciente/menu.jsp");
	}
	
	private void PasoAnular(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		pasosService.accionPaso(request.getParameter("IdPaciente"), request.getParameter("PasoID"), 3);
		
		Reload(request, response, request.getParameter("IdPaciente"));
		response.sendRedirect("/MedManager/webapp/pages/menu/paciente/menu.jsp");
	}
	
	private void LogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("/MedManager/webapp/pages/login/login.jsp");
	}
	
	private void Reload(HttpServletRequest request, HttpServletResponse response, String idPac) throws ServletException, IOException {
		System.out.println("Reload Pasos");
		request.getSession().setAttribute("Scroll", request.getParameter("scrollPosition"));
		request.getSession().setAttribute("Pasos", Arrays.asList(pasosService.getPasosPorIdPaciente(String.valueOf(idPac))));
	}
}
