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
public class Ronda {
    private final String id;
    private final HashMap<String, Partido> partidos;  

    public Ronda(String id) {
        this.id = id;
        partidos = new HashMap();
    }

    public String getId() {
        return id;
    }

    public HashMap<String, Partido> getPartidos() {
        return partidos;
    }
    
    
    public void agregarPartido(Partido partido){
        partidos.put(partido.getId(), partido);
    }

    public Partido getPartido(String partidoId){
        return partidos.get(partidoId);
    }

    @Override
    public String toString() {
        return id + " " + partidos.toString();
    }   
    
    
}
