/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos;

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
        lineasRonda.forEach((linea) -> {
            String[] datosUnaLinea = linea.split(",");
            String rondaId = datosUnaLinea[0];
            String partidoId = datosUnaLinea[1];
            EnumEquipo local = EnumEquipo.valueOf(datosUnaLinea[2]);
            EnumEquipo visitante = EnumEquipo.valueOf(datosUnaLinea[3]);
            Integer golesLocal = Integer.parseInt(datosUnaLinea[4]);
            Integer golesVisitante = Integer.parseInt(datosUnaLinea[5]);
            
            if(!torneo.getRondas().containsKey(rondaId)){
                Ronda ronda = new Ronda(rondaId);
                torneo.agregarRonda(ronda);
            }
            
            if(!torneo.getRonda(rondaId).getPartidos().containsKey(partidoId)){
                Partido partido = new Partido(partidoId);
                partido.setEnumEquipo(local, visitante);
                partido.setGoles(golesLocal, golesVisitante);
                torneo.getRonda(rondaId).agregarPartido(partido);
            }
        });
        
        
        //Generación de la apuesta de un jugador
        HashMap<String, Jugador> jugadores = new HashMap();
        List<String> lineasJugadores = Files.readAllLines(Paths.get(basePath + archivoPronostico));
        lineasJugadores.forEach( (linea) -> {
            String[] datosUnaLinea = linea.split(",");
            String jugadorNombre = datosUnaLinea[0];
            String rondaId = datosUnaLinea[1];
            String partidoId = datosUnaLinea[2];
            EnumLocalia equipoElegido = EnumLocalia.valueOf(datosUnaLinea[3]);
            EnumResultado resultado = EnumResultado.valueOf(datosUnaLinea[4]);
            
            if(!jugadores.containsKey(jugadorNombre)){
                Jugador jugador = new Jugador(jugadorNombre, jugadorNombre);
                jugadores.put(jugadorNombre, jugador);
            }
            
            if(!jugadores.get(jugadorNombre).tieneJugadorRonda(rondaId)){
                JugadorRonda jugadorRonda = new JugadorRonda(rondaId);
                jugadores.get(jugadorNombre).agregarJugadorRonda(jugadorRonda);
            }
            
            if(!jugadores.get(jugadorNombre).getJugadorRonda(rondaId).tieneJugadorApuesta(partidoId)){
                JugadorApuesta jugadorApuesta = new JugadorApuesta(partidoId, equipoElegido, resultado);
                jugadores.get(jugadorNombre).getJugadorRonda(rondaId).agregarJugadorApuesta(jugadorApuesta);
            }
            
            
        });

        //Procesamiento de los resultados finales del partido y las apuestas del jugador
        torneo.getRondas().forEach((rondaId, ronda) -> {
            ronda.getPartidos().forEach((partidoId, partido) -> {
                jugadores.forEach((jugadorNombre, jugador) -> {
                    
                    if(jugador.getJugadorRonda(rondaId).getJugadorApuesta(partidoId).getEquipoResultado().equals(partido.calcularResultado(jugador.getJugadorRonda(rondaId).getJugadorApuesta(partidoId).getEquipoElegido()))){
                        jugador.sumarPuntos(1);
                    }
                });
            });
        });
        
        //Mostrando la cantidad de puntos del jugador
        jugadores.forEach((jugadorNombre, jugador) -> {
            System.out.println(jugadorNombre + ": " + jugador.getPuntos() + " puntos.");
        });
        
    }
    
}
