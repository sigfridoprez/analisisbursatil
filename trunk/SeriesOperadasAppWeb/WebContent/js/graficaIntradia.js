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
	var num = new Number(obj.x);

	return obj.label + ' : ( ' + num.toFixed(0) + ' , ' + obj.y + ' )'; 
}