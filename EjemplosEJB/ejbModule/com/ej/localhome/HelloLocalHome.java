package com.ej.localhome;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

import com.ej.local.HelloLocal;

public interface HelloLocalHome extends EJBLocalHome {
	HelloLocal create()throws CreateException;
}
