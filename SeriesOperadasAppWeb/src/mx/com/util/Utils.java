package mx.com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
	private static SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/mm/yyyy",new Locale("es","MX"));
	
	public String getHora(Date date){
		return getFecha(date,"HH:mm");
	}
	
	public String getFecha(Date date,String pattern){
		sdfFormato.applyPattern(pattern);
		try{
			return sdfFormato.format(date);
		}catch(Exception e){
			return "";
		}
	}
}
