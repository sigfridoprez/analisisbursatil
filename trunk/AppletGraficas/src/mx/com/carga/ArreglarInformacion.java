package mx.com.carga;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.business.service.seriesoperadas.SeriesOperadasSrv;
import mx.com.factory.ApplicationFactory;
import mx.com.infraestructura.exceptions.BusinessException;

public class ArreglarInformacion {
	private static ApplicationFactory factory = ApplicationFactory
			.getInstance();
	private static SeriesOperadasSrv seriesOperadasSrv;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		seriesOperadasSrv = (SeriesOperadasSrv) factory
				.getBean("seriesOperadasSrv");
		List<SeriesOperadas> lstSeries;
		SeriesOperadas anterior;
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_MONTH, 3);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.YEAR, 2008);
		try {
			lstSeries = seriesOperadasSrv.getListaSeriesOperadas(null, null,
					null, null, '\0',false);
			System.out.println(lstSeries.size());

			for (SeriesOperadas ob : lstSeries) {
				anterior = seriesOperadasSrv.getSerieOperadaAnterior(ob.getId()
						.getEmisora(), ob.getId().getSerie(), ob.getId()
						.getFecha());

				carlcular(ob, anterior);
				seriesOperadasSrv.txUpdateSeriesOperadas(ob);
			}
			System.out.println("Termino");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void carlcular(SeriesOperadas seriesOperadasVO,
			SeriesOperadas voTmp) {
		BigDecimal tmp=null;
		BigDecimal bdPctprecio=null;

		seriesOperadasVO.setVarPctMaximo(new BigDecimal("0"));
		seriesOperadasVO.setVarPctMinimo(new BigDecimal("0"));
		seriesOperadasVO.setVarPct(new BigDecimal("0"));
		seriesOperadasVO.setVarPctPrecio(new BigDecimal("0"));
		seriesOperadasVO.setRendimiento(new BigDecimal("1"));
		if (voTmp != null) {// Calculamos la variacion y rendimiento
			// Calculamos la variacion del precio
			if (voTmp.getPpp().doubleValue() != 0) {
				bdPctprecio = seriesOperadasVO.getPpp().divide(voTmp.getPpp(), 6,
						BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"));
				
				tmp = bdPctprecio; 
				if(Math.abs(bdPctprecio.intValue())<1){
					tmp = tmp.multiply(new BigDecimal("100"));	
				}
				
				seriesOperadasVO.setVarPctPrecio(tmp);
			}
			// Calculamos la variacion de Maximo
			if (voTmp.getPpp().doubleValue() != 0) {
				tmp = seriesOperadasVO.getMaximo().divide(voTmp.getPpp(), 6, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"));
				
				if(Math.abs(tmp.intValue())<1){
					tmp = tmp.multiply(new BigDecimal("100"));	
				}
				
				seriesOperadasVO.setVarPctMaximo(tmp);
			}
			// Calculamos la variacion de Minimo
			if (voTmp.getPpp().doubleValue() != 0) {
				tmp = seriesOperadasVO.getMinimo().divide(voTmp.getPpp(), 6, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"));
				
				if(Math.abs(tmp.intValue())<1){
					tmp = tmp.multiply(new BigDecimal("100"));	
				}
				
				seriesOperadasVO.setVarPctMinimo(tmp);
			}
			// Calculamos el Porcentaje
			seriesOperadasVO.setVarPct(seriesOperadasVO.getVarPctMaximo()
					.subtract(seriesOperadasVO.getVarPctMinimo()));
			// calculamos el rendimiento
			if (voTmp.getRendimiento() != null && bdPctprecio!=null) {
				seriesOperadasVO.setRendimiento(voTmp.getRendimiento()
						.multiply(bdPctprecio).add(
								voTmp.getRendimiento()));
			}
		}
	}
}
