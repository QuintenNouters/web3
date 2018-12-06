package main.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.domain.Person;
import main.model.domain.ShopService;
import main.controller.handlers.RequestHandler;
import main.controller.handlers.RequestHandlerFactory;

/**
 * Servlet implementation class WebShopServlet
 */
@WebServlet("/Controller")
public class WebShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShopService service;
	private RequestHandlerFactory requestHandlerFactory;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = getServletContext();
		service = (ShopService) context.getAttribute("shopService");
		requestHandlerFactory = (RequestHandlerFactory) context.getAttribute("handlerFactory");
	}

	public WebShopServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "showHomePage";
		}
		processRequest(request, response, action);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "showHomePage";
		}
		processRequest(request, response, action);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response, String action)
			throws ServletException, IOException {
		cookieMethod(request, response);
		authenticate(request);
		RequestHandler handler = requestHandlerFactory.getHandler(action);
		handler.handle(request, response);
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

	private void cookieMethod(HttpServletRequest request, HttpServletResponse response) {
		// GET COOKIE FROM USER
		Cookie q = null;
		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals("color")) {
					q = c;
					break;
				}
			}
		}
		// MAKE A COOKIE WHEN USER HAS NO COOKIE
		if (q == null) {
			q = new Cookie("color", "yellow");
		}
		String color = q.getValue();

		if (color.equals("yellow") | color.equals("red")) {
			request.setAttribute("color", color);
		}
	}
}
