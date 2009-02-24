package main;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import persistencia.Ejemplo;


public class Main {
	
	public static void main(String[] arg){
		System.out.println("Hola mundo");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Ejemplo ej = new Ejemplo(new BigDecimal("200000"));
		
		Transaction tx;
		
		/*Transaction tx=pm.currentTransaction();
		tx.begin();
			pm.makePersistent(ej);
			//Object ob = pm.getObjectById(ej,true);
			//System.out.println(ob);
		tx.commit();*/
		
		Object ob;
		Object ob2;
		
		// Basic Extent of all Products
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Retrieving Extent for Products");
            ob = pm.getObjectById(Ejemplo.class,new BigDecimal("200"));
            System.out.println(((Ejemplo)ob).getIdConsecutivo());
            
            /*Extent e = pm.getExtent(Ejemplo.class, true);
            Iterator iter = e.iterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                System.out.println(">  " + obj);
            }*/
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println("Exception thrown during retrieval of Extent : " + e.getMessage());
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        System.out.println("");
		System.out.println("DEspues");
	}
}	
