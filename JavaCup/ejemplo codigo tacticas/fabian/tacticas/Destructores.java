/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fabian.tacticas;

import java.awt.Color;
import java.util.LinkedList;
import org.javahispano.javacup.modelo.*;
import java.util.List;
import java.util.Random;

public class Destructores implements Tactica {

    Posicion alineacion1[] = new Posicion[]{
        new Posicion(0.2595419847328244, -50.41044776119403),
        new Posicion(-11.16030534351145, -31.082089552238806),
        new Posicion(11.16030534351145, -31.6044776119403),
        new Posicion(28.290076335877863, -28.470149253731343),
        new Posicion(-28.290076335877863, -28.470149253731343),
        new Posicion(11.16030534351145, -1.3059701492537314),
        new Posicion(-10.641221374045802, -0.7835820895522387),
        new Posicion(-27.251908396946565, 31.6044776119403),
        new Posicion(-10.641221374045802, 30.559701492537314),
        new Posicion(9.603053435114505, 28.992537313432837),
        new Posicion(25.69465648854962, 28.992537313432837)
    };
    Posicion alineacion2[] = new Posicion[]{
        new Posicion(0.2595419847328244, -50.41044776119403),
        new Posicion(-11.16030534351145, -35.78358208955224),
        new Posicion(12.717557251908397, -35.26119402985075),
        new Posicion(28.290076335877863, -28.470149253731343),
        new Posicion(-28.290076335877863, -28.470149253731343),
        new Posicion(14.793893129770993, -18.544776119402986),
        new Posicion(-17.389312977099234, -19.58955223880597),
        new Posicion(-23.618320610687025, -0.7835820895522387),
        new Posicion(5.969465648854961, -5.485074626865671),
        new Posicion(0.2595419847328244, -0.26119402985074625),
        new Posicion(22.580152671755727, -1.3059701492537314)
    };
    Posicion alineacion3[] = new Posicion[]{
        new Posicion(0.2595419847328244, -50.41044776119403),
        new Posicion(-11.16030534351145, -35.78358208955224),
        new Posicion(12.717557251908397, -35.26119402985075),
        new Posicion(28.290076335877863, -28.470149253731343),
        new Posicion(-28.290076335877863, -28.470149253731343),
        new Posicion(14.793893129770993, -18.544776119402986),
        new Posicion(-17.389312977099234, -19.58955223880597),
        new Posicion(-23.618320610687025, -0.7835820895522387),
        new Posicion(6.4885496183206115, -6.529850746268657),
        new Posicion(-6.4885496183206115, -6.529850746268657),
        new Posicion(22.580152671755727, -1.3059701492537314)
    };

    class TacticaDetalleImpl implements TacticaDetalle {

        public String getNombre() {
            return "Destructores";
        }

        public String getPais() {
            return "Chile";
        }

        public String getEntrenador() {
            return "Fabian";
        }

        public Color getColorCamiseta() {
            return new Color(0, 153, 0);
        }

        public Color getColorPantalon() {
            return new Color(255, 255, 255);
        }

        public Color getColorFranja() {
            return new Color(198, 254, 73);
        }

        public Color getColorCalcetas() {
            return new Color(0, 0, 0);
        }

        public Color getColorPortero() {
            return new Color(186, 206, 52);
        }

        public EstiloUniforme getEstilo() {
            return EstiloUniforme.LINEAS_VERTICALES;
        }

        class JugadorImpl implements JugadorDetalle {

            String nombre;
            int numero;
            Color piel, pelo;
            double velocidad, remate, presicion;
            boolean portero;
            Posicion posicion;

            public JugadorImpl(String nombre, int numero, Color piel, Color pelo,
                    double velocidad, double remate, double presicion, boolean portero) {
                this.nombre = nombre;
                this.numero = numero;
                this.piel = piel;
                this.pelo = pelo;
                this.velocidad = velocidad;
                this.remate = remate;
                this.presicion = presicion;
                this.portero = portero;
            }

            public String getNombre() {
                return nombre;
            }

            public Color getColorPiel() {
                return piel;
            }

            public Color getColorPelo() {
                return pelo;
            }

            public int getNumero() {
                return numero;
            }

            public boolean esPortero() {
                return portero;
            }

            public double getVelocidad() {
                return velocidad;
            }

            public double getRemate() {
                return remate;
            }

            public double getPresicion() {
                return presicion;
            }
        }

        public JugadorDetalle[] getJugadores() {
            return new JugadorDetalle[]{
                        new JugadorImpl("Goran", 1, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 1.0d, 1.0d, true),
                        new JugadorImpl("Lexter", 2, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.58d, 1.0d, false),
                        new JugadorImpl("Ivan", 3, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.58d, 1.0d, false),
                        new JugadorImpl("Orson", 4, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.58d, 1.0d, false),
                        new JugadorImpl("Pedro", 5, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.58d, 1.0d, false),
                        new JugadorImpl("Joro", 6, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.66d, 1.0d, false),
                        new JugadorImpl("Marco", 7, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.66d, 1.0d, false),
                        new JugadorImpl("Lenni", 8, new Color(255, 200, 150), new Color(50, 0, 0), 0.71d, 1.0d, 0.48d, false),
                        new JugadorImpl("Romeo", 9, new Color(255, 200, 150), new Color(50, 0, 0), 0.71d, 1.0d, 0.53d, false),
                        new JugadorImpl("Felix", 10, new Color(255, 200, 150), new Color(50, 0, 0), 0.71d, 1.0d, 0.53d, false),
                        new JugadorImpl("Dago", 11, new Color(255, 200, 150), new Color(50, 0, 0), 0.71d, 1.0d, 0.48d, false)
                    };
        }
    }
    TacticaDetalle detalle = new TacticaDetalleImpl();

    public TacticaDetalle getDetalle() {
        return detalle;
    }

    public Posicion[] getPosicionSaca(SituacionPartido sp) {
        return alineacion2;
    }

    public Posicion[] getPosicionRecive(SituacionPartido sp) {
        return alineacion3;
    }

    LinkedList<Comando> comandos = new LinkedList<Comando>();
    Random r=new Random();
    public List<Comando> ejecutar(SituacionPartido sp) {
        comandos.clear();
        for (int i = 0; i < 11; i++) {
            comandos.add(new ComandoIrA(i, alineacion1[i]));
        }
        int cercanos[] = sp.balon().indicesMasCercanos(sp.misJugadores());
        for (int i = 0; i < 2; i++) {
            comandos.add(new ComandoIrA(cercanos[i], sp.balon()));
        }
        int rematan[] = sp.puedenRematar();
        for (int i : rematan) {
            if (sp.misJugadores()[i].getY() < 20) {
                int j=i;
                while(j==i){
                    j=r.nextInt(11);
                }
                boolean alto;
                double dist=sp.misJugadores()[i].distancia(sp.misJugadores()[j]);
                if (dist>30){
                    alto=true;
                } else {
                    alto=false;
                }
                comandos.add(new ComandoGolpearBalon(i, sp.misJugadores()[j], .6, alto));
            } else {
                comandos.add(new ComandoGolpearBalon(i, Constantes.centroArcoSup, 1, true));
            }
        }
        return comandos;
    }
}