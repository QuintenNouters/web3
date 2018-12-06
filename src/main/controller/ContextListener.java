package main.controller;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import main.model.domain.ShopService;
import main.controller.handlers.RequestHandlerFactory;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private RequestHandlerFactory handlerFactory;
    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext context = sce.getServletContext();
    	try {
			//this is for db connection parameters
    		Properties prop = new Properties();
			Enumeration<String> parameterNames = context.getInitParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				prop.setProperty(paramName, context.getInitParameter(paramName));
			}
			ShopService s = new ShopService(prop);
			context.setAttribute("shopService", s);
			
			//this is for HandlerFactory parameters
			InputStream input = context.getResourceAsStream("/WEB-INF/handlers.xml");
			Properties properties = new Properties();
			properties.loadFromXML(input);
			handlerFactory = new RequestHandlerFactory(properties, s);
			context.setAttribute("handlerFactory", handlerFactory);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	
}
