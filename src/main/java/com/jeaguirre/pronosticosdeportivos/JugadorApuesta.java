/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos;

/**
 *
 * @author aguir
 */
public class JugadorApuesta {
    private final String partidoId;
    private final EnumLocalia equipoElegido;
    private final EnumResultado equipoResultado;

    public JugadorApuesta(String partidoId, EnumLocalia equipoElegido, EnumResultado equipoResultado) {
        this.partidoId = partidoId;
        this.equipoElegido = equipoElegido;
        this.equipoResultado = equipoResultado;
    }

    public String getPartidoId() {
        return partidoId;
    }

    public EnumLocalia getEquipoElegido() {
        return equipoElegido;
    }

    public EnumResultado getEquipoResultado() {
        return equipoResultado;
    }

    @Override
    public String toString() {
        return partidoId + ": " + equipoElegido + " sale " + equipoResultado + ".";
    }
    
    
    
    
}
