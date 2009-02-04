package com.ej.home;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

import com.ej.Hello;

public interface HelloHome extends EJBHome {

	Hello create()throws RemoteException, CreateException;
}