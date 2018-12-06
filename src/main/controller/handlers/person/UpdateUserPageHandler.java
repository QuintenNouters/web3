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

public class UpdateUserPageHandler implements RequestHandler{

	private ShopService service;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person user = (Person) request.getAttribute("user");
		List<Role> roles = new ArrayList<>();
		roles.add(Role.ADMIN);
		if(user != null && roles.contains(user.getRole())) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("product", service.getProduct(id));
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "the page \"Update User\" is only for administrators");
			(new HomePageHandler()).handle(request, response);
		}
	}

	@Override
	public void setModel(ShopService service) {
		this.service = service;
		
	}

}
