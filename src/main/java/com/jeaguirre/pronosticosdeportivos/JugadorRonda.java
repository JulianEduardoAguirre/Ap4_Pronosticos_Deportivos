/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos;

import java.util.HashMap;

/**
 *
 * @author aguir
 */
public class JugadorRonda {
    private final String rondaId;
    private final HashMap<String, JugadorApuesta> jugadorRonda;

    public JugadorRonda(String rondaId) {
        this.rondaId = rondaId;
        jugadorRonda = new HashMap();
    }

    public String getRondaId() {
        return rondaId;
    }
    
    public void agregarJugadorApuesta(JugadorApuesta jugadorApuesta){
        this.jugadorRonda.put(jugadorApuesta.getPartidoId(), jugadorApuesta);
    }
    
    public JugadorApuesta getJugadorApuesta(String partidoId){
        return jugadorRonda.get(partidoId);
    }
    
}
