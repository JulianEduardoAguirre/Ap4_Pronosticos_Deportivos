/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos.torneo;

import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumEquipo;
import java.util.HashMap;
import java.util.List;

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
    
    public void generarTorneo(List<String> lineasRonda){
        lineasRonda.forEach((linea) -> {
            String[] datosUnaLinea = linea.split(",");
            String rondaId = datosUnaLinea[0];
            String partidoId = datosUnaLinea[1];
            EnumEquipo local = EnumEquipo.valueOf(datosUnaLinea[2]);
            EnumEquipo visitante = EnumEquipo.valueOf(datosUnaLinea[3]);
            Integer golesLocal = Integer.parseInt(datosUnaLinea[4]);
            Integer golesVisitante = Integer.parseInt(datosUnaLinea[5]);
            
            if(!this.getRondas().containsKey(rondaId)){
                Ronda ronda = new Ronda(rondaId);
                this.agregarRonda(ronda);
            }
            
            if(!this.getRonda(rondaId).getPartidos().containsKey(partidoId)){
                Partido partido = new Partido(partidoId);
                partido.setEnumEquipo(local, visitante);
                partido.setGoles(golesLocal, golesVisitante);
                this.getRonda(rondaId).agregarPartido(partido);
            }
        });
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
