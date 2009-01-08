package servicio;

import mx.com.cargainformacionipc.servicio.CargaInformacionIntraDiaSrv;
import mx.com.cargainformacionipc.servicio.CargaInformacionSrv;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;
import mx.com.infraparseohtml.servicio.ServicioParseoTabla;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import test.TestSrv;


import junit.framework.TestCase;

public class TestServicio extends TestCase {
	private static BeanFactory factory=null;
	private static Resource res = null;
	private ServicioParseoTabla servicioParseoTabla;
	private CargaInformacionSrv cargaInformacionSrv;
	private CargaInformacionIntraDiaSrv cargaInformacionIntraDiaSrv;
	private TestSrv testSrv;
	
	public TestServicio(String str){
		super(str);
	}
	
	public static void inicia(){
		if (factory == null) {
			res = new FileSystemResource("WebContent/WEB-INF/conf/applicationContext.xml");
			System.out.println("::::::::::::::::::::::: CONTEXT FILE:::::::::::::::::::::: ");
			factory = new XmlBeanFactory(res);
		}
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		BasicConfigurator.configure();
		if(cargaInformacionSrv==null){
			cargaInformacionSrv = (CargaInformacionSrv)factory.getBean("cargaInformacionSrv");
		}
		if(servicioParseoTabla==null){
			servicioParseoTabla = (ServicioParseoTabla)factory.getBean("servicioParseoTabla");
		}
		if(testSrv==null){
			testSrv = (TestSrv)factory.getBean("testSrv");
		}
		if(cargaInformacionIntraDiaSrv==null){
			cargaInformacionIntraDiaSrv = (CargaInformacionIntraDiaSrv)factory.getBean("cargaInformacionIntraDiaSrv");
		}
	}
	
	public void testCarga()throws BusinessException, PaginaSinDatosException{
		testSrv.testCarga();
	}
	
	public void testServicio() throws BusinessException, PaginaSinDatosException{
		System.out.println("hola mundo");
		cargaInformacionSrv.cargaInformacion();
	}
	
	public void testServicioIntradia() throws BusinessException, PaginaSinDatosException{
		System.out.println("hola mundo");
		cargaInformacionIntraDiaSrv.cargaInformacion();
	}
}
