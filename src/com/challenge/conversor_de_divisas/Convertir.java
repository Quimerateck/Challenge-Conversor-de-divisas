package com.challenge.conversor_de_divisas;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Convertir {
    
    /**
     * Ingresar debe usar mayusculas
     * @param fromDivisa
     * @param toDivisa
     */
    public static double getCambioActual(String fromDivisa, String toDivisa){

        Double valor;

        try {
            String url = "https://www.google.com/finance/quote/"+ fromDivisa + "-" + toDivisa;
            System.out.println(url);
            Document document = Jsoup.connect(url).get();
            
            // Busca el elemento especifico
            Element tipoCambio = document.selectFirst(".AHmHk");
            if (tipoCambio != null) {
                valor = Double.parseDouble(tipoCambio.text());
                return valor;
            } else {
                return valor = 0.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor = 0.0;
    }

    public static String getDivisas(JComboBox<String> jb){
        int indice = jb.getSelectedIndex();

        ArrayList<String> codigos = new ArrayList<>();
        codigos.add("USD");
        codigos.add("EUR");
        codigos.add("GBP");
        codigos.add("JPY");
        codigos.add("KRW");
        codigos.add("CRC");

        return codigos.get(indice);
    }

    public static void calcularTipoDeCambio(JTextField t1, JTextField t2, double tipoCambio){
        ArrayList<String> simbolo = new ArrayList<>();
        simbolo.add("$");
        simbolo.add("€");
        simbolo.add("£");
        simbolo.add("¥");
        simbolo.add("₩");
        simbolo.add("₡");

        double a = Double.parseDouble(t1.getText());
        if (tipoCambio!=0) {
            double total = a*tipoCambio;
            t2.setText("" + total);
        }else{
            t2.setText("ERROR");
        }
    }
}
