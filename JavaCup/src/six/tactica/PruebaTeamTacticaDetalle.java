package six.tactica;

import java.awt.Color;
import org.javahispano.javacup.modelo.*;

public class PruebaTeamTacticaDetalle implements TacticaDetalle {

        public String getNombre() {
            return "Mexicoatl";
        }

        public String getPais() {
            return "Mexicalpan de las Tunas";
        }

        public String getEntrenador() {
            return "King Lion";
        }

        public Color getColorCamiseta() {
            return new Color(72, 13, 22);
        }

        public Color getColorPantalon() {
            return new Color(10, 47, 24);
        }

        public Color getColorFranja() {
            return new Color(21, 47, 173);
        }

        public Color getColorCalcetas() {
            return new Color(75, 117, 220);
        }

        public Color getColorPortero() {
            return new Color(174, 130, 180        );
        }

        public EstiloUniforme getEstilo() {
            return EstiloUniforme.FRANJA_HORIZONTAL;
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
                this.nombre=nombre;
                this.numero=numero;
                this.piel=piel;
                this.pelo=pelo;
                this.velocidad=velocidad;
                this.remate=remate;
                this.presicion=presicion;
                this.portero=portero;
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
                new JugadorImpl("Portero", 30, new Color(255,255,255), new Color(204,0,0),0.35d,0.15d,0.5d, true),
                new JugadorImpl("UNO", 1, new Color(51,51,0), new Color(255,255,51),0.51d,0.57d,0.5d, false),
                new JugadorImpl("Jugador", 3, new Color(255,255,255), new Color(204,0,0),0.5d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 4, new Color(255,255,255), new Color(204,0,0),0.5d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 5, new Color(255,255,255), new Color(204,0,0),0.5d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 6, new Color(255,255,255), new Color(204,0,0),0.5d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 7, new Color(255,255,255), new Color(204,0,0),0.5d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 8, new Color(255,255,255), new Color(204,0,0),0.5d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 9, new Color(255,255,255), new Color(204,0,0),0.5d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 10, new Color(255,255,255), new Color(204,0,0),0.5d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 11, new Color(255,255,255), new Color(204,0,0),0.5d,0.5d,0.5d, false)
            };
        }
    }

