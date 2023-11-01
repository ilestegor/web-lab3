package com.ilestegor.lab3.utils;


import com.ilestegor.lab3.beans.ResultBean;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * Utility hibernate class for configuration and getting session factory
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.load(HibernateUtils.class.getClassLoader().getResourceAsStream(System.getenv("DBConfig")));
            System.out.println(properties.getProperty("password"));
            sessionFactory = new Configuration().configure().setProperty(AvailableSettings.USER, properties.getProperty("user")).
                    setProperty(AvailableSettings.PASS, properties.getProperty("password")).
                    addAnnotatedClass(ResultBean.class).buildSessionFactory();
        } catch (IOException ex) {
            System.err.println("Something went wrong");
        }
        return sessionFactory;
    }
}
