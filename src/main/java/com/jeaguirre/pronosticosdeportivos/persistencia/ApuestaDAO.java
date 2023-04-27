/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos.persistencia;

import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumLocalia;
import com.jeaguirre.pronosticosdeportivos.enumeraciones.EnumResultado;
import com.jeaguirre.pronosticosdeportivos.jugador.Jugador;
import com.jeaguirre.pronosticosdeportivos.jugador.JugadorApuesta;
import com.jeaguirre.pronosticosdeportivos.jugador.JugadorRonda;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author aguir
 */
public class ApuestaDAO extends DAO{
    //Comentario a sacar
    final String READ = "SELECT jugador.nombre, ronda.nombre, partido.nombre, Elegido, Resultado FROM apuesta\n" +
                            "JOIN jugador ON apuesta.jugadorId = jugador.id\n" +
                            "JOIN ronda ON apuesta.rondaId = ronda.id\n" +
                            "JOIN partido ON apuesta.partidoId = partido.id\n" +
                            "ORDER BY jugador.id, ronda.id, partido.id ASC;";

    public ApuestaDAO(){
    }
    
    public ApuestaDAO(String usuario, String password, String database) {
        this.setUSER(usuario);
        this.setPASSWORD(password);
        this.setDATABASE(database);
    }
    
    public HashMap<String, Jugador> generarJugadoresDB() throws Exception {
        HashMap<String, Jugador> jugadores = new HashMap();
        
        try {
            
            conectarBase();
            PreparedStatement ps = conexion.prepareStatement(READ);
            resultado = ps.executeQuery();
            
            while(resultado.next()){
               String jugadorNombre = resultado.getString("jugador.nombre");
               String rondaId = resultado.getString("ronda.nombre");
               String partidoId = resultado.getString("partido.nombre");
               EnumLocalia equipoElegido = EnumLocalia.valueOf(resultado.getString("Elegido"));
               EnumResultado enumResultado = EnumResultado.valueOf(resultado.getString("Resultado"));    
               
                           if(!jugadores.containsKey(jugadorNombre)){
                Jugador jugador = new Jugador(jugadorNombre, jugadorNombre);
                jugadores.put(jugadorNombre, jugador);
            }
            
            if(!jugadores.get(jugadorNombre).tieneJugadorRonda(rondaId)){
                JugadorRonda jugadorRonda = new JugadorRonda(rondaId);
                jugadores.get(jugadorNombre).agregarJugadorRonda(jugadorRonda);
            }
            
            if(!jugadores.get(jugadorNombre).getJugadorRonda(rondaId).tieneJugadorApuesta(partidoId)){
                JugadorApuesta jugadorApuesta = new JugadorApuesta(partidoId, equipoElegido, enumResultado);
                jugadores.get(jugadorNombre).getJugadorRonda(rondaId).agregarJugadorApuesta(jugadorApuesta);
            }
            }
            
            
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            desconectarBase();
        }
        
        
        return jugadores;
    }
    
}
