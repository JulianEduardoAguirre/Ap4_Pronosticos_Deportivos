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
public class Torneo {
    private final String id;
    private final HashMap<String, Ronda> rondas;

    public Torneo(String id) {
        this.id = id;
        rondas = new HashMap();
    }

    public String getId() {
        return id;
    }

    public HashMap<String, Ronda> getRondas() {
        return rondas;
    }
    
    public void agregarRonda(Ronda ronda){
        rondas.put(ronda.getId(), ronda);
    }

    public Ronda getRonda(String rondaId) {
        return rondas.get(rondaId);
    }
    
    public void mostrarRondas(){
        rondas.forEach((k,v) -> {
            System.out.println(v);
        });
    }
    
    
}
