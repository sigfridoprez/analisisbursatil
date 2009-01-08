package mx.com.infraparseohtml.servicio.impl;

import org.apache.log4j.Logger;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;


import mx.com.analisispreciosmercado.conf.IpConfTablasHtmlParsear;
import mx.com.infracomunes.vo.ObjetoTablaVO;
import mx.com.infracomunes.vo.ObjetoTransportadorVO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;
import mx.com.infraparseohtml.conftablas.servicio.IpConfTablasHTMLParsearSrv;
import mx.com.infraparseohtml.extractor.ExtractorDatosTabla;
import mx.com.infraparseohtml.parseo.ExtractorNodosTableColumnHTML;
import mx.com.infraparseohtml.parseo.ParserHTML;
import mx.com.infraparseohtml.servicio.ServicioParseoTabla;
import mx.com.infraparseohtml.util.Constantes;

import java.util.List;

public class ServicioParseoTablaImpl implements ServicioParseoTabla {
	private Logger logger = Logger.getLogger(this.getClass());
	private IpConfTablasHTMLParsearSrv ipConfTablasHTMLParsearSrv;
	
	public ObjetoTransportadorVO getTabla(Integer idTabla,String strUrl)throws BusinessException, PaginaSinDatosException{
		logger.debug("En getTabla idTabla:::" + idTabla + "  strUrl::" + strUrl);
		ObjetoTransportadorVO returnOV = null;
		IpConfTablasHtmlParsear ipConfTablasHtmlParsear;
		
		logger.debug("Verificamos que los parametros vengan");
		if(idTabla == null){
			logger.debug("Error debe de indicar el ID_TABLA");
			throw new BusinessException("Error debe de indicar el ID_TABLA");
		}
		if(strUrl== null){
			logger.debug("La URL no puede ser nula");
			throw new BusinessException("La URL no puede ser nula");
		}
		if("".equals( strUrl )){
			logger.debug("Debe de indicar la URL");
			throw new BusinessException("Debe de indicar la URL");
		}
		logger.debug("Todos los datos vienen");
		logger.debug("Obtenemos la configuracion de la tabla");
		ipConfTablasHtmlParsear = ipConfTablasHTMLParsearSrv.getIpConfTablasHtmlParsear(idTabla);
		if(ipConfTablasHtmlParsear!=null){
			logger.debug("La configuracion se encontro el tipo de regreso es::" + ipConfTablasHtmlParsear.getFormatoTabla());
			if(ipConfTablasHtmlParsear.getFormatoTabla()==Constantes.TIPO_TABLA_GRID){
				logger.debug("El tipo de tabla es un GRI, se llama la funcion respectiva");
				returnOV = new ObjetoTransportadorVO();
				returnOV.setLstObjetos(getTablaGrid(strUrl, ipConfTablasHtmlParsear));
			}else if(ipConfTablasHtmlParsear.getFormatoTabla() == Constantes.TIPO_TABLA_DATOS){
				returnOV = new ObjetoTransportadorVO();
				returnOV.setObjetoTablaVO(getTablaObjeto(strUrl,ipConfTablasHtmlParsear));
			}
		}else{
			logger.debug("No existe la configuracion se regresa nulo");
		}
		
		logger.debug("Objeto regresado::" + returnOV);
		return returnOV;
	}
	
	private ObjetoTablaVO getTablaObjeto(String strUrl,IpConfTablasHtmlParsear ipConfTablasHtmlParsear)throws BusinessException, PaginaSinDatosException{
		logger.debug("En getTablaObjeto  strUrl::" + strUrl );
		ExtractorNodosTableColumnHTML extractorNodosTableColumnHTML = new ExtractorNodosTableColumnHTML();
		ExtractorDatosTabla extractorDatosTabla = new ExtractorDatosTabla();
		
		logger.debug("Nos conectamos a la pagina y nos traemos una lista de columnas");
		NodeList lst = extractorNodosTableColumnHTML.getListaNodosTableColumn(strUrl);
		
		logger.debug("Extraemos la informacion");
		return extractorDatosTabla.getDatosTablaVO(lst,ipConfTablasHtmlParsear);
	}

	private List<ObjetoTablaVO> getTablaGrid(String strUrl,IpConfTablasHtmlParsear ipConfTablasHtmlParsear)throws BusinessException, PaginaSinDatosException {
		logger.debug("En getTablaGrid strUrl::" + strUrl);
		ParserHTML parserHTML = new ParserHTML();
		ExtractorDatosTabla extractorDatosTabla = new ExtractorDatosTabla();
		TableTag table;
		
		logger.debug("Nos conectamos a la pagina::" + strUrl + " y extraemos la tabla que nos interesa");
		table = parserHTML.getTabla(strUrl, ipConfTablasHtmlParsear.getNombreTabla());
		
		logger.debug("Extraemos los valores");
		return extractorDatosTabla.getDatosTabla(table, ipConfTablasHtmlParsear);
	}

	public IpConfTablasHTMLParsearSrv getIpConfTablasHTMLParsearSrv() {
		return ipConfTablasHTMLParsearSrv;
	}

	public void setIpConfTablasHTMLParsearSrv(
			IpConfTablasHTMLParsearSrv ipConfTablasHTMLParsearSrv) {
		this.ipConfTablasHTMLParsearSrv = ipConfTablasHTMLParsearSrv;
	}
	
}
