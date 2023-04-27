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
    public static void main(String[] args) throws IOException, Exception {

        ///***** GENERACION POR ARCHIVOS - ETAPA 2 *****
        //Archivos para un torneo de 8 equipos, 7 rondas, 4 partidos por ronda - 10 personas
//        String archivoRonda = "\\torneo_8_equipos_1.txt";        
//        String archivoPronostico = "\\pronostico_completo.txt";
                
        //Generación del torneo
//        Torneo torneo = new Torneo("Primer torneo");
//        torneo.generarTorneo(Importador.leerArchivoTxt(archivoRonda));
        
        //Generación de los jugadores (Publico)
//        Publico publico = new Publico();
        //Si se lee desde un archivo de texto
//      publico.generarJugadores(Importador.leerArchivoTxt(archivoPronostico));
        //Si se lee desde una base de datos
//        publico.generarJugadoresDB();
        

        //********* GENERACION DESDE LA CLASE JUEGO - ETAPA 3 **********
        //Generación del juego
        Juego juego = new Juego();
//      juego.setPuntoPorPartido(5);
//      juego.procesarJuegoExterno(torneo, publico);
//        juego.mostrarResultadosExterno(publico);
        
        //Usando el mismo método en juego
        juego.juegoInterno();
        
    }
    
}
