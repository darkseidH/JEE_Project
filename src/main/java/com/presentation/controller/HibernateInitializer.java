package com.presentation.controller;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HibernateInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        // Initialize EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        // Store EntityManagerFactory in the servlet context
        servletContext.setAttribute("entityManagerFactory", emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Cleanup resources if needed
        EntityManagerFactory emf = (EntityManagerFactory) servletContextEvent.getServletContext().getAttribute("entityManagerFactory");
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

