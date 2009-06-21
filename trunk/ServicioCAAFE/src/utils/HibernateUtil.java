/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.HibernateEntityManagerFactory;

/**
 *
 * @author Edgar
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    private static Ejb3Configuration ejbConfig = new Ejb3Configuration();
    private static EntityManagerFactory emF;

    private HibernateUtil() {
    }

    public synchronized static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            emF = ejbConfig.configure("/caafes/conf/hibernate.cfg.xml").buildEntityManagerFactory();
            sessionFactory = ((HibernateEntityManagerFactory)emF).getSessionFactory();
        }
        return sessionFactory;
    }
}
