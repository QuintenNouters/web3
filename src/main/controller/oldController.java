package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.db.DbException;
import main.model.domain.DomainException;
import main.model.domain.Person;
import main.model.domain.Product;
import main.model.domain.ShopService;

/**
 * Servlet implementation class WebShopServlet
 */
@WebServlet("/oldController")
public class oldController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private ShopService service;
	private Properties prop;
//	@Override
//	public void init() throws ServletException {
//		super.init();
//		ServletContext context = getServletContext();
//		service = (ShopService) context.getAttribute("shopService");
//	}
	
    public oldController() {
        super();
        service = new ShopService(prop);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action == null) {
			action = "";
		}
		processRequest(request, response, action);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action == null) {
			action = "";
		}
		processRequest(request, response, action);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
		
		//GET COOKIE FROM USER
		Cookie q = null;
		if(request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if(c.getName().equals("color")) {
					q = c;
					break;
				}
			}
		}
		//MAKE A COOKIE WHEN USER HAS NO COOKIE
		if(q == null) {
			q = new Cookie("color", "yellow");
		}
		String color = q.getValue();
		if(action.equals("changeColor")) {
			action = changeColor(request, response, q);
			color = q.getValue();
		}
		
		if(color.equals("yellow") | color.equals("red")) {
			request.setAttribute("color", color);
		}
		
		String goal;
		switch (action) {
		case "showUserOverviewPage":
			goal = showUserOverviewPage(request, response);
			break;
		case "showProductOverviewPage":
			goal = showProductOverviewPage(request, response);
			break;
		case "showAddProductPage":
			goal = "addProduct.jsp";
			break;
		case "doAddProduct":
			goal = addProduct(request, response);
			break;
		case "showUpdateProductPage":
			goal = showUpdateProductPage(request, response);
			break;
		case "doUpdateProduct":
			goal = updateProduct(request,response);
			break;
		case "showDeleteProductPage":
			goal = "deleteProduct.jsp";
			break;
		case "doDeleteProduct":
			goal = deleteProduct(request, response);
			break;
		case "showDeleteUserPage":
			goal = "deleteUser.jsp";
			break;
		case "doDeleteUser":
			goal = deleteUser(request, response);
			break;
		case "logInPage":
			goal = "logIn.jsp";
			break;
		case "logIn":
			goal = logIn(request, response);
			break;
		case "logOut":
			goal = logOut(request, response);
			break;
		case "showSignUpPage":
			goal = "signUp.jsp";
			break;
		case "doSignUp":
			goal = signUpPerson(request, response);
			break;
		case "checkPasswordPage":
			goal = "checkPassword.jsp";
			break;
		case "checkPassword":
			goal = checkPassword(request, response);
			break;
		default:
			goal = "index.jsp";
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(goal);
		rd.forward(request, response);
	}
	
	private String showUserOverviewPage(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("allUsers", service.getPersons());
		return "useroverview.jsp";
	}
	
	private String showProductOverviewPage(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("allProducts", service.getProducts());
		return "productoverview.jsp";
	}
	
	private String showUpdateProductPage(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("product", service.getProduct(id));
		return "updateProduct.jsp";
	}
	
	private String signUpPerson(HttpServletRequest request, HttpServletResponse response) {
		Person p = new Person();
		List<String> errors = new ArrayList<>();
		processUserId(p, request, errors);
		processFirstName(p, request, errors);
		processLastName(p, request, errors);
		processEmail(p, request, errors);
		processPassword(p, request, errors);
		if(errors.isEmpty()) {
			try {
				service.addPerson(p);
			} catch (DbException e) {
				errors.add(e.getMessage());
			}
		}
		if(errors.isEmpty()) {
			return showUserOverviewPage(request, response);
		} else {
			request.setAttribute("errors", errors);
			return "signUp.jsp";
		}
	}
	
	private void processUserId(Person p, HttpServletRequest request, List<String> errors) {
		try {
			String id = request.getParameter("userid");
			p.setUserId(id);
			request.setAttribute("userIdState", "OK");
			request.setAttribute("filledInUserId", id);
		} catch (IllegalArgumentException message) {
			request.setAttribute("userIdState", "NOK");
			errors.add(message.getMessage());
		}
	}
	private void processFirstName(Person p, HttpServletRequest request, List<String> errors) {
		try {
			String firstName = request.getParameter("firstName");
			p.setFirstName(firstName);
			request.setAttribute("firstNameState", "OK");
			request.setAttribute("filledInFirstName", firstName);
		} catch (IllegalArgumentException message) {
			request.setAttribute("firstNameState", "NOK");
			errors.add(message.getMessage());
		}
	}
	private void processLastName(Person p, HttpServletRequest request, List<String> errors) {
		try {
			String lastName = request.getParameter("lastName");
			p.setLastName(lastName);
			request.setAttribute("lastNameState", "OK");
			request.setAttribute("filledInLastName", lastName);
		} catch (IllegalArgumentException message) {
			request.setAttribute("lastNameState", "NOK");
			errors.add(message.getMessage());
		}
	}
	private void processEmail(Person p, HttpServletRequest request, List<String> errors) {
		try {
			String email = request.getParameter("email");
			p.setEmail(email);
			request.setAttribute("emailState", "OK");
			request.setAttribute("filledInEmail", email);
		} catch (IllegalArgumentException message) {
			request.setAttribute("emailState", "NOK");
			errors.add(message.getMessage());
		}
	}
	private void processPassword(Person p, HttpServletRequest request, List<String> errors) {
		try {
			String password = request.getParameter("password");
			p.setPasswordHashed(password);
			request.setAttribute("passwordState", "OK");
		} catch (IllegalArgumentException message) {
			request.setAttribute("passwordState", "NOK");
			errors.add(message.getMessage());
		}
	}
	
	private String addProduct(HttpServletRequest request, HttpServletResponse response) {
		Product p = new Product();
		List<String> errors = new ArrayList<>();
		processProductName(p, request, errors);
		processProductDescription(p, request, errors);
		processProductPrice(p, request, errors);
		if(errors.isEmpty()) {
			try {
				service.addProduct(p);;
			} catch (DbException e) {
				errors.add(e.getMessage());
			}
		}
		if(errors.isEmpty()) {
			return showProductOverviewPage(request, response);
		} else {
			request.setAttribute("errors", errors);
			return "addProduct.jsp";
		}
	}
	
	private void processProductName(Product p, HttpServletRequest request, List<String> errors) {
		try {
			String name = request.getParameter("name");
			p.setName(name);
			request.setAttribute("nameState", "OK");
			request.setAttribute("filledInName", name);
		} catch (DomainException message) {
			request.setAttribute("nameState", "NOK");
			errors.add(message.getMessage());
		}
	}
	
	private void processProductDescription(Product p, HttpServletRequest request, List<String> errors) {
		try {
			String description = request.getParameter("description");
			p.setDescription(description);
			request.setAttribute("descriptionState", "OK");
			request.setAttribute("filledInDescription", description);
		} catch (DomainException message) {
			request.setAttribute("descriptionState", "NOK");
			errors.add(message.getMessage());
		}
	}
	
	private void processProductPrice(Product p, HttpServletRequest request, List<String> errors) {
		try {
			Double price = Double.parseDouble(request.getParameter("price"));
			p.setPrice(price);
			request.setAttribute("priceState", "OK");
			request.setAttribute("filledInPrice", price);
		} catch (DomainException | NumberFormatException message) {
			request.setAttribute("priceState", "NOK");
			errors.add(message.getMessage());
		}
	}
	
	
	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		Product p = new Product();
		List<String> errors = new ArrayList<>();
		processProductId(p, request, errors);
		processProductName(p, request, errors);
		processProductDescription(p, request, errors);
		processProductPrice(p, request, errors);
		if(errors.isEmpty()) {
			try {
				service.updateProduct(p);
			} catch (DbException e) {
				errors.add(e.getMessage());
			}
		}
		if(errors.isEmpty()) {
			return showProductOverviewPage(request, response);
		} else {
			request.setAttribute("errors", errors);
			
			//kan beter in html met als param null is
			request.setAttribute("product", service.getProduct(p.getProductId()));
			return "updateProduct.jsp";
		}
	}
	
	private void processProductId(Product p, HttpServletRequest request, List<String> errors) {
		try {
			int id = Integer.parseInt(request.getParameter("productIdUpdate"));
			p.setProductId(id);
			request.setAttribute("productIdState", "OK");
			request.setAttribute("filledInProductId", id);
		} catch (DomainException | NumberFormatException message) {
			request.setAttribute("productIdState", "NOK");
			errors.add(message.getMessage());
		}
	}
	
	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("productId"));
		service.deleteProduct(id);
		return showProductOverviewPage(request, response);
	}
	
	private String deleteUser(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("userId");
		service.deletePerson(id);
		return showUserOverviewPage(request, response);
	}
	
	private String checkPassword(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		if(service.checkPassword(id, password)) {
			request.setAttribute("result", "The filled in password is CORRECT");
			return "checkPassword.jsp";
		} else {
			request.setAttribute("result", "the filled in password is NOT CORRECT");
			return "checkPassword.jsp";
		}
	}
	
	private String changeColor(HttpServletRequest request, HttpServletResponse response, Cookie c) {
		String origin = request.getParameter("page");
		if(c.getValue().equals("yellow")) {
			c.setValue("red");
		}else {
			c.setValue("yellow");
		}
		response.addCookie(c);
		return origin;
	}
	
	private String logIn(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("userid");
		String password = request.getParameter("password");
		Person p = service.getUserIfAuthenticated(id, password);
		if(p != null) {
			request.getSession().setAttribute("userid", id);
			authenticate(request);
			return "index.jsp";
		} else {
			request.setAttribute("error", "Wrong userId or password");
			return "logIn.jsp";
		}
	}
	
	private void authenticate(HttpServletRequest request) {
		Person user;
		if (request.getSession().getAttribute("userid") != null) {
			request.setAttribute("auth", true);
			user = service.getPerson(request.getSession().getAttribute("userid").toString());
			request.setAttribute("user", user);
		} else {
			request.setAttribute("auth", false);
		}
	}
	
	private String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Something went wrong", e);
		}
		authenticate(request);
		return "index.jsp";
	}
}
