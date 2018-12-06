package main.controller.handlers.person;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.domain.Person;
import main.model.domain.ShopService;
import main.controller.handlers.HomePageHandler;
import main.controller.handlers.RequestHandler;

public class LogInHandler implements RequestHandler{

	private ShopService s;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userid");
		String password = request.getParameter("password");
		Person p = s.getUserIfAuthenticated(id, password);
		if(p != null) {
			request.getSession().setAttribute("userid", id);
			authenticate(request);
			response.sendRedirect("Controller");
		} else {
			request.setAttribute("error", "Wrong userId or password");
			(new LogInPageHandler()).handle(request, response);
		}
	}

	@Override
	public void setModel(ShopService service) {
		this.s = service;
		
	}
	
	private void authenticate(HttpServletRequest request) {
		Person user;
		if (request.getSession().getAttribute("userid") != null) {
			request.setAttribute("auth", true);
			user = s.getPerson(request.getSession().getAttribute("userid").toString());
			request.setAttribute("user", user);
		} else {
			request.setAttribute("auth", false);
		}
	}

}
