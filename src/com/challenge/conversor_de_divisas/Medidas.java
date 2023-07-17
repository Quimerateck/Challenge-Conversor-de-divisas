package com.challenge.conversor_de_divisas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Medidas {    
    
    /**
     * Recibe como parametro el nombre de la unidad a convertir 
     * y la unidad objetivo ambos String 
     * retorna la unidad convertida
     * @param map
     * @param unidad
     * @return
     */
    static double getMedidas(String unidadBase, String unidadObjetivo, double n){
        Map<String,Double> metros = new HashMap<>();
        metros.put("m", 1.0);
        metros.put("cm", 100.0);
        metros.put("mm", 1000.0);
        metros.put("in", 39.3701);
        metros.put("ft", 3.28084);

        Map<String,Double> centimetro = new HashMap<>();
        centimetro.put("m", 0.01);
        centimetro.put("cm", 1.0);
        centimetro.put("mm", 10.0);
        centimetro.put("in", 0.393701);
        centimetro.put("ft", 0.0328084);

        Map<String,Double> milimetro = new HashMap<>();
        milimetro.put("m", 0.001);
        milimetro.put("cm", 0.1);
        milimetro.put("mm", 1.0);
        milimetro.put("in", 0.0393701);
        milimetro.put("ft", 0.00328084);

        Map<String,Double> pulgadas = new HashMap<>();
        pulgadas.put("m", 0.0254);
        pulgadas.put("cm", 2.54);
        pulgadas.put("mm", 25.4);
        pulgadas.put("in", 1.0);
        pulgadas.put("ft", 0.0833333);
        
        Map<String,Double> pies = new HashMap<>();
        pies.put("m", 0.3048);
        pies.put("cm", 30.48);
        pies.put("mm", 304.8);
        pies.put("in", 12.0);
        pies.put("ft", 1.0);
        
        Map<String,Map<String,Double>> conjunto = new HashMap<>();
        conjunto.put("m",metros);
        conjunto.put("cm",centimetro);
        conjunto.put("mm",milimetro);
        conjunto.put("in",pulgadas);
        conjunto.put("ft",pies);

        var proporcionMap = conjunto.get(unidadBase);
        return (double) (n * proporcionMap.get(unidadObjetivo));
    }

    public static void setListas(Vector<String> listaDeOpciones, ArrayList<String> listaDeSimbolos, ArrayList<String> codigos){
        //limpia CLEAR() e inicia ADD() todos sus valores en las listas ingresadas
        listaDeOpciones.clear();
        listaDeOpciones.add("Selecciona una unidad");
        listaDeOpciones.add("m (Metros)");
        listaDeOpciones.add("cm (Centimetros)");
        listaDeOpciones.add("mm (Milimetros)");
        listaDeOpciones.add("in (Pulgadas)");
        listaDeOpciones.add("ft (Pies)");


        listaDeSimbolos.clear();
        listaDeSimbolos.add("");
        listaDeSimbolos.add("m");
        listaDeSimbolos.add("cm");
        listaDeSimbolos.add("mm");
        listaDeSimbolos.add("in");
        listaDeSimbolos.add("ft");

        codigos.clear();
        codigos.add("null");
        codigos.add("m");
        codigos.add("cm");
        codigos.add("mm");
        codigos.add("in");
        codigos.add("ft");
    }
}
