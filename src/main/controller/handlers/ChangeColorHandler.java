package main.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.domain.ShopService;

public class ChangeColorHandler implements RequestHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String origin = request.getParameter("page");
		String color = (String) request.getAttribute("color");
		Cookie c = new Cookie("color", color);
		if(c.getValue().equals("yellow")) {
			c.setValue("red");
		}else {
			c.setValue("yellow");
		}
		response.addCookie(c);
		response.sendRedirect("Controller?action=" + origin);
	}

	@Override
	public void setModel(ShopService service) {
		// TODO Auto-generated method stub
		
	}

}
