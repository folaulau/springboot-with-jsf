package com.folau.jsf.config;

import jakarta.faces.webapp.FacesServlet;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configuration
public class JSFConfig {

//    @Bean
//    public ServletRegistrationBean<FacesServlet> facesServlet() {
//        ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<FacesServlet>(new FacesServlet(), "*.xhtml");
//        registration.setLoadOnStartup(1);
//        return registration;
//    }
}
