/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos.torneo;

import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumLocalia;
import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumEquipo;
import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumResultado;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author aguir
 */
public class Partido {
    private final String id;
    private final EnumEquipo[] equipos;
    private final Integer[] goles;

    public Partido(String id) {
        this.id = id;
        this.equipos = new EnumEquipo[2];
        this.goles = new Integer[2];
    }
    
    public void setEnumEquipo(EnumEquipo local, EnumEquipo visitante){
        equipos[0] = local;
        equipos[1] = visitante;
    }
    
    public void setGoles(Integer golesLocal, Integer golesVisitante){
        goles[0] = golesLocal;
        goles[1] = golesVisitante;
    }
    
    public String getId(){
        return id;
    }
    
    public EnumEquipo[] getEquipos() {
        return equipos;
    }
    
    public Integer[] getGoles() {
        return goles;
    }
    
    public EnumResultado calcularResultado(EnumLocalia equipoElegido){
        if(Objects.equals(goles[0], goles[1])){
            return EnumResultado.EMPATE;
        } else {
            if(equipoElegido.equals(EnumLocalia.LOCAL)){
                if(goles[0]>goles[1]){
                    return EnumResultado.GANADOR;
                } else {
                    return EnumResultado.PERDEDOR;
                }
            }else {
                if(goles[0]<goles[1]){
                    return EnumResultado.GANADOR;
                } else {
                    return EnumResultado.PERDEDOR;
                }
            }
        }
    }

    @Override
    public String toString() {
        return equipos[0].toString() + " " + goles[0].toString() + "-" + goles[1].toString() + " " + equipos[1].toString();
    }
    
    
    
    
}
