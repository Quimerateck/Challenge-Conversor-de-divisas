package com.challenge.conversor_de_divisas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ConversorDeDivisas{

    /**
     * genera la interfase grafica del convertidor de divisas
     */
    public ConversorDeDivisas() {
        String panelActivo = "";
        JFrame f = new JFrame("Conversor de " + panelActivo);

        JLabel l1 = new JLabel("Convertir de: ");
        JLabel l2 = new JLabel(" a ");
        JLabel l3 = new JLabel();
        JLabel l4 = new JLabel();

        JTextField t1 = new JTextField("0");
        JTextField t2 = new JTextField("0");

        JButton b1 = new JButton("Convertir");

        Vector<String> tipoMoneda = new Vector<>();
        tipoMoneda.add("Selecciona una divisa");
        tipoMoneda.add("USD (Dollar Estadounidense)");
        tipoMoneda.add("EUR (Euro)");
        tipoMoneda.add("GBP (Libra Esterlina)");
        tipoMoneda.add("JPY (Yen Japones)");
        tipoMoneda.add("KRW (Won SurCoreano)");
        tipoMoneda.add("CRC (Colon Costarrisense)");

        ArrayList<String> simbolo = new ArrayList<>();
        simbolo.add(" ");
        simbolo.add("$");
        simbolo.add("€");
        simbolo.add("£");
        simbolo.add("¥");
        simbolo.add("₩");
        simbolo.add("₡");

        JComboBox<String> cb1 = new JComboBox<>(tipoMoneda);
        cb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int a = cb1.getSelectedIndex();
                l3.setText(simbolo.get(a));
            }
        });

        JComboBox<String> cb2 = new JComboBox<>(tipoMoneda);
        cb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int b = cb2.getSelectedIndex();
                l4.setText(simbolo.get(b));
            }

        });

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String divisa1 = getCodigodeUnidad(cb1);
                String divisa2 = getCodigodeUnidad(cb2);
                double valor = getCambioActual(divisa1, divisa2);
                calcularConversion(t1, t2, valor);
            }
        });

        // Define las posiciones de los objetos visuales
        l1.setBounds(50, 20, 200, 50);
        l2.setBounds(310, 20, 50, 50);
        l3.setBounds(115, 70, 15, 30);
        l4.setBounds(315, 70, 15, 30);
        t1.setBounds(130, 70, 170, 30);
        t1.setHorizontalAlignment(SwingConstants.RIGHT);
        t2.setBounds(330, 70, 170, 30);
        t2.setHorizontalAlignment(SwingConstants.RIGHT);
        b1.setBounds(260, 110, 100, 30);
        cb1.setBounds(130, 30, 170, 30);
        cb2.setBounds(330, 30, 170, 30);

        // Añade cada objeto al frame a ser mostrado
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(t1);
        f.add(t2);
        f.add(b1);
        f.add(cb1);
        f.add(cb2);
        f.setSize(600, 200);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    /**
     * Usa dos String representando los codigos de las divisas (Eg. USD)
     * y retorna el valor que corresponde al tipo de cambio de la primera con la
     * segunda
     * 
     * @param fromDivisa
     * @param toDivisa
     * @return
     */
    public static double getCambioActual(String fromDivisa, String toDivisa) {

        Double valor = 0.0;

        try {
            // Conecta ala web y lee su contenido
            String url = "https://www.google.com/finance/quote/" + fromDivisa + "-" + toDivisa;
            System.out.println(url);
            Document document = Jsoup.connect(url).get();

            // Busca el elemento especifico de acuerdo a su atributo y usa ese mismo para
            // obtener el valor
            Elements documentData = document.getElementsByAttribute("data-last-price");
            String tipoCambio = documentData.attr("data-last-price");

            // verifica el estado de la informacion buscada y retorna su valor
            if (tipoCambio != null) {
                valor = Double.parseDouble(tipoCambio);
                return valor;

            } else {
                return valor;

            }
        } catch (Exception e) {
            System.out.println("Error al alcanzar la URL o posible seleccion invalida a convertir");
            e.printStackTrace();
        }
        return valor;
    }

    /**
     * Convierte el indice del JComboBox en un codigo de divisa para ser usado en el
     * metodo
     * getCambioActual()
     * 
     * @param jb
     * @return
     */
    public static String getCodigodeUnidad(JComboBox<String> jb) {
        int indice = jb.getSelectedIndex();

        ArrayList<String> codigos = new ArrayList<>();
        codigos.add("null");
        codigos.add("USD");
        codigos.add("EUR");
        codigos.add("GBP");
        codigos.add("JPY");
        codigos.add("KRW");
        codigos.add("CRC");

        return codigos.get(indice);
    }

    /**
     * hace el calculo respectivo alas divisas selecionadas y muestra su valor en el
     * 2ndo cuadro de texto
     * 
     * @param t1
     * @param t2
     * @param tipoCambio
     */
    public static void calcularConversion(JTextField t1, JTextField t2, double tipoCambio) {

        double a = Double.parseDouble(t1.getText());

        if (tipoCambio != 0) {
            DecimalFormat format = new DecimalFormat("#.##");
            String total = format.format(a * tipoCambio);
            System.out.println(a / tipoCambio);
            t2.setText(total);

        } else {
            t2.setText("ERROR");
        }
    }

}
