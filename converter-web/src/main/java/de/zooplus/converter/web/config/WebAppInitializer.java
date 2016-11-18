package de.zooplus.converter.web.config;


import de.zooplus.converter.dao.config.DatabaseEmbeddedConfig;
import de.zooplus.converter.service.config.ServiceConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Initializer of the web application (context setup, servlet creation, etc.).
 */
public class WebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
        ctx.setConfigLocations(new String[] {
                ServiceConfig.class.getPackage().getName(),
                DatabaseEmbeddedConfig.class.getPackage().getName()
        });
    }


}