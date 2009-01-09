package mx.com.util;

import java.util.List;

import mx.com.faces.seriesoperadas.util.SerieGraficarVO;
import mx.com.faces.seriesoperadas.util.ValorVO;

public class UtilsGrafica {

	
	public String getSeries(List<SerieGraficarVO> lstSeries){
		StringBuilder sbSeries = new StringBuilder("series:[");
		int intContador = 0;
		
		for(SerieGraficarVO vo:lstSeries){
			sbSeries.append(getSerie(vo));
			if(intContador < lstSeries.size()-1){
				sbSeries.append(",");
			}
			intContador++;
		}
		sbSeries.append("]");
		return sbSeries.toString();
	}
	
	public String getOpciones(int intNoCols){
		StringBuilder sbOpciones = new StringBuilder("options:{");

		
		sbOpciones.append(" mouse:{");
		sbOpciones.append("   track: true,");
		sbOpciones.append("   lineColor: 'purple',");
		sbOpciones.append("   sensibility: 1,");
		sbOpciones.append("   trackDecimals: 3,");
		sbOpciones.append("   position: 'sw',");	
		//sbOpciones.append("   trackFormatter: function(obj){ return obj.label + ' : (' + obj.x + ' , ' + obj.y + ')' ; } ");
		sbOpciones.append("   trackFormatter: function(obj){ return getTrackFormat(obj); } ");
		sbOpciones.append(" },");
		
		sbOpciones.append(" selection:{mode:'x'},");
		
		sbOpciones.append(" xaxis: { ");
		//sbOpciones.append(" 	ticks: null, ");
		//sbOpciones.append(" 	noTicks: 5,	 ");
		//sbOpciones.append(" 	tickFormatter: defaultTickFormatter, ");
		sbOpciones.append(" 	tickDecimals: 0	");
		//sbOpciones.append(" 	min: null,	");
		//sbOpciones.append(" 	max: null,	");
		//sbOpciones.append(" 	autoscaleMargin: 0	");
		sbOpciones.append(" },");
		
		sbOpciones.append(" legend: { ");
		sbOpciones.append(" 	show: true,	");
		sbOpciones.append(" 	noColumns: "+intNoCols+", ");
		//sbOpciones.append(" 	labelFormatter: null,	");
		//sbOpciones.append(" 	labelBoxBorderColor: Ô#cccÕ, ");
		//sbOpciones.append(" 	container: null,	");
		sbOpciones.append(" 	position: 'sw',	");
		sbOpciones.append(" 	margin: 20	");
		//sbOpciones.append(" 	backgroundColor: null,	");
		//sbOpciones.append(" 	backgroundOpacity: 0.85	");
		sbOpciones.append(" } ");
		
		sbOpciones.append("}");
		
		return sbOpciones.toString();
	}
	
	public String getSerie(SerieGraficarVO vo){
		StringBuilder sbSerie = new StringBuilder("{");
		int intContador = 0;
		
		sbSerie.append("data:[");
		for(ValorVO val:vo.getLstValores()){
			sbSerie.append("["+val.getIntX()+","+val.getDblRendimiento()+"]");
			if(intContador<vo.getLstValores().size()-1){
				sbSerie.append(",");
			}
			intContador++;
		}
		sbSerie.append("],");
		sbSerie.append("label: '"+vo.getStrNombreSerie()+"' ");		
		sbSerie.append("}");
		return sbSerie.toString();
	}
}
