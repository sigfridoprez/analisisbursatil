package six.tactica;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.javahispano.javacup.modelo.*;
import org.javahispano.javacup.modelo.ComandoGolpearBalon;
import org.javahispano.javacup.modelo.ComandoIrA;

public class TacticaBarcelona implements Tactica {

	TacticaBarcelonaDetalle detalle = new TacticaBarcelonaDetalle();
	    Posicion alineacion1[]=new Posicion[]{
        new Posicion(0.2595419847328244,-50.41044776119403),
        new Posicion(-11.16030534351145,-31.082089552238806),
        new Posicion(11.16030534351145,-31.6044776119403),
        new Posicion(27.251908396946565,-27.94776119402985),
        new Posicion(-29.84732824427481,-26.902985074626866),
        new Posicion(8.564885496183205,-7.574626865671642),
        new Posicion(-10.641221374045802,-7.052238805970149),
        new Posicion(27.251908396946565,4.440298507462686),
        new Posicion(-29.32824427480916,3.3955223880597014),
        new Posicion(-0.2595419847328244,19.067164179104477),
        new Posicion(-0.2595419847328244,35.78358208955224)
    };

    Posicion alineacion2[]=new Posicion[]{
        new Posicion(0.2595419847328244,-50.41044776119403),
        new Posicion(-11.16030534351145,-31.082089552238806),
        new Posicion(11.16030534351145,-31.6044776119403),
        new Posicion(28.290076335877863,-28.470149253731343),
        new Posicion(-28.290076335877863,-28.470149253731343),
        new Posicion(11.16030534351145,-1.3059701492537314),
        new Posicion(-10.641221374045802,-0.7835820895522387),
        new Posicion(-27.251908396946565,31.6044776119403),
        new Posicion(-10.641221374045802,30.559701492537314),
        new Posicion(9.603053435114505,28.992537313432837),
        new Posicion(25.69465648854962,28.992537313432837)
    };

    Posicion alineacion3[]=new Posicion[]{
        new Posicion(0.2595419847328244,-50.41044776119403),
        new Posicion(-11.16030534351145,-35.78358208955224),
        new Posicion(12.717557251908397,-35.26119402985075),
        new Posicion(28.290076335877863,-28.470149253731343),
        new Posicion(-28.290076335877863,-28.470149253731343),
        new Posicion(14.793893129770993,-18.544776119402986),
        new Posicion(-17.389312977099234,-19.58955223880597),
        new Posicion(-23.618320610687025,-0.7835820895522387),
        new Posicion(5.969465648854961,-5.485074626865671),
        new Posicion(0.2595419847328244,-0.26119402985074625),
        new Posicion(22.580152671755727,-1.3059701492537314)
    };

    Posicion alineacion4[]=new Posicion[]{
        new Posicion(0.2595419847328244,-50.41044776119403),
        new Posicion(-11.16030534351145,-35.78358208955224),
        new Posicion(12.717557251908397,-35.26119402985075),
        new Posicion(28.290076335877863,-28.470149253731343),
        new Posicion(-28.290076335877863,-28.470149253731343),
        new Posicion(14.793893129770993,-18.544776119402986),
        new Posicion(-17.389312977099234,-19.58955223880597),
        new Posicion(-23.618320610687025,-0.7835820895522387),
        new Posicion(6.4885496183206115,-6.529850746268657),
        new Posicion(-6.4885496183206115,-6.529850746268657),
        new Posicion(22.580152671755727,-1.3059701492537314)
    };

	public TacticaDetalle getDetalle() {
		return detalle;
	}

	public Posicion[] getPosicionSaca(SituacionPartido sp) {
		return alineacion3;
	}

	public Posicion[] getPosicionRecive(SituacionPartido sp) {
		return alineacion4;
	}

	Random rand = new Random();
	LinkedList<Comando> comandos = new LinkedList<Comando>();

	public List<Comando> ejecutar(SituacionPartido sp) {
		//vacia la lista de comandos
		comandos.clear();
		//si voy perdiendo o empatando
		if (sp.golesPropios() <= sp.golesRival()) {
			//cada jugador ubicarse segun alineacion1
			for (int i = 0; i < 11; i++) {
				comandos.add(new ComandoIrA(i, alineacion1[i]));
			}
		} else {//sino, osea que voy ganando
			//cada jugador ubicarse segun alineacion2
			for (int i = 0; i < 11; i++) {
				comandos.add(new ComandoIrA(i, alineacion2[i]));
			}
		}
		//obtiene el indice del jugador mas cercano al balon
		int indMasCercano = sp.balon().indiceMasCercano(sp.misJugadores());
		//el jugador mas cercano sigue al balon
		comandos.add(new ComandoIrA(indMasCercano, sp.balon()));
		//obtiene un arreglo con los indices de los jugadores que pueden rematar
		int rematan[] = sp.puedenRematar();
		//recorre los jugadores que pueden rematar
		for (int i : rematan) {
			//obtiene los jugadores mas cercanos, al jugador que puede rematar
			int[] cercanos = sp.misJugadores()[i].indicesMasCercanos(sp.misJugadores());
			//encontro un pase en falso
			boolean pase = false;
			boolean alto;
			//recorre los jugadores desde el mas lejano al mas cercano
			for (int j = 1; !pase && j < cercanos.length; j++) {
				//si el jugador es distinto al jugador que remata y el jugador esta mas adelantado
				if (sp.misJugadores()[i].getY() < sp.misJugadores()[cercanos[j]].getY()) {
					//dar pase al jugador mas cercano que este en posicion mas ofensiva
					if (sp.misJugadores()[i].distancia(sp.misJugadores()[cercanos[j]]) > 20) {
						alto = true;
					} else {
						alto = false;
					}
					comandos.add(new ComandoGolpearBalon(i, sp.misJugadores()[cercanos[j]], .5 + rand.nextDouble() * .5, alto));
					//encontro un pase por lo menos
					pase = true;
				}
			}
			//si no encontro ningun pase
			if (!pase) {
				//da pase hacia el jugador mas cercano
				if (sp.misJugadores()[i].distancia(sp.misJugadores()[cercanos[1]]) > 20) {
					alto = true;
				} else {
					alto = false;
				}
				comandos.add(new ComandoGolpearBalon(i, sp.misJugadores()[cercanos[1]], .5 + rand.nextDouble() * .5, alto));
			}
			//si el jugador que remata esta a menos de 30 metros del arco rival -> remata al arco
			if (sp.misJugadores()[i].distancia(Constantes.centroArcoSup) < 30) {
				comandos.add(new ComandoGolpearBalon(i, Constantes.centroArcoSup, 1, true));
			}
		}
		//retorna la lista de comandos.
		return comandos;
	}
	
}
