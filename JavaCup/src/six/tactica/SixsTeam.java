package six.tactica;

import java.util.ArrayList;
import java.util.List;

import org.javahispano.javacup.modelo.Comando;
import org.javahispano.javacup.modelo.ComandoGolpearBalon;
import org.javahispano.javacup.modelo.ComandoIrA;
import org.javahispano.javacup.modelo.Posicion;
import org.javahispano.javacup.modelo.SituacionPartido;
import org.javahispano.javacup.modelo.Tactica;
import org.javahispano.javacup.modelo.TacticaDetalle;

import six.tactica.ayuda.FuncionesAyuda;


public class SixsTeam implements Tactica {
	private static int FACTOR_FUERZA = 10;
	
    Posicion alineacion1[]=new Posicion[]{
        new Posicion(0.2595419847328244,-50.41044776119403),
        new Posicion(-19.46564885496183,-31.6044776119403),
        new Posicion(0.2595419847328244,-31.082089552238806),
        new Posicion(19.984732824427482,-31.6044776119403),
        new Posicion(7.526717557251908,-11.753731343283583),
        new Posicion(-8.564885496183205,-11.753731343283583),
        new Posicion(-24.65648854961832,-2.3507462686567164),
        new Posicion(23.099236641221374,-2.873134328358209),
        new Posicion(-14.274809160305344,30.559701492537314),
        new Posicion(-0.7786259541984732,8.097014925373134),
        new Posicion(12.717557251908397,29.51492537313433)
    };

    Posicion alineacion2[]=new Posicion[]{
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

    Posicion alineacion3[]=new Posicion[]{
        new Posicion(0.2595419847328244,-50.41044776119403),
        new Posicion(-11.16030534351145,-31.082089552238806),
        new Posicion(11.16030534351145,-31.6044776119403),
        new Posicion(26.732824427480914,-20.111940298507463),
        new Posicion(-29.32824427480916,-21.67910447761194),
        new Posicion(0.2595419847328244,-0.26119402985074625),
        new Posicion(-18.946564885496183,-0.26119402985074625),
        new Posicion(18.946564885496183,-0.26119402985074625),
        new Posicion(-19.46564885496183,35.78358208955224),
        new Posicion(-0.2595419847328244,19.067164179104477),
        new Posicion(18.946564885496183,35.26119402985075)
    };

    Posicion alineacion4[]=new Posicion[]{
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

    Posicion alineacion5[]=new Posicion[]{
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

    Posicion alineacion6[]=new Posicion[]{
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
    
    SixsTeamTacticaDetalle detalle=new SixsTeamTacticaDetalle();
    public TacticaDetalle getDetalle() {
        return detalle;
    }

    public Posicion[] getPosicionSaca(SituacionPartido sp) {
    	return alineacion5;
    }
    
    public Posicion[] getPosicionRecive(SituacionPartido sp) {
    	return alineacion6;
    }

    public List<Comando> ejecutar(SituacionPartido sp) {
    	List<Comando> lstComandos = new ArrayList<Comando>();
    	int iJugador;
    	
    	//primero vamos al balon
    	iJugador = sp.balon().indiceMasCercano(sp.misJugadores());
    	lstComandos.add(new ComandoIrA(iJugador, sp.balon()));
    	
    	lstComandos.add(FuncionesAyuda.getComandoPase(sp,iJugador,false));
    	
        return lstComandos;
    }
}