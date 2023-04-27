/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos.juego;

import com.jeaguirre.pronosticosdeportivos.archivos.Importador;
import com.jeaguirre.pronosticosdeportivos.jugador.Jugador;
import com.jeaguirre.pronosticosdeportivos.jugador.Publico;
import com.jeaguirre.pronosticosdeportivos.torneo.Torneo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import org.json.simple.JSONObject;

/**
 *
 * @author aguir
 */
public class Juego {
    
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private Integer puntosPorPartido;
    private Torneo torneo;
    private Publico publico;
    
    public Juego(){
        puntosPorPartido = 3;
    }
    
    public void setPuntoPorPartido(Integer valor){
        if(valor >= 1 && valor < 10){
            puntosPorPartido = valor;
        }
    }
    
    public void generarJuego() throws IOException, Exception{
        System.out.println("Generación del torneo");
        String archivoTorneo = "";
        String archivoConfiguracion = "";
        do{
            System.out.println("Ingrese el nombre del archivo (txt): ");
            archivoTorneo = leer.next();
        } while (archivoTorneo.equals(""));
        
        do{
            System.out.println("Ingrese el nombre del archivo de configuración (con su extensión): ");
            archivoConfiguracion = leer.next();
        } while (archivoConfiguracion.equals(""));
        
        //Generación del Torneo
        this.torneo = new Torneo("Primer torneo");
        torneo.generarTorneo(Importador.leerArchivoTxt(archivoTorneo + ".txt"));
        
        //Lectura del archivo de configuraciones
        JSONObject configuraciones = Importador.leerArchivoJson(archivoConfiguracion);
        
        this.puntosPorPartido = Math.toIntExact((long) configuraciones.get("puntosPorPartido"));
        
        //Generación del público desde la BBDD
        this.publico = new Publico();
//      publico.generarJugadores(Importador.leerArchivoTxt(archivoPronostico));
//      publico.generarJugadoresDB();
        publico.generarJugadoresDBConfig(configuraciones);
        
        
        
        
    }
    
    public void procesarJuego(){
        HashMap<String, Jugador> jugadores = this.publico.getJugadores();
        
        this.torneo.getRondas().forEach((rondaId, ronda) -> {
            ronda.getPartidos().forEach((partidoId, partido) -> {
                jugadores.forEach((jugadorNombre, jugador) -> {
                    
                    if(jugador.getJugadorRonda(rondaId).getJugadorApuesta(partidoId).getEquipoResultado().equals(partido.calcularResultado(jugador.getJugadorRonda(rondaId).getJugadorApuesta(partidoId).getEquipoElegido()))){
                        jugador.sumarPuntos(this.puntosPorPartido);
                        jugador.sumarAciertos();
                    }
                });
            });
        });
    }
    
    
    public void procesarJuegoExterno(Torneo torneo, Publico publico){
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
    
    public void mostrarResultadosExterno(Publico publico){
        publico.getJugadores().forEach((jugadorNombre, jugador) -> {
            System.out.println(jugadorNombre + ":\n\tPuntos: " + jugador.getPuntos() + " (" + jugador.getAciertos() + " aciertos).");
        });       
    }
    
    public void mostrarResultados(){
        this.publico.getJugadores().forEach((jugadorNombre, jugador) -> {
            System.out.println(jugadorNombre + ":\n\tPuntos: " + jugador.getPuntos() + " (" + jugador.getAciertos() + " aciertos).");
        });       
    }
    
    public void juegoInterno() throws Exception{
        this.generarJuego();
        this.procesarJuego();
        this.mostrarResultados();
    }
}
