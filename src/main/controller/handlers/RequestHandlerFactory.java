package main.controller.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import main.model.domain.ShopService;

public class RequestHandlerFactory {
	public Map<String, RequestHandler> handlers = new HashMap<>();
	
	public RequestHandlerFactory(Properties prop, ShopService service) {
		for (Object key : prop.keySet()) {
			RequestHandler handler = null;
			String handlerName = prop.getProperty((String)key);
			try {
				Class<?> handlerClass = Class.forName(handlerName);
				Object handlerObject = handlerClass.newInstance();
				handler = (RequestHandler) handlerObject;
			} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
			handler.setModel(service);
			handlers.put((String)key, handler);
		}
	}
	
	public RequestHandler getHandler(String key) {
		return handlers.get(key);
	}
	
	@Override
	public String toString() {
		return handlers.toString();
	}
}
