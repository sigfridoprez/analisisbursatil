/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javahispano.javacup.tacticas.ejemplos;

import java.awt.Color;
import org.javahispano.javacup.modelo.*;

public class TacticaRealMadridDetalle implements TacticaDetalle {

    public String getNombre() {
        return "Real Madrid";
    }

    public String getPais() {
        return "España";
    }

    public String getEntrenador() {
        return "Bernd Schuster";
    }

    public Color getColorCamiseta() {
        return new Color(255, 255, 255);
    }

    public Color getColorPantalon() {
        return new Color(255, 255, 255);
    }

    public Color getColorFranja() {
        return new Color(64, 49, 79);
    }

    public Color getColorCalcetas() {
        return new Color(255, 255, 255);
    }

    public Color getColorPortero() {
        return new Color(165, 182, 191);
    }

    public EstiloUniforme getEstilo() {
        return EstiloUniforme.SIN_ESTILO;
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
            new JugadorImpl("Casillas", 16, new Color(255,200,150), new Color(50,0,0),1.0d,0.99d,1.0d, true),
            new JugadorImpl("Salgado", 1, new Color(255,200,150), new Color(96,79,56),0.72d,0.72d,0.5d, false),
            new JugadorImpl("Heinze", 2, new Color(255,200,150), new Color(89,89,37),1.0d,1.0d,1.0d, false),
            new JugadorImpl("Ramos", 3, new Color(255,200,150), new Color(50,0,0),0.71d,1.0d,0.6d, false),
            new JugadorImpl("Cannavaro", 4, new Color(219,172,128), new Color(143,105,84),0.72d,0.95d,0.77d, false),
            new JugadorImpl("Diarra",5, new Color(88,68,47), new Color(0,0,0),0.87d,0.73d,1.0d, false),
            new JugadorImpl("Robben", 6, new Color(255,200,150), new Color(156,107,80),1.0d,1.0d,1.0d, false),
            new JugadorImpl("Gago", 7, new Color(255,200,150), new Color(50,0,0),0.75d,0.5d,0.5d, false),
            new JugadorImpl("Saviola", 8, new Color(255,200,150), new Color(0,0,0),0.66d,1.0d,1.0d, false),
            new JugadorImpl("V.Nistelrooy", 9, new Color(255,200,150), new Color(50,0,0),1.0d,0.98d,1.0d, false),
            new JugadorImpl("Guti", 10, new Color(255,200,150), new Color(50,0,0),0.73d,0.6d,0.5d, false)
        };
    }
}