package servicio;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for servicio");
		//$JUnit-BEGIN$
		TestServicio.inicia();
		//suite.addTest(new TestServicio("testServicio"));
		suite.addTest(new TestServicio("testServicioIntradia"));
		
		//suite.addTest(new TestServicio("testCarga"));
		//$JUnit-END$
		return suite;
	}

}
