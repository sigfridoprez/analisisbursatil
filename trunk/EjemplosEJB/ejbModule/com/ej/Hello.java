package com.ej;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Hello extends EJBObject {
	public String sayHello()throws RemoteException;
}
