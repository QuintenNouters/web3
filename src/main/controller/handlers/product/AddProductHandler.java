package main.controller.handlers.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.db.DbException;
import main.model.domain.DomainException;
import main.model.domain.Person;
import main.model.domain.Product;
import main.model.domain.Role;
import main.model.domain.ShopService;
import main.controller.handlers.HomePageHandler;
import main.controller.handlers.RequestHandler;

public class AddProductHandler implements RequestHandler{
	
	private ShopService service;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person user = (Person) request.getAttribute("user");
		List<Role> roles = new ArrayList<>();
		roles.add(Role.ADMIN);
		if(user != null && roles.contains(user.getRole())) {
			Product p = new Product();
			List<String> errors = new ArrayList<>();
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String price = request.getParameter("price");
			
			processProductName(p, name, errors);
			processProductDescription(p, description, errors);
			processProductPrice(p, price, errors);
			
			if(errors.isEmpty()) {
				try {
					service.addProduct(p);
				} catch (DbException e) {
					errors.add(e.getMessage());
				}
			}
			if(errors.isEmpty()) {
				response.sendRedirect("Controller?action=showProductOverviewPage");
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("name", name);
				request.setAttribute("description", description);
				request.setAttribute("price", price);
				request.getRequestDispatcher("addProduct.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "the page \"Add Product\" is only for administrators");
			(new HomePageHandler()).handle(request, response);
		}
	}

	@Override
	public void setModel(ShopService service) {
		this.service = service;
		
	}
	
	public void processProductName(Product p, String name, List<String> errors) {
		try {
			p.setName(name);
		} catch (DomainException message) {
			errors.add(message.getMessage());
		}
	}
	
	public void processProductDescription(Product p, String description, List<String> errors) {
		try {
			p.setDescription(description);
		} catch (DomainException message) {
			errors.add(message.getMessage());
		}
	}
	
	public void processProductPrice(Product p, String priceS, List<String> errors) {
		try {
			Double price = Double.parseDouble(priceS);
			p.setPrice(price);
		} catch (DomainException | NumberFormatException message) {
			errors.add(message.getMessage());
		}
	}
	
	public void processProductId(List<String> errors, Product p) {
		try {
			Random r = new Random();
			p.setProductId(r.nextInt());
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}
	}

}
