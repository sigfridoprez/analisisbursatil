package servicio;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for servicio");
		//$JUnit-BEGIN$
		TestServicio.inicia();
		suite.addTest(new TestServicio("testServicio"));
		//$JUnit-END$
		return suite;
	}

}
