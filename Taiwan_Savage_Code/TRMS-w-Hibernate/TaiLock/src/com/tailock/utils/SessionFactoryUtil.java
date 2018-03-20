package com.tailock.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
	private static SessionFactory sf;

    static {
        //create the configuration object
        Configuration config = new Configuration();
        //pass our hiberate.cfg.xml to the Configuration object
        config.configure("hibernate.cfg.xml");

        //Register the configuration as a standard service
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

        //build our session factory
        sf = config.buildSessionFactory(serviceRegistry);
    }

    public static Session getSession() {
        return sf.openSession();
    }
    public static SessionFactory getSf(){ return sf; }
}