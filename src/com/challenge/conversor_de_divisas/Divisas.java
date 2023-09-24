package com.challenge.conversor_de_divisas;

import java.util.ArrayList;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Divisas {
    
    /**
     * Recibe como parametro el nombre de la unidad a convertir 
     * y la unidad objetivo ambos String
     * retorna el tipo de cambio
     * @param unidadBase
     * @param unidadObjetivo
     * @return
     */
    public static double getCambioActual(String unidadBase, String unidadObjetivo, double cantidad) {
        
        //valor por defecto en caso que no funcione la coneccion
        Double valor = 0.0;

        try {
            // Conecta ala url especifica de acuerdo al los parametros
            String url = "https://www.google.com/finance/quote/" + unidadBase + "-" + unidadObjetivo;
            System.out.println(url);
            // Lee el contenido de la pagina web y lo almacena
            Document document = Jsoup.connect(url).get();

            // Busca el elemento especifico de acuerdo a su atributo y usa ese mismo para
            // obtener el valor del tipo de cambio
            Elements documentData = document.getElementsByAttribute("data-last-price");
            String tipoCambio = documentData.attr("data-last-price");

            // verifica el estado de la informacion buscada y retorna su valor
            if (tipoCambio != null) {
                valor = Double.parseDouble(tipoCambio);
                return  cantidad * valor;

            } else {
                return cantidad * valor;

            }
        } catch (Exception e) {
            System.out.println("Error al alcanzar la URL o posible seleccion invalida a convertir");
            e.printStackTrace();
        }
        return cantidad * valor;
    }
    
    public static void setListas(Vector<String> listaDeOpciones, ArrayList<String> listaDeSimbolos, ArrayList<String> codigos){
        //limpia CLEAR() e inicia ADD() todos sus valores en las listas ingresadas
        listaDeOpciones.clear();
        listaDeOpciones.add(" Selecciona una divisa");
        listaDeOpciones.add("USD (Dollar Estadounidense)");
        listaDeOpciones.add("EUR (Euro)");
        listaDeOpciones.add("GBP (Libra Esterlina)");
        listaDeOpciones.add("JPY (Yen Japones)");
        listaDeOpciones.add("KRW (Won SurCoreano)");
        listaDeOpciones.add("CRC (Colon Costarrisense)");

        listaDeSimbolos.clear();
        listaDeSimbolos.add(" ");
        listaDeSimbolos.add("$");
        listaDeSimbolos.add("€");
        listaDeSimbolos.add("£");
        listaDeSimbolos.add("¥");
        listaDeSimbolos.add("₩");
        listaDeSimbolos.add("₡");

        codigos.clear();
        codigos.add("null");
        codigos.add("USD");
        codigos.add("EUR");
        codigos.add("GBP");
        codigos.add("JPY");
        codigos.add("KRW");
        codigos.add("CRC");
    }
}

