package mx.com.carga;

import junit.framework.TestCase;

public class TestCargaArchivo extends TestCase {

	public void testCargaArchivo(){
		CargaArchivo carga=new CargaArchivo();
		carga.cargaArchivo("/Users/sigfridoperezcoapango/TITULACION/Historial/Feb 2008/26_Feb_2008.csv");
	}
}
