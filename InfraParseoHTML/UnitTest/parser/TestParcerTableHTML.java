package parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;


import mx.com.analisispreciosmercado.conf.IpConfPropiedadesTabla;
import mx.com.analisispreciosmercado.conf.IpConfPropiedadesTablaId;
import mx.com.analisispreciosmercado.conf.IpConfTablasHtmlParsear;
import mx.com.infracomunes.impl.vo.ResumenEmisoraVO;
import mx.com.infracomunes.impl.vo.SeriesOperadasMercadoCapitalesVO;
import mx.com.infracomunes.vo.ObjetoTablaVO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;
import mx.com.infraparseohtml.extractor.ExtractorDatosTabla;
import mx.com.infraparseohtml.parseo.ExtractorNodosTableColumnHTML;
import mx.com.infraparseohtml.parseo.ParserHTML;
import junit.framework.TestCase;
import org.apache.log4j.BasicConfigurator;

public class TestParcerTableHTML extends TestCase {
Ajuste en master temporal	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		BasicConfigurator.configure();
	}

	public void testParcer() throws BusinessException{
		ParserHTML parser = new ParserHTML();
		ExtractorDatosTabla extractorDatosTabla = new ExtractorDatosTabla();
		IpConfTablasHtmlParsear vo = getIpConfTablasHtmlParsear();
		
		SeriesOperadasMercadoCapitalesVO voDat;
		
		TableTag tableTag = parser.getTabla("http://www.bmv.com.mx/wb3/wb/BMV/BMV_resumen_del_mercado",vo.getNombreTabla());
		List<ObjetoTablaVO> lstReturn;
		try {
			lstReturn = extractorDatosTabla.getDatosTabla(tableTag,vo);
			for(ObjetoTablaVO voInst :lstReturn){
				voDat = (SeriesOperadasMercadoCapitalesVO)voInst;
				System.out.println(voDat.getStrEmisora());	
				System.out.println(voDat.getTimeHora());
			}
		} catch (PaginaSinDatosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testParcerVO() throws BusinessException, PaginaSinDatosException{
		ExtractorNodosTableColumnHTML extractorNodosTextoHTML = new ExtractorNodosTableColumnHTML();
		ExtractorDatosTabla extractorDatosTabla = new ExtractorDatosTabla();
		IpConfTablasHtmlParsear vo = getIpConfTablasHtmlParsear();
		
		NodeList lst = extractorNodosTextoHTML.getListaNodosTableColumn("http://mx.finance.yahoo.com/q?s=ALFAA.MX");
		ResumenEmisoraVO voR = (ResumenEmisoraVO)extractorDatosTabla.getDatosTablaVO(lst,vo);
		System.out.println(voR.getDblCierrePrevio());
	}
	
	private IpConfTablasHtmlParsear getIpConfTablasHtmlParsear(){
		IpConfTablasHtmlParsear vo = new IpConfTablasHtmlParsear();
		
		
		vo.setFormatoTabla('G');
		vo.setIdTabla(Integer.valueOf(3));
		vo.setNombreTabla("formaPrincipalesIndices:table1");
		//vo.setNombreTabla("yfncsumtab");
		vo.setObjetoRegresar("mx.com.infracomunes.impl.vo.SeriesOperadasMercadoCapitalesVO");
		
		vo.setIpConfPropiedadesTablas(getIpConfPropiedadesTablas(vo));
		
		return vo;
	}
	
	private Set<IpConfPropiedadesTabla> getIpConfPropiedadesTablas(IpConfTablasHtmlParsear vo){
		Set<IpConfPropiedadesTabla> ipConfPropiedadesTablas = new HashSet<IpConfPropiedadesTabla>(0);
		
		ipConfPropiedadesTablas.add(getIpConfPropiedadesTabla(vo.getIdTabla(),Integer.valueOf(0),"Ultimo","dblUltima",'D'));
		ipConfPropiedadesTablas.add(getIpConfPropiedadesTabla(vo.getIdTabla(),Integer.valueOf(0),"Hora","timeHora",'T'));
		
		return ipConfPropiedadesTablas;
	}
	
	@SuppressWarnings("unused")
	private IpConfPropiedadesTabla getIpConfPropiedadesTabla(Integer idTabla,
			Integer idPropiedadTabla,String strNombrePropiedad,
			String nombrePropiedadObjeto,char tipoDato){
		IpConfPropiedadesTabla vo = new IpConfPropiedadesTabla();
		IpConfPropiedadesTablaId id = new IpConfPropiedadesTablaId();
		
		id.setIdPropiedadTabla(idPropiedadTabla);
		id.setIdTabla(idTabla);
		
		vo.setId(id);
		
		vo.setNombrePropiedad(strNombrePropiedad);
		vo.setNombrePropiedadObjeto(nombrePropiedadObjeto);
		vo.setTipoDato(tipoDato);
		
		return vo;
	}
}
