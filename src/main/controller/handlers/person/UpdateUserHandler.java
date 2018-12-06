package main.controller.handlers.person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.db.DbException;
import main.model.domain.DomainException;
import main.model.domain.Person;
import main.model.domain.Role;
import main.model.domain.ShopService;
import main.controller.handlers.HomePageHandler;
import main.controller.handlers.RequestHandler;

public class UpdateUserHandler implements RequestHandler{

	private ShopService s;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person user = (Person) request.getAttribute("user");
		List<Role> roles = new ArrayList<>();
		roles.add(Role.ADMIN);
		if(user != null && roles.contains(user.getRole())) {
			
			Person p = new Person();
			List<String> errors = new ArrayList<>();
			
			String id = request.getParameter("id");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String role = request.getParameter("role");
			
			processUserId(p, id, errors);
			processUserFirstName(p, firstName, errors);
			processUserLastName(p, lastName, errors);
			processUserEmail(p, email, errors);
			processUserPassword(p, password, errors);
			processUserRole(p, role, errors);
			
			if(errors.isEmpty()) {
				try {
					s.updatePerson(p);
				} catch (DbException e) {
					errors.add(e.getMessage());
				}
			}
			if(errors.isEmpty()) {
				response.sendRedirect("Controller?action=showUserOverviewPage");
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("person", p);				
				request.getRequestDispatcher("updatePerson.jsp").forward(request, response);
			}
			
		} else {
			request.setAttribute("error", "the page \"Update person\" is only for administrators");
			(new HomePageHandler()).handle(request, response);
		}
		
	}

	@Override
	public void setModel(ShopService service) {
		this.s = service;
		
	}
	
	public void processUserId(Person p, String id, List<String> errors) {
		try {
			p.setUserId(id);
		} catch (DomainException message) {
			errors.add(message.getMessage());
		}
	}
	public void processUserFirstName(Person p, String firstName, List<String> errors) {
		try {
			p.setFirstName(firstName);
		} catch (DomainException message) {
			errors.add(message.getMessage());
		}
	}
	public void processUserLastName(Person p, String LastName, List<String> errors) {
		try {
			p.setLastName(LastName);
		} catch (DomainException message) {
			errors.add(message.getMessage());
		}
	}
	public void processUserEmail(Person p, String email, List<String> errors) {
		try {
			p.setEmail(email);
		} catch (DomainException message) {
			errors.add(message.getMessage());
		}
	}
	public void processUserPassword(Person p, String password, List<String> errors) {
		try {
			p.setPasswordHashed(password);
		} catch (DomainException message) {
			errors.add(message.getMessage());
		}
	}
	public void processUserRole(Person p, String role, List<String> errors) {
		try {
			p.setRole(Role.valueOf(role.toUpperCase()));
		} catch (DomainException message) {
			errors.add(message.getMessage());
		}
	}

}
