package com.example.WebFormHandling;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfigurationUtils {
	private static Configuration configuration;
	private static SessionFactory sessionFactory = null;
	
	static {
		configuration = new Configuration();
		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(Book.class);
		configuration.configure();
	}
	
	public static SessionFactory getSessionFactory() throws HibernateException {
		sessionFactory = sessionFactory == null? configuration.buildSessionFactory() : sessionFactory;
		return sessionFactory;
	}
}
