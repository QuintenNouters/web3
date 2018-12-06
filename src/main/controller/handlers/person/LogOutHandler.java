package main.controller.handlers.person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.domain.Person;
import main.model.domain.Role;
import main.model.domain.ShopService;
import main.controller.handlers.HomePageHandler;
import main.controller.handlers.RequestHandler;

public class LogOutHandler implements RequestHandler{
	
	private ShopService s;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person user = (Person) request.getAttribute("user");
		List<Role> roles = new ArrayList<>();
		roles.add(Role.ADMIN);
		roles.add(Role.CUSTOMER);		
		if(user != null && roles.contains(user.getRole())) {
			try {
				request.getSession().invalidate();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException("Something went wrong", e);
			}
			authenticate(request);
			response.sendRedirect("Controller");
		} else {
			request.setAttribute("error", "You need to be logged in to perform a log out");
			(new HomePageHandler()).handle(request, response);
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
