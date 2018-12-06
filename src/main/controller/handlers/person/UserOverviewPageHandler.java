package main.controller.handlers.person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.controller.handlers.HomePageHandler;
import main.controller.handlers.RequestHandler;
import main.model.domain.Person;
import main.model.domain.Role;
import main.model.domain.ShopService;

public class UserOverviewPageHandler implements RequestHandler{
	
	private ShopService service;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person user = (Person) request.getAttribute("user");
		List<Role> roles = new ArrayList<>();
		roles.add(Role.ADMIN);
		roles.add(Role.CUSTOMER);
		if(user != null && roles.contains(user.getRole())) {
			request.setAttribute("allUsers", service.getPersons());
			request.getRequestDispatcher("useroverview.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "the page \"User Overview\" is only for administrators or customers. Please log in to get access");
			(new HomePageHandler()).handle(request, response);
		}
		
		
	}

	@Override
	public void setModel(ShopService service) {
		this.service = service;
		
	}
}
