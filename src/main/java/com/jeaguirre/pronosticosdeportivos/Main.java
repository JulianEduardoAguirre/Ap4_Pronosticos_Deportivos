/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos;

import com.jeaguirre.pronosticosdeportivos.juego.Juego;
import java.io.IOException;

/**
 *
 * @author aguir
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {
     
        //********* GENERACION DESDE LA CLASE JUEGO - ETAPA 3 **********
        //Generación del juego
        Juego juego = new Juego();
        juego.juegoInterno();
        
    }
    
}
