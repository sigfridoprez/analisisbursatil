package mx.com.faces.seriesoperadas.util;

import java.util.ArrayList;
import java.util.List;

public class SerieGraficarVO {
	private String strNombreSerie;
	private List<ValorVO> lstValores;
	
	public SerieGraficarVO(){
		lstValores = new ArrayList<ValorVO>();
		strNombreSerie = "";
	}
	
	public void setValores(double dblRendimiento,int    intX){
		lstValores.add(new ValorVO(dblRendimiento,intX));
	}
	
	public String getStrNombreSerie() {
		return strNombreSerie;
	}
	public void setStrNombreSerie(String strNombreSerie) {
		this.strNombreSerie = strNombreSerie;
	}
	public List<ValorVO> getLstValores() {
		return lstValores;
	}
}
