package mx.com.infracomunes.vo;

import java.util.List;

public class ObjetoTransportadorVO {
	private ObjetoTablaVO objetoTablaVO;
	private List<ObjetoTablaVO> lstObjetos;
	
	public ObjetoTablaVO getObjetoTablaVO() {
		return objetoTablaVO;
	}
	public void setObjetoTablaVO(ObjetoTablaVO objetoTablaVO) {
		this.objetoTablaVO = objetoTablaVO;
	}
	public List<ObjetoTablaVO> getLstObjetos() {
		return lstObjetos;
	}
	public void setLstObjetos(List<ObjetoTablaVO> lstObjetos) {
		this.lstObjetos = lstObjetos;
	}
	
}
