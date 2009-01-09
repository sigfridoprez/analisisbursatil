package mx.com.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class ApplicationFactory {
	private static ApplicationFactory INSTANCE = null;
	private static BeanFactory factory=null;
	private static Resource res = null;
	
	private ApplicationFactory(){
		if (factory == null) {
			res = new FileSystemResource("config/applicationContext.xml");
			//res = new FileSystemResource("bin/config/applicationContext.xml");
			System.out.println("::::::::::::::::::::::: CONTEXT FILE:::::::::::::::::::::: ");
			factory = new XmlBeanFactory(res);
		}
	}
	
	private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new ApplicationFactory();
        }
    }
 
    public static ApplicationFactory getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    
    public Object getBean(String strServicio){
    	if(factory!=null){
    		return factory.getBean(strServicio);
    	}else{
    		return null;
    	}
    }
}
