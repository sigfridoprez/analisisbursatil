package test;

import mx.com.analisispreciosmercado.conf.CiConfLinks;
import mx.com.infracomunes.vo.ObjetoTransportadorVO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;
import mx.com.infraparseohtml.servicio.ServicioParseoTabla;

public class TestSrvImpl implements TestSrv {
private mx.com.cargainformacionipc.persistencia.service.CiConfLinksSrv ciConfLinksSrv;
private ServicioParseoTabla servicioParseoTabla;

	public void testCarga()throws BusinessException, PaginaSinDatosException{
		
		CiConfLinks lstLinkBMV = ciConfLinksSrv.getCiConfLinks(Integer.valueOf(3));
		
		ObjetoTransportadorVO tablaVO = servicioParseoTabla.getTabla(lstLinkBMV.getIpConfTablasHtmlParsear().getIdTabla(), lstLinkBMV.getLink());
		System.out.println(tablaVO);
	}
	
	public mx.com.cargainformacionipc.persistencia.service.CiConfLinksSrv getCiConfLinksSrv() {
		return ciConfLinksSrv;
	}
	public void setCiConfLinksSrv(
			mx.com.cargainformacionipc.persistencia.service.CiConfLinksSrv ciConfLinksSrv) {
		this.ciConfLinksSrv = ciConfLinksSrv;
	}
	public ServicioParseoTabla getServicioParseoTabla() {
		return servicioParseoTabla;
	}
	public void setServicioParseoTabla(ServicioParseoTabla servicioParseoTabla) {
		this.servicioParseoTabla = servicioParseoTabla;
	}

}
