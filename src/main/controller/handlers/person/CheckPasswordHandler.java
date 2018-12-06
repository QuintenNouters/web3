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

public class CheckPasswordHandler implements RequestHandler{

	private ShopService service;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person user = (Person) request.getAttribute("user");
		List<Role> roles = new ArrayList<>();
		roles.add(Role.ADMIN);
		if(user != null && roles.contains(user.getRole())) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			if(service.checkPassword(id, password)) {
				request.setAttribute("result", "The filled in password is CORRECT");
				request.getRequestDispatcher("checkPassword.jsp").forward(request, response);
			} else {
				request.setAttribute("result", "the filled in password is NOT CORRECT");
				request.getRequestDispatcher("checkPassword.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "the page \"Check Password\" is only for administrators");
			(new HomePageHandler()).handle(request, response);
		}
		
	}

	@Override
	public void setModel(ShopService service) {
		this.service = service;
		
	}

}
