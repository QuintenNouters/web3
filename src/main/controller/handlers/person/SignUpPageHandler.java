package main.controller.handlers.person;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.domain.ShopService;
import main.controller.handlers.RequestHandler;

public class SignUpPageHandler implements RequestHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("signUp.jsp").forward(request, response);
	}

	@Override
	public void setModel(ShopService service) {
		// TODO Auto-generated method stub
		
	}

}
