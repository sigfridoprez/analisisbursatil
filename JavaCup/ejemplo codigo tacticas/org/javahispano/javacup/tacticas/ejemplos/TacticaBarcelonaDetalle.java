package org.javahispano.javacup.tacticas.ejemplos;

import java.awt.Color;
import org.javahispano.javacup.modelo.*;

public class TacticaBarcelonaDetalle  implements TacticaDetalle {

    public String getNombre() {
        return "Barcelona";
    }

    public String getPais() {
        return "España";
    }

    public String getEntrenador() {
        return "Josep Guardiola";
    }

    public Color getColorCamiseta() {
        return new Color(26, 26, 99);
    }

    public Color getColorPantalon() {
        return new Color(23, 23, 101);
    }

    public Color getColorFranja() {
        return new Color(129, 38, 79);
    }

    public Color getColorCalcetas() {
        return new Color(75, 15, 75);
    }

    public Color getColorPortero() {
        return new Color(0, 51, 204);
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
            new JugadorImpl("Valdes", 1, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,1.0d, true),
            new JugadorImpl("Milito", 2, new Color(255,200,150), new Color(50,0,0),0.69d,0.71d,0.76d, false),
            new JugadorImpl("Marquez", 4, new Color(255,200,150), new Color(50,0,0),0.72d,0.72d,1.0d, false),
            new JugadorImpl("Iniesta", 11, new Color(255,200,150), new Color(50,0,0),0.71d,0.73d,0.68d, false),
            new JugadorImpl("Puyol", 5, new Color(255,200,150), new Color(50,0,0),1.0d,0.73d,0.69d, false),
            new JugadorImpl("Hernandez", 6, new Color(255,200,150), new Color(50,0,0),0.75d,0.74d,0.77d, false),
            new JugadorImpl("Sylvinho", 7, new Color(255,200,150), new Color(50,0,0),0.83d,0.73d,0.74d, false),
            new JugadorImpl("Messi", 10, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,1.0d, false),
            new JugadorImpl("Caceres", 8, new Color(255,200,150), new Color(50,0,0),0.79d,0.5d,0.5d, false),
            new JugadorImpl("Eto", 9, new Color(101,72,46), new Color(50,0,0),1.0d,1.0d,1.0d, false),
            new JugadorImpl("Henry", 16, new Color(162,117,74), new Color(50,0,0),1.0d,1.0d,1.0d, false)
        };
    }
}