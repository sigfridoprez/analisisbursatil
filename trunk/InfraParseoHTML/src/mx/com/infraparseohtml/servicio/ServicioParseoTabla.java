package mx.com.infraparseohtml.servicio;


import mx.com.infracomunes.vo.ObjetoTransportadorVO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;

public interface ServicioParseoTabla {
	
	public ObjetoTransportadorVO getTabla(Integer idTabla,String strUrl)throws BusinessException, PaginaSinDatosException;
	
}
