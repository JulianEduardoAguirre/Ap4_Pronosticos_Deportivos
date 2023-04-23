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
public class Jugador {
    private final String id;
    private final String nombre;
    private final int puntos;
    private final HashMap<String, JugadorRonda> pronostico;

    public Jugador(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        puntos = 0;
        pronostico = new HashMap();
    }
    
    
    public String getNombre(){
        return nombre;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    public void agregarJugadorRonda(JugadorRonda jugadorRonda){
        pronostico.put(jugadorRonda.getRondaId(), jugadorRonda);
    }
    
    public JugadorRonda getJugadorRonda(String rondaId){
        return pronostico.get(rondaId);
    }
    
    public boolean tieneJugadorRonda(String rondaId){
        return pronostico.containsKey(rondaId);
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", puntos=" + puntos + ", pronostico=" + pronostico + '}';
    }
    
    
    
    
}
