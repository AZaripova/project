package ru.datateh.GWTACT.server.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory 
{
    
    MySessionFactory(){}
    
    private static Session session;
    
    static Session getSession()
    {
        Configuration config = new Configuration();
        config.configure();
        SessionFactory factory = config.buildSessionFactory();
        session = factory.openSession();
        return session;
    }
            
}
