/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos;

import com.jeaguirre.pronosticosdeportivos.archivos.Importador;
import com.jeaguirre.pronosticosdeportivos.torneo.Torneo;
import com.jeaguirre.pronosticosdeportivos.juego.Juego;
import com.jeaguirre.pronosticosdeportivos.jugador.Publico;
import java.io.IOException;

/**
 *
 * @author aguir
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        //Archivos para un torneo de 4 equipos, 3 rondas, 2 partidos por ronda - 5 personas
//        String archivoRonda = "\\ronda.txt";
//        String archivoPronostico = "\\pronostico.txt";


        //Archivos para un torneo de 8 equipos, 7 rondas, 4 partidos por ronda - 10 personas
        String archivoRonda = "\\ronda_completa.txt";
        String archivoPronostico = "\\pronostico_completo.txt";
        
        
        
        //Generación de la ronda
        Torneo torneo = new Torneo("Primer torneo");
        torneo.generarTorneo(Importador.leerArchivoTxt(archivoRonda));
//      torneo.mostrarRondas();
        
        //Generación de la apuesta de un jugador
        Publico publico = new Publico();
        publico.generarJugadores(Importador.leerArchivoTxt(archivoPronostico));
//      publico.mostrarJugadores();
        
        //Generación del juego
        Juego juego = new Juego();
//        juego.setPuntoPorPartido(5);
        juego.procesarJuego(torneo, publico);
        juego.mostrarResultados(publico);
        
    }
    
}
