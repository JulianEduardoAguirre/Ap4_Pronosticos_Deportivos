/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos.juego;

import com.jeaguirre.pronosticosdeportivos.jugador.Jugador;
import com.jeaguirre.pronosticosdeportivos.jugador.Publico;
import com.jeaguirre.pronosticosdeportivos.torneo.Torneo;
import java.util.HashMap;

/**
 *
 * @author aguir
 */
public class Juego {
    
    int puntosPorPartido = 3;
    public void procesarJuego(Torneo torneo, Publico publico){
        HashMap<String, Jugador> jugadores = publico.getJugadores();
        
        torneo.getRondas().forEach((rondaId, ronda) -> {
            ronda.getPartidos().forEach((partidoId, partido) -> {
                jugadores.forEach((jugadorNombre, jugador) -> {
                    
                    if(jugador.getJugadorRonda(rondaId).getJugadorApuesta(partidoId).getEquipoResultado().equals(partido.calcularResultado(jugador.getJugadorRonda(rondaId).getJugadorApuesta(partidoId).getEquipoElegido()))){
                        jugador.sumarPuntos(puntosPorPartido);
                        jugador.sumarAciertos();
                    }
                });
            });
        });
    }
    
    public void mostrarResultados(Publico publico){
        publico.getJugadores().forEach((jugadorNombre, jugador) -> {
            System.out.println(jugadorNombre + ": " + jugador.getPuntos() + " puntos. Número de aciertos: " + jugador.getAciertos());
        });       
    }
}
