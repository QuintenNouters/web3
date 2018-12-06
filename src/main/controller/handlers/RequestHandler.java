package main.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.domain.ShopService;

public interface RequestHandler {
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	public void setModel(ShopService service);
}
