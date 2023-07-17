package com.challenge.conversor_de_divisas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * MenuConversor
 */
public class ConversorMultifuncion extends JFrame implements ActionListener{

    int conversorSeleccionado = 0;
    JTextField t1, t2;
    JPanel fondo;
    JMenuItem divisas, temperatura, medidas, acercaDe;
    JComboBox<String> unidadBaseBox, unidadObjetivoBox;
    JLabel l1, l2, l3, l4;
    Vector<String> listaDeOpciones;
    ArrayList<String> codigos;
    ArrayList<String> listaDeSimbolos;
    DecimalFormat doubleFormateado;

    public ConversorMultifuncion() {
        setTitle("Conversor Multifuncional");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 260);
        setLocationRelativeTo(null);

        JMenuBar mBar = new JMenuBar();
        JMenu tipos = new JMenu("Tipos");
        JMenu mas = new JMenu("Más");
        l1 = new JLabel("Convertir de: ");
        l2 = new JLabel(" a ");
        l3 = new JLabel();
        l4 = new JLabel();
        JButton b1 = new JButton("Convertir");
        divisas = new JMenuItem("Divisas", 1);
        temperatura = new JMenuItem("Temperatura", 2);
        medidas = new JMenuItem("Medidas", 3);
        acercaDe = new JMenuItem("Acerca De");
        listaDeSimbolos = new ArrayList<>();
        listaDeOpciones = new Vector<>();
        codigos = new ArrayList<>();
        t1 = new JTextField();
        t2 = new JTextField();
        fondo = new JPanel(null);
        
        fondo.setVisible(false);
        tipos.setMnemonic('t');
        mas.setMnemonic('m');

        unidadBaseBox = new JComboBox<>(listaDeOpciones);
        unidadObjetivoBox = new JComboBox<>(listaDeOpciones);

        divisas.addActionListener(this);
        temperatura.addActionListener(this);
        medidas.addActionListener(this);
        acercaDe.addActionListener(this);
        unidadBaseBox.addActionListener(this);
        unidadObjetivoBox.addActionListener(this);
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double n = Double.parseDouble( t1.getText());
                double x;
                String base = codigos.get((int)unidadBaseBox.getSelectedIndex());
                String objetivo = codigos.get((int)unidadObjetivoBox.getSelectedIndex());
                
                switch (conversorSeleccionado) {
                    case 1:
                        x = Divisas.getCambioActual(base, objetivo, n);
                        break;
                    case 2:
                        x = Temperatura.getTemperatura(base, objetivo, n);
                        break;
                    case 3:
                        x = Medidas.getMedidas(base, objetivo, n);
                        break;
                    default:
                        t2.setText("Error!!");
                        x = 0;
                        break;
                    }
                    if (x > 0.1){
                        doubleFormateado = new DecimalFormat("#.##");
                    }else{
                        doubleFormateado = new DecimalFormat("#.####");
                    }
                    String numeroEnTexto = doubleFormateado.format(x);
                    t2.setText(numeroEnTexto);
            }
            
        });
        
        tipos.add(divisas);
        tipos.add(temperatura);
        tipos.add(medidas);
        mas.add(acercaDe);
        mBar.add(tipos);
        mBar.add(mas);
        fondo.add(t1);
        fondo.add(t2);
        fondo.add(l1);
        fondo.add(l2);
        fondo.add(l3);
        fondo.add(l4);
        fondo.add(b1);
        fondo.add(unidadBaseBox);
        fondo.add(unidadObjetivoBox);

        unidadBaseBox.setBounds(130, 30, 170, 30);
        unidadObjetivoBox.setBounds(330, 30, 170, 30);
        l1.setBounds(50, 20, 200, 50);
        l2.setBounds(310, 20, 50, 50);
        l3.setBounds(100, 70, 25, 30);
        l4.setBounds(300, 70, 25, 30);
        t1.setBounds(130, 70, 170, 30);
        t2.setBounds(330, 70, 170, 30);
        b1.setBounds(240, 130, 150, 40);

        t1.setHorizontalAlignment(SwingConstants.RIGHT);
        t2.setHorizontalAlignment(SwingConstants.RIGHT);
        l3.setHorizontalAlignment(SwingConstants.RIGHT);
        l4.setHorizontalAlignment(SwingConstants.RIGHT);

        this.setJMenuBar(mBar);
        this.add(fondo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();
        
        if (fuente == divisas) {
            fondo.setVisible(true);
            Divisas.setListas(listaDeOpciones,listaDeSimbolos,codigos);
            conversorSeleccionado = 1;
            resetConversor();
        }
        if(fuente == temperatura){
            fondo.setVisible(true);
            Temperatura.setListas(listaDeOpciones,listaDeSimbolos,codigos);
            conversorSeleccionado = 2;
            resetConversor();
        }
        if(fuente == medidas){
            fondo.setVisible(true);
            Medidas.setListas(listaDeOpciones,listaDeSimbolos,codigos);
            conversorSeleccionado = 3;
            resetConversor();
        }
        if(fuente == unidadBaseBox){
            l3.setText(listaDeSimbolos.get(unidadBaseBox.getSelectedIndex()));
            System.out.println(listaDeSimbolos.get(unidadBaseBox.getSelectedIndex())); 
        }
        if(fuente == unidadObjetivoBox){
            l4.setText(listaDeSimbolos.get(unidadObjetivoBox.getSelectedIndex()));
            System.out.println(listaDeSimbolos.get(unidadObjetivoBox.getSelectedIndex()));
        }
        
    }

    private void resetConversor(){
        unidadBaseBox.setSelectedIndex(0);
        unidadObjetivoBox.setSelectedIndex(0);
        t1.setText("0");
        t2.setText("0");
    }

    public static void main(String[] args) {
        new ConversorMultifuncion().setVisible(true);
    }
}
