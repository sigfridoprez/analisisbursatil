package mx.com.faces.reporteseriesoperadas.util;

import java.util.ArrayList;
import java.util.List;

import mx.com.infraestructura.exceptions.BusinessException;

public class DataSuggestionBoxBuilder {
	
	public List<DataSuggestionBox> getListData(List<String> lst)throws BusinessException{
		List<DataSuggestionBox> lstReturn = null;
		DataSuggestionBox vo;
		
		if(lst!=null){
			lstReturn = new ArrayList<DataSuggestionBox>();
			for(String str:lst){
				vo = new DataSuggestionBox();
				vo.setTexto(str==null?"":str);
				lstReturn.add(vo);
			}
		}
		return lstReturn;
	}
}
