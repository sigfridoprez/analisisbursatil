package com.ej.sessiobean;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class HelloBean implements SessionBean {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private SessionContext ctx;
	private int i;
	
	public void ejbCreate(){
		System.out.println("ejbCreate");
		i=0;
	}
	
	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		System.out.println("ejbActivate");
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		System.out.println("ejbPassivate");
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		System.out.println("ejbRemove");
		i=0;
	}

	@Override
	public void setSessionContext(SessionContext ctx) throws EJBException,
			RemoteException {
		System.out.println("setSessionContext");
		this.ctx=ctx;
	}

	public String sayHello(){
		System.out.println("sayHello");
		i++;
		return "Hello World "+i+"  !!!!!!";
	}
}
