package com.sandra.Biblioteca1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	java.util.logging.Logger.getLogger("org.hibernate")
		.setLevel(java.util.logging.Level.SEVERE);
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	if (session!=null) System.out.println("Sesión abierta");
    	else System.out.println("Fallo en la sesión");
    }
}
