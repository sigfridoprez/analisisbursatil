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

import six.tactica.ayuda.Alineaciones;
import six.tactica.ayuda.FuncionesAyuda;


public class SixsTeam implements Tactica {
	private static int FACTOR_FUERZA = 10;
	int iJugador = -1;
	int iJugadorDestino = -1;
	
    SixsTeamTacticaDetalle detalle=new SixsTeamTacticaDetalle();
    public TacticaDetalle getDetalle() {
        return detalle;
    }

    public Posicion[] getPosicionSaca(SituacionPartido sp) {
    	return Alineaciones.alineacion5;
    }
    
    public Posicion[] getPosicionRecive(SituacionPartido sp) {
    	return Alineaciones.alineacion6;
    }

    public List<Comando> ejecutar(SituacionPartido sp) {
    	List<Comando> lstComandos = new ArrayList<Comando>();
    	
    	//primero vamos al balon
    	if(iJugadorDestino!=-1){
    		iJugador = iJugadorDestino;
    	}else{
    		iJugador = sp.balon().indiceMasCercano(sp.misJugadores());    		
    	}
    	if(iJugador!=0){
    		lstComandos.add(new ComandoIrA(iJugador, sp.balon()));
        	lstComandos.add(FuncionesAyuda.getComandoPase(sp,iJugador,false));	
    	}else{
    		//despejamos
    		lstComandos.add(new ComandoIrA(iJugador, sp.balon()));
    		lstComandos.add(new ComandoGolpearBalon(0,sp.misJugadores()[10],1,true));
    		lstComandos.add(new ComandoIrA(0, Alineaciones.alineacion1[0]));
    	}
    	
    	
        return lstComandos;
    }
}