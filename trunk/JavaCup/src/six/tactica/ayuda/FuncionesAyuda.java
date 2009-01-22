package six.tactica.ayuda;

import java.util.HashMap;

import org.javahispano.javacup.modelo.Posicion;
import org.javahispano.javacup.modelo.SituacionPartido;


public final class FuncionesAyuda {
	private static final int MAX_ROUND = 30;
	private static final int MIN_ROUND = 10;
	
	public static int ontieneJugadorCercano(SituacionPartido sp,int iJugador){
		int[] arrayJugadores = sp.misJugadores()[iJugador].indicesMasCercanos(sp.misJugadores());
		int iJugadorDestino = 0;
		HashMap<Double, Integer> hsmJugadores = new HashMap<Double, Integer>();
		double dblDistancia;
		
		System.out.println("iJugador::"+iJugador);
		
		//De estos jugadores busamos al que no tenga marca
		for(Posicion posRival:sp.rivales()){
			for(int iMiJugador:arrayJugadores){
				dblDistancia = posRival.distancia(sp.misJugadores()[iMiJugador]);
				System.out.println("posRival::"+posRival + "  iMiJugador::"+iMiJugador);
				System.out.println("dblDistancia::"+dblDistancia);
				if(posRival.distancia(sp.misJugadores()[iMiJugador])>MIN_ROUND){
					hsmJugadores.put(dblDistancia, iMiJugador);
				}
			}
		}
		//Buscamos al que este a la redonda
		for(Double dblDist:hsmJugadores.keySet()){
			System.out.println("Distancia::"+dblDist+" JUGADOR::::"+hsmJugadores.get(dblDist));
		}
		
		
		return iJugadorDestino;
	}
}
