package com.challenge.conversor_de_divisas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Temperatura {
    
    /**
     * Recibe como parametro el nombre de la unidad a convertir 
     * y la unidad objetivo ambos String
     * retorna la unidad convertida
     * @param unidadBase
     * @param unidadObjetivo
     * @return
     */
    static double getTemperatura(String unidadBase, String unidadObjetivo, double n){
        Map<String,Double> celsio = new HashMap<>();
        celsio.put("c", n);
        celsio.put("f", (n * 9/5) + 32);
        celsio.put("k", n + 273.15);

        Map<String,Double> fahrenheit = new HashMap<>();
        fahrenheit.put("c", (n - 32) * 5/9);
        fahrenheit.put("f", n);
        fahrenheit.put("k", (n - 32) * 5/9 + 273.15);

        Map<String,Double> kelvin = new HashMap<>();
        kelvin.put("c", n - 273.15);
        kelvin.put("f", (n - 273.15) * 9/5 + 32);
        kelvin.put("k", n);

        Map<String,Map<String,Double>> conjunto = new HashMap<>();
        conjunto.put("c",celsio);
        conjunto.put("f",fahrenheit);
        conjunto.put("k",kelvin);

        var proporcionMap = conjunto.get(unidadBase);
        return  (double) proporcionMap.get(unidadObjetivo);
    }

    public static void setListas(Vector<String> listaDeOpciones, ArrayList<String> listaDeSimbolos, ArrayList<String> codigos){
        //limpia CLEAR() e inicia ADD() todos sus valores en las listas ingresadas
        listaDeOpciones.clear();
        listaDeOpciones.add("Selecciona una escala");
        listaDeOpciones.add("(°C) Celsius");
        listaDeOpciones.add("(°F) Fahrenheit ");
        listaDeOpciones.add("(K) Kelvin");
        
        listaDeSimbolos.clear();
        listaDeSimbolos.add("");
        listaDeSimbolos.add("°C");
        listaDeSimbolos.add("°F");
        listaDeSimbolos.add("K");

        codigos.clear();
        codigos.add("null");
        codigos.add("c");
        codigos.add("f");
        codigos.add("k");
    }
}
