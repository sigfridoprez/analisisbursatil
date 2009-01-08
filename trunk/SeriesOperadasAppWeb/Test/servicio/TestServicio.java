package servicio;

import java.util.ArrayList;
import java.util.List;

import mx.com.business.mainservice.seriesoperadas.SeriesOperadasMainSrv;
import mx.com.infraestructura.exceptions.BusinessException;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


import junit.framework.TestCase;

public class TestServicio extends TestCase {
	private static BeanFactory factory=null;
	private static Resource res = null;
	private SeriesOperadasMainSrv seriesOperadasMainSrv;
	
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
		if(seriesOperadasMainSrv==null){
			seriesOperadasMainSrv = (SeriesOperadasMainSrv)factory.getBean("seriesOperadasMainSrv");
		}
	}
	
	public void testServicio() throws BusinessException{
		System.out.println("hola mundo");
		List<String> lstSeriesGraficar = new ArrayList<String>();
		
		lstSeriesGraficar.add("ALFA-A");
		lstSeriesGraficar.add("ALSEA-*");
		lstSeriesGraficar.add("AMX-A");
		lstSeriesGraficar.add("ACCELSA-B");
		
		seriesOperadasMainSrv.getGrafica(lstSeriesGraficar, null, null, true);
	}
}
