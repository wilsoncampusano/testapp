package com.testapp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebAppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		 AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	        rootContext.register(ApplicationConfig.class);
	 
	        // Manage the lifecycle of the root application context
	        container.addListener(new ContextLoaderListener(rootContext));
	 
	        // Create the dispatcher servlet's Spring application context
//	        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
//	        dispatcherServlet.register(MvcConfig.class);
	             
	        // Register and map the dispatcher servlet
	        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(rootContext));
	        dispatcher.setLoadOnStartup(1);
	        dispatcher.addMapping("/");
	}

}
