package six.tactica.ayuda;


import java.util.ArrayList;
import java.util.List;

import org.javahispano.javacup.modelo.ComandoGolpearBalon;
import org.javahispano.javacup.modelo.Posicion;
import org.javahispano.javacup.modelo.SituacionPartido;


public final class FuncionesAyuda {
	private static final int MAX_ROUND = 30;
	@SuppressWarnings("unused")
	private static final int MIN_ROUND = 10;
	
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
				//System.out.println("iMiJugadorCercano::" + iMiJugadorCercano + "  iJugadorRivalCercano::"+iJugadorRivalCercano+"  iDistancia::"+iDistancia);
				if(iDistancia>MAX_ROUND){//Encontramos
					lstJugadores.add(iMiJugadorCercano);
				}
			}
		}
		
		Integer iTmp=new Integer(0);
		Integer iJugadorMax = new Integer(0);
		//Buscamos el maximo jugador cercano
		for(int iJigadorM:lstJugadores){
			iJugadorMax = iJigadorM;
			
			if(iJugadorMax<iTmp){
				iJugadorMax=iTmp;
			}else{
				iTmp=iJugadorMax;
			}
		}
		
		iJugadorDestino=iJugadorMax;
		System.out.println("iJugador::"+iJugador+"  iJugadorDestino:::"+iJugadorDestino);
		return iJugadorDestino;
	}
	
	public static double getFuerzaPase(int iJugadorOrigen,int iJugadorDestino){
		int iDistanciaOrigen = 0;
		int iDistanciaDestino = 0;
		
		return 1;
	}
	
	public static ComandoGolpearBalon getComandoPase(SituacionPartido sp,int iJugador,boolean blnDespeje){
		int iJugadorDestino = FuncionesAyuda.ontieneJugadorCercano(sp,iJugador);
		
		return new ComandoGolpearBalon(iJugador,getPosicionJugadorCercano(sp, iJugadorDestino),
				getFuerzaPase(iJugador,iJugadorDestino),true);
	}
	
	public static ComandoGolpearBalon getComandoPase(SituacionPartido sp,int iJugador,int iJugadorDestino,boolean blnDespeje){
		return new ComandoGolpearBalon(iJugador,getPosicionJugadorCercano(sp, iJugadorDestino),
				getFuerzaPase(iJugador,iJugadorDestino),true);
	}
}
