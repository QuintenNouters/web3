package main.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.domain.ShopService;

public class HomePageHandler implements RequestHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	@Override
	public void setModel(ShopService service) {
		// TODO Auto-generated method stub
		
	}

}
