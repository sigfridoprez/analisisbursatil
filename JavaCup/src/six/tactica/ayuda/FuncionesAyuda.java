package six.tactica.ayuda;


import java.util.ArrayList;
import java.util.List;

import org.javahispano.javacup.modelo.ComandoGolpearBalon;
import org.javahispano.javacup.modelo.Posicion;
import org.javahispano.javacup.modelo.SituacionPartido;


public final class FuncionesAyuda {
	private static final int MAX_DISTANCIA = 50;
	private static final int MAX_ROUND = 10;
	private static final int MIN_ROUND = 5;
	private static final double FACTORES[]={0.1,0.3,0.4,0.6,0.7,0.8,1.0};
	
	public static Posicion getPosicionJugadorCercano(SituacionPartido sp,int iJugador){
		return sp.misJugadores()[iJugador];
	}
	
	public static int ontieneJugadorCercano(SituacionPartido sp,int iJugador){
		int[] arrayJugadoresCercanos = sp.misJugadores()[iJugador].indicesMasCercanos(sp.misJugadores());
		int iJugadorDestino = 0;
		int iDistancia = 0;
		int iJugadorRivalCercano;
		List<Integer> lstJugadores = new ArrayList<Integer>();
		
		for(int iMiJugadorCercano:arrayJugadoresCercanos){
			if(iMiJugadorCercano!=iJugador){
				iJugadorRivalCercano = sp.misJugadores()[iMiJugadorCercano].indiceMasCercano(sp.rivales());
				iDistancia = (int)sp.misJugadores()[iMiJugadorCercano].distancia(sp.rivales()[iJugadorRivalCercano]);

				//System.out.println("La distancia es de ::"+iDistancia +" entre iMiJugadorCercano::"+iMiJugadorCercano+" y iJugadorRivalCercano::"+iJugadorRivalCercano);
				
				if(iDistancia>MIN_ROUND){//Encontramos
					lstJugadores.add(iMiJugadorCercano);
				}
			}
		}
		
		Integer iJugadorMax = new Integer(0);
		//Buscamos el maximo jugador cercano
		for(int iJigadorM:lstJugadores){
			//System.out.println("iJigadorM::"+iJigadorM);
			if(iJugadorMax<iJigadorM){
				iJugadorMax=iJigadorM;
			}
		}
		
		iJugadorDestino=iJugadorMax;
		//System.out.println("iJugador::"+iJugador+"  iJugadorDestino:::"+iJugadorDestino);
		return iJugadorDestino;
	}
	
	public static double getFuerzaPase(SituacionPartido sp,int iJugadorOrigen,int iJugadorDestino){
		int iDistancia = (int)sp.misJugadores()[iJugadorOrigen].distancia(sp.misJugadores()[iJugadorDestino]);
		double dblReturn = 0;
		
		System.out.println("iDistancia::"+iDistancia);
		if(iDistancia>MAX_DISTANCIA){
			dblReturn=FACTORES[6];
		}else if(iDistancia<2){
			dblReturn=FACTORES[2];
		}else if(iDistancia>2 && iDistancia<15){
			dblReturn=FACTORES[3];
		}else if(iDistancia>15 && iDistancia<MAX_DISTANCIA){
			dblReturn=FACTORES[4];
		}
		
		return dblReturn;
	}
	
	public static ComandoGolpearBalon getComandoPase(SituacionPartido sp,int iJugador,boolean blnDespeje){
		int iJugadorDestino = FuncionesAyuda.ontieneJugadorCercano(sp,iJugador);
		double dblFuerzaPase = getFuerzaPase(sp,iJugador,iJugadorDestino);
		
		System.out.println("dblFuerzaPase::"+dblFuerzaPase);
		return new ComandoGolpearBalon(iJugador,getPosicionJugadorCercano(sp, iJugadorDestino),
				dblFuerzaPase,dblFuerzaPase>FACTORES[4]?true:false);
	}
	
	public static ComandoGolpearBalon getComandoPase(SituacionPartido sp,int iJugador,int iJugadorDestino,boolean blnDespeje){
		double dblFuerzaPase = getFuerzaPase(sp,iJugador,iJugadorDestino);
		
		System.out.println("dblFuerzaPase::"+dblFuerzaPase);
		return new ComandoGolpearBalon(iJugador,getPosicionJugadorCercano(sp, iJugadorDestino),
				dblFuerzaPase,dblFuerzaPase>FACTORES[4]?true:false);
	}
}
