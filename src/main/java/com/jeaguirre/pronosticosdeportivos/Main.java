/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos;

import com.jeaguirre.pronosticosdeportivos.jugador.JugadorRonda;
import com.jeaguirre.pronosticosdeportivos.jugador.JugadorApuesta;
import com.jeaguirre.pronosticosdeportivos.jugador.Jugador;
import com.jeaguirre.pronosticosdeportivos.torneo.Ronda;
import com.jeaguirre.pronosticosdeportivos.torneo.Partido;
import com.jeaguirre.pronosticosdeportivos.torneo.Torneo;
import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumLocalia;
import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumEquipo;
import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumResultado;
import com.jeaguirre.pronosticosdeportivos.juego.Juego;
import com.jeaguirre.pronosticosdeportivos.jugador.Publico;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

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
        String basePath = new File("").getAbsolutePath().concat("\\src\\main\\resources");
        String archivoRonda = "\\ronda.txt";
        String archivoPronostico = "\\pronostico.txt";
        
        //Generación de la ronda
        List<String> lineasRonda = Files.readAllLines(Paths.get(basePath + archivoRonda));
        Torneo torneo = new Torneo("Primer torneo");
        torneo.generarTorneo(lineasRonda);
//      torneo.mostrarRondas();
        
        //Generación de la apuesta de un jugador
        List<String> lineasJugadores = Files.readAllLines(Paths.get(basePath + archivoPronostico));
        Publico publico = new Publico();
        publico.generarJugadores(lineasJugadores);
//      publico.mostrarJugadores();
        
        //Generación del juego
        Juego juego = new Juego();
                
        juego.procesarJuego(torneo, publico);
        juego.mostrarResultados(publico);
        
    }
    
}
