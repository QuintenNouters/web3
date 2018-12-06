package main.controller.handlers.person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.db.DbException;
import main.model.domain.Person;
import main.model.domain.Role;
import main.model.domain.ShopService;
import main.controller.handlers.HomePageHandler;
import main.controller.handlers.RequestHandler;

public class SignUpHandler implements RequestHandler{

	private ShopService s;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person p = new Person();
		List<String> errors = new ArrayList<>();
		
		String userId = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		processUserId(p, userId, errors);
		processFirstName(p, firstName, errors);
		processLastName(p, lastName, errors);
		processEmail(p, email, errors);
		processPassword(p, password, errors);
		
		if(errors.isEmpty()) {
			try {
				s.addPerson(p);
			} catch (DbException e) {
				errors.add(e.getMessage());
			}
		}
		if(errors.isEmpty()) {
			response.sendRedirect("Controller");
		} else {
			request.setAttribute("errors", errors);
			request.setAttribute("id", userId);
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.getRequestDispatcher("signUp.jsp").forward(request, response);
		}
	}

	@Override
	public void setModel(ShopService service) {
		this.s = service;
		
	}
	
	private void processUserId(Person p, String userId, List<String> errors) {
		try {
			p.setUserId(userId);
		} catch (IllegalArgumentException message) {
			errors.add(message.getMessage());
		}
	}
	private void processFirstName(Person p, String firstName, List<String> errors) {
		try {
			p.setFirstName(firstName);
		} catch (IllegalArgumentException message) {
			errors.add(message.getMessage());
		}
	}
	private void processLastName(Person p, String lastName, List<String> errors) {
		try {
			p.setLastName(lastName);
		} catch (IllegalArgumentException message) {
			errors.add(message.getMessage());
		}
	}
	private void processEmail(Person p, String email, List<String> errors) {
		try {
			p.setEmail(email);
		} catch (IllegalArgumentException message) {
			errors.add(message.getMessage());
		}
	}
	private void processPassword(Person p, String password, List<String> errors) {
		try {
			p.setPasswordHashed(password);
		} catch (IllegalArgumentException message) {
			errors.add(message.getMessage());
		}
	}
	
}
