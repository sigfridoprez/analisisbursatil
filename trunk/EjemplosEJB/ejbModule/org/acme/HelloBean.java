package org.acme;

import javax.ejb.Stateful;
import javax.ejb.Stateless;

@Stateful
public class HelloBean implements Hello {
int i=0;

	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		i++;
		return "Hola mundo!!!:::"+i;
	}

}
