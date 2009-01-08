package mx.com.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.DiasFestivos;

public final class Utils {

	public final Date getDiaHabilAnterior(List<DiasFestivos> lstDiasFestivos,Date fechaHoy){
		Calendar calFechaHoy = Calendar.getInstance();
		Calendar diasFestivos = Calendar.getInstance();
		boolean blnDiaFestivo = false;
		
		calFechaHoy.setTime(fechaHoy);
		
		calFechaHoy.add(Calendar.DATE,-1);
		
		do{
			//Con esto nos aseguramos que la fecha de hoy menos uno no sea una fecha festiva
			for(DiasFestivos diasFestivosVO:lstDiasFestivos){
				diasFestivos.setTime(diasFestivosVO.getFecha());
				if(diasFestivos.get(Calendar.DAY_OF_MONTH)==calFechaHoy.get(Calendar.DAY_OF_MONTH) &&
						diasFestivos.get(Calendar.MONTH)==calFechaHoy.get(Calendar.MONTH) &&
						diasFestivos.get(Calendar.YEAR)==calFechaHoy.get(Calendar.YEAR)){
					blnDiaFestivo=true;
					calFechaHoy.add(Calendar.DATE,-1);
					break;
				}
			}
			//con esto nos asegurmos que la fecha no sea un fin de semana
			if(calFechaHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
				calFechaHoy.add(Calendar.DATE,-1);//nos vanos al viernes
				blnDiaFestivo=true;
			}else if(calFechaHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
				calFechaHoy.add(Calendar.DATE,-2);//nos vanos al viernes
				blnDiaFestivo=true;
			}else{
				blnDiaFestivo=false;
			}
		}while(blnDiaFestivo==true);
		//Ya obtubimos la fecha anterior habil
		
		return calFechaHoy.getTime();
	}
}
