/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos.archivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author aguir
 */
public class Importador {
    private static String basePath = new File("").getAbsolutePath().concat("\\src\\main\\resources\\");
    
    public static List<String> leerArchivoTxt(String archivo) throws IOException{
        List<String> lineas = Files.readAllLines(Paths.get(basePath + archivo));
        return lineas;
    }
}
