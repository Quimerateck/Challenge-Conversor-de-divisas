package com.challenge.conversor_de_divisas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CrearVentana {

    public static void ventana(){
        JFrame f = new JFrame("Conversor de divisas");
        JLabel l1 = new JLabel("Convertir de: ");
        JLabel l2 = new JLabel(" a ");
        JTextField t1 = new JTextField("0");
        JTextField t2 = new JTextField("0");
        JButton b1 = new JButton("Convertir");

        Vector<String> tipoMoneda = new Vector<>();
        tipoMoneda.add("USD (Dollar Estadounidense)");
        tipoMoneda.add("EUR (Euro)");
        tipoMoneda.add("GBP (Libra Esterlina)");
        tipoMoneda.add("JPY (Yen Japones)");
        tipoMoneda.add("KRW (Won SurCoreano)");
        tipoMoneda.add("CRC (Colon Costarrisense)");

        JComboBox<String> cb1 = new JComboBox<>(tipoMoneda);
        JComboBox<String> cb2 = new JComboBox<>(tipoMoneda);

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String divisa1 = Convertir.getDivisas(cb1);
                String divisa2 = Convertir.getDivisas(cb2);
                double valor = Convertir.getCambioActual(divisa1, divisa2);
                Convertir.calcularTipoDeCambio(t1, t2,valor);
            }
        });
        

        l1.setBounds(50, 20, 200, 50);
        l2.setBounds(310,20,50,50);
        t1.setBounds(130, 70, 170, 30);
        t2.setBounds(330, 70, 170, 30);
        b1.setBounds(260, 110, 100, 30);
        cb1.setBounds(130, 30, 170, 30);
        cb2.setBounds(330, 30, 170, 30);

        f.add(l1);
        f.add(l2);
        f.add(t1);
        f.add(t2);
        f.add(b1);
        f.add(cb1);
        f.add(cb2);
        f.setSize(600, 200);
        f.setLayout(null);
        f.setVisible(true);
    }

}
