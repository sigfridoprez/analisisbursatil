package examples.session.stateless;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(OHello.class)
public class OHelloBean implements OHello {

	@Override
	public String hello() {
		return "Hello World 2 !!!!!!";
	}

}
