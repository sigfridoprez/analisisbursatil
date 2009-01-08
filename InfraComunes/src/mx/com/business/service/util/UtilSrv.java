package mx.com.business.service.util;

import mx.com.analisispreciosmercado.conf.IndicesIntraDia;
import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.infraestructura.exceptions.BusinessException;

public interface UtilSrv {
	
	public int getMaximaSerieIntraDiaCarga()throws BusinessException;
	public SeriesOperadasIntraDia getSerieIntradiaAnterior(int idCargaActual,String emisora,String serie)throws BusinessException;
	
	public int getMaximaIndiceIntraDiaCarga()throws BusinessException;
	public IndicesIntraDia getIndiceIntradiaAnterior(int idCargaActual,String indice)throws BusinessException;
}
