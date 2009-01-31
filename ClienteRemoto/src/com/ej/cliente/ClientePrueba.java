package com.ej.cliente;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.ej.Hello;
import com.ej.home.HelloHome;

public class ClientePrueba {
	public static void main(String[] arg){
		System.out.println("Hola mundo");
		Properties props = System.getProperties();
		Object obj;
		Hello hello;
		
		try {
			props.put("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");
			System.out.println(props);
			
			Context ctx = new InitialContext(props);
			obj = ctx.lookup("HelloWorldEJBRemoteHome");
			//obj = ctx.lookup("HelloWorldEJBLocalHome");
			
			HelloHome helloHome = (HelloHome) PortableRemoteObject.narrow(obj,HelloHome.class);
			hello = helloHome.create();
			
			System.out.println(hello.sayHello());
			
			hello.remove();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoveException e) {
			e.printStackTrace();
		}
	}
}
