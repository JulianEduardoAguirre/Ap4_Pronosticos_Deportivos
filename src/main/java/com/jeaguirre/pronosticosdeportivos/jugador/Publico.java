/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos.jugador;

import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumLocalia;
import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumResultado;
import com.jeaguirre.pronosticosdeportivos.persistencia.ApuestaDAO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author aguir
 */
public class Publico {
    private HashMap<String, Jugador> jugadores;

    public Publico() {
        this.jugadores = new HashMap();
    }
    
    public void generarJugadores(List<String> lineasJugadores){
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
    }
    
    public void generarJugadoresDB() throws Exception{
        ApuestaDAO dao = new ApuestaDAO();
        HashMap<String, Jugador> otrosJugadores = dao.generarJugadoresDB();
        
        this.jugadores = otrosJugadores;
//        System.out.println("HOLA");
//        System.out.println(otrosJugadores);
//        System.out.println("CHAU");
    }
    
    
    
    public HashMap<String, Jugador> getJugadores(){
        return this.jugadores;
    }
    
    public void mostrarJugadores(){
        jugadores.forEach((k,v) -> {System.out.println(k);});
    }
    
    
}
