package com.example.WebFormHandling;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.configure();
		sessionFactory = sessionFactory == null? configuration.buildSessionFactory() : sessionFactory;
		return sessionFactory;
	}
}
