package mx.com.carga;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.analisispreciosmercado.conf.SeriesOperadasId;
import mx.com.business.service.seriesoperadas.SeriesOperadasSrv;
import mx.com.factory.ApplicationFactory;
import mx.com.infraestructura.exceptions.BusinessException;

public class CargaArchivo {
	private File file;
	private ApplicationFactory factory = ApplicationFactory.getInstance();
	private SeriesOperadasSrv seriesOperadasSrv;
	
	public CargaArchivo(){
		seriesOperadasSrv = (SeriesOperadasSrv) factory.getBean("seriesOperadasSrv");
	}
	
	public void  cargaArchivo(String archivo){
		file = new File(archivo);
		InputStream input;
		byte[] byteArray;
		int i=0;
		boolean blnRenglon=false;
		StringBuffer sbRenglon = new StringBuffer("");
		List<String> lstRenglones = null;
		String[] arrayColumnas;
		SeriesOperadas serie;
		List<SeriesOperadas> lstSeries;
		
		if(file!=null){
			try {
				byteArray = new byte[(int)file.length()];
				System.out.println("Size:::" + file.length());
				input = new FileInputStream(file);
				lstRenglones = new ArrayList<String>();
				
				while(i<file.length()){
					byteArray[i]=(byte) input.read();
					//System.out.println("Byte::" + byteArray[i] + "Char::" + (char)byteArray[i]);
					if(byteArray[i]==10){
						blnRenglon = true;
					}
					if(!blnRenglon){
						if(byteArray[i]!=13){
							sbRenglon.append((char)byteArray[i]);
						}
					}else{
						//System.out.println(sbRenglon.toString());
						lstRenglones.add(sbRenglon.toString());
						sbRenglon = new StringBuffer("");
						blnRenglon = false;
					}
					i++;
				}
				System.out.println("Size::" + lstRenglones.size());
				int j=0;
				lstSeries = new ArrayList<SeriesOperadas>();
				for(String strRegistro:lstRenglones){
					arrayColumnas = getColumnas(strRegistro);
					if(j!=0){
						System.out.println("Creamos los objetos" + arrayColumnas);
						serie = creaVOGuardad(arrayColumnas);
						lstSeries.add(serie);
					}
					j++;
				}
				try {
					seriesOperadasSrv.txInsertLstSeriesOperadas(lstSeries);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private SeriesOperadas creaVOGuardad(String[] arrayColumnas) throws BusinessException{
		SeriesOperadas serie;
		SeriesOperadasId id;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		SeriesOperadas voTmp;
		
		serie = new SeriesOperadas();
		id = new SeriesOperadasId();
		
		try {
			id.setFecha(sdf.parse(arrayColumnas[0]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		id.setEmisora(arrayColumnas[1]);
		id.setSerie(arrayColumnas[2]);
		
		serie.setId(id);
		try {
			serie.setHora(sdfTime.parse(revisaFormatoTime(arrayColumnas[3])));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		serie.setUltimo(getValorDecimal(arrayColumnas[4]));
		serie.setPpp(getValorDecimal(arrayColumnas[5]));
		
		if(serie.getPpp().intValue()==0){
			serie.setPpp(serie.getUltimo());
		}
		serie.setAnterior(getValorDecimal(arrayColumnas[6]));
		serie.setMaximo(getValorDecimal(arrayColumnas[7]));
		serie.setMinimo(getValorDecimal(arrayColumnas[8]));
		serie.setVolumen(getValorDecimal(arrayColumnas[9]).longValue());
		serie.setImporte(getValorDecimal(arrayColumnas[10]));
		serie.setOperaciones(getValorDecimal(arrayColumnas[11]).intValue());
		serie.setPuntos(getValorDecimal(arrayColumnas[12]));
		serie.setPorcentaje(getValorDecimal(arrayColumnas[13]));
		
		cal.setTime(id.getFecha());
		serie.setDiaHabil('S');
		
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ||
		   cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			serie.setDiaHabil('N');	
		}
		/*Buscamos si existe la serie en una carga previa,
		en caso de existir cargamos el split anterior, si no existe se busca de la configuracion
		y si no existe de la configuracion se le coloca 1*/
		serie.setSplit(new Integer(1));
		voTmp = seriesOperadasSrv.getSerieOperadaAnterior(serie.getId().getEmisora(), serie.getId().getSerie(),serie.getId().getFecha());
		if(voTmp!=null){//Existe serie, se aplica el Split anterior
			serie.setSplit(voTmp.getSplit());
		}
		serie.setVarPctMaximo(new BigDecimal("0"));
		serie.setVarPctMinimo(new BigDecimal("0"));
		serie.setVarPct(new BigDecimal("0"));
		serie.setVarPctPrecio(new BigDecimal("0"));
		serie.setRendimiento(new BigDecimal("1"));
		if(voTmp!=null){//Calculamos la variacion y rendimiento
			//Calculamos la variacion del precio 
			if(voTmp.getPpp().doubleValue()!=0){
				serie.setVarPctPrecio(serie.getPpp().divide(voTmp.getPpp(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1")));	
			}
			//Calculamos la variacion de Maximo
			if(voTmp.getMaximo().doubleValue()!=0){
				serie.setVarPctMaximo(serie.getMaximo().divide(voTmp.getMaximo(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1")));	
			}
			//Calculamos la variacion de Minimo
			if(voTmp.getMinimo().doubleValue()!=0){
				serie.setVarPctMinimo(serie.getMinimo().divide(voTmp.getMinimo(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1")));	
			}
			//Calculamos el Porcentaje
			serie.setVarPct(serie.getVarPctMaximo().subtract(serie.getVarPctMinimo()));
			//calculamos el rendimiento
			if(voTmp.getRendimiento()!=null){
				serie.setRendimiento(voTmp.getRendimiento().multiply(serie.getVarPct()).add(voTmp.getRendimiento()));
			}
		}
		
		return serie;
	}
	
	private BigDecimal getValorDecimal(String strValor){
		BigDecimal ob = new BigDecimal("0");
		
		if(strValor!=null && !strValor.equals("")){
			try{
				ob = new BigDecimal(strValor);	
			}catch(Exception e){}
			
		}
		return ob;
	}
	
	private String[] getColumnas(String strRegistro){
        String[] strDatos = null;
        StringBuffer sbDato;
        byte[] arrayByte;
        byte car;
        byte bytComa=44;
        byte bytComilla=34;
        int intEstado=0;
        int intRegistros=0;
        
        if(strRegistro!=null && !strRegistro.equals("")){
            strDatos = new String[14];
            sbDato = new StringBuffer("");
            arrayByte = strRegistro.getBytes();
            for(int i=0;i<arrayByte.length;i++){
                car = arrayByte[i];
                switch(intEstado){
                    case 0:
                        if(car==bytComa){
                            intEstado=1;
                        }else if(car==bytComilla){
                            intEstado=2;
                        }else{
                            sbDato.append((char)car);
                        }
                        break;
                    case 1:
                        strDatos[intRegistros]=sbDato.toString();
                        sbDato = new StringBuffer("");
                        intRegistros++;
                        if(i+1<arrayByte.length){
                            if(car==bytComilla){
                                intEstado=2;
                            }else if(arrayByte[i]==bytComa){
                                strDatos[intRegistros]="";
                                sbDato = new StringBuffer("");
                                intRegistros++;
                                intEstado=0;
                            }else{
                                sbDato.append((char)car);
                                intEstado=0;
                            }
                        }
                        break;
                    case 2:
                        if(car==bytComilla){
                            strDatos[intRegistros]=sbDato.toString();
                            sbDato = new StringBuffer();
                            intRegistros++;
                            i++;
                            intEstado=0;
                        }else if(car==bytComa){
                            
                        }else{
                            sbDato.append((char)car);
                        }
                        break;
                    case 3:
                        break;
                }
            }
            if(sbDato.length()>0){
                strDatos[intRegistros]=sbDato.toString();
            }
        }
        
        return strDatos;
    }
	
	@SuppressWarnings("unused")
	private String revisaFormatoTime(String strValor) {
		StringTokenizer stzFormato = new StringTokenizer(strValor, ":");

		if (stzFormato.countTokens() == 1) {
			return new StringBuffer(strValor + ":00:00").toString();
		} else if (stzFormato.countTokens() == 2) {
			return new StringBuffer(strValor + ":00").toString();
		} else if (stzFormato.countTokens() == 3) {
			return strValor;
		}

		return "00:00:00";
	}
}
