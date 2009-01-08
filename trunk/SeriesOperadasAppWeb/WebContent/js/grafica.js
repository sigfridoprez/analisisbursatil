//Variables globales
var options;
var data;


function graficaJONSON(transport){
	var json;
	
	try{
		json = transport.evalJSON();
	}catch(e){
	 	alert('No se produjo nungún resultado');
		document.getElementById('container').textContent = '';
		options=null;
		data = null;
		return;
	}
		
	options = json.options;
	data = json.series;
	
	//alert(data);
	//alert(options);
	if(json.series && json.options){
		$('container').setStyle({'display':'block'});
		f = drawGraph(json.options); 
	}

	function drawGraph(opts){
			var o = Object.extend(Object.clone(options), opts || {});
			return Flotr.draw($('container'),data,o);
	}	
	
	$('container').observe('flotr:select', function(evt){
		var area = evt.memo[0];
		f = drawGraph({
			xaxis: {min:area.x1, max:area.x2},
			yaxis: {min:area.y1, max:area.y2}						
		});
	});
	
	$('reset-btn').observe('click', function(){drawGraph()});
}

function getTrackFormat(obj){
	var fechaInicio  = document.getElementById('Graficas:idBFechaInicio').value;
	
	var mes = new Array('01','02','03','04','05','06','07','08','09','10','11','12');
	var fecha = getFecha(fechaInicio);
	
	var fechaMostrar = sumaDias(fecha,obj.x);
	
	var strDia;
	if(fechaMostrar.getDate()<10){
		strDia = '0'+fechaMostrar.getDate();
	}else{
		strDia = ''+fechaMostrar.getDate();
	}
	
	var strFecha = ''+strDia+'/'+mes[fechaMostrar.getMonth()]+'/'+fechaMostrar.getFullYear();
	return obj.label + ' : ( ' + strFecha + ' , ' + obj.y + ' )'; 
}

function getFecha(strFecha){
	var dia=parseInt(strFecha[0]+strFecha[1]);
	var mes=parseInt(strFecha[3]+strFecha[4]);
	var anio=parseInt(strFecha[6]+strFecha[7]+strFecha[8]+strFecha[9]);
	
	var fechaR = new Date();
	fechaR.setFullYear(anio);
	fechaR.setMonth(mes-1);
	fechaR.setDate(dia);
	
	return fechaR;
}

function sumaDias(fecha, diasSumar){
	var milisecSumar= parseInt(diasSumar *24*60*60*1000);
	 
	try{
		fecha.setTime(fecha.getTime()+milisecSumar);
	}catch(e){
		alert(e);
	}
	return fecha; 
}