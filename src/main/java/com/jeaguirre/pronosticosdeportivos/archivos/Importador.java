/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeaguirre.pronosticosdeportivos.archivos;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 *
 * @author aguir
 */
public class Importador {
    private static final String BASEPATH = new File("").getAbsolutePath().concat("\\src\\main\\resources\\");
    
    public static List<String> leerArchivoTxt(String archivo) throws IOException{
        List<String> lineas = Files.readAllLines(Paths.get(BASEPATH + archivo));
        return lineas;
    }
    
    public static JSONObject leerArchivoJson(String archivo) {
        JSONObject js = null;
        try {
            Object ob = new JSONParser().parse(new FileReader(BASEPATH + archivo + ".json"));
            js = (JSONObject) ob;
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        
        return js;
    }
}
