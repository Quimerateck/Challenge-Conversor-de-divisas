package com.challenge.conversor_de_divisas;

import java.awt.CardLayout;
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

    private int conversorSeleccionado = 0;
    private JMenuBar mBar;
    private JMenu tipos, info;
    private JMenuItem divisas, temperatura, medidas, creditos, instrucciones;
    private CardLayout cardLayout;
    private JPanel panelContenedor, panelConversor, panelAcercaDe, panelDeInstrucciones;
    private JComboBox<String> unidadBaseBox, unidadObjetivoBox;
    private JTextField t1, t2;
    private JLabel l1, l2, labelSimbolo1, labelSimbolo2, labelNombre, labelCreditos;
    private JButton b1;
    private Vector<String> listaDeOpciones;
    private ArrayList<String> codigos;
    private ArrayList<String> listaDeSimbolos;
    private DecimalFormat doubleFormateado;

    public ConversorMultifuncion() {
        this.setTitle("Conversor Multifuncional");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(640, 260);
        this.setLocationRelativeTo(null);

        //se instancian los objetos
        newJComponents();

        panelContenedor.setLayout(cardLayout);
        tipos.setMnemonic('t');
        info.setMnemonic('m');
        divisas.addActionListener(this);
        temperatura.addActionListener(this);
        medidas.addActionListener(this);
        creditos.addActionListener(this);
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
                        if (x > 0.1){
                            doubleFormateado = new DecimalFormat("#.##");
                        }else{
                            doubleFormateado = new DecimalFormat("#.####");
                        }
                        break;
                    case 2:
                        x = Temperatura.getTemperatura(base, objetivo, n);
                        break;
                    case 3:
                        x = Medidas.getMedidas(base, objetivo, n);
                        doubleFormateado = new DecimalFormat("#.##");
                        break;
                    default:
                        t2.setText("Error!!");
                        x = 0;
                        break;
                    }
                    
                    String numeroEnTexto = doubleFormateado.format(x);
                    t2.setText(numeroEnTexto);
            }
            
        });

        // Se añaden los componentes a sus respectivos padres
        addJComponents();
        // Donde van todos los component.setBounds()
        setSizeAndPos();
        
        //donde se colocan los Paneles dentro del panel contenedor
        t1.setHorizontalAlignment(SwingConstants.RIGHT);
        t2.setHorizontalAlignment(SwingConstants.RIGHT);
        labelSimbolo1.setHorizontalAlignment(SwingConstants.RIGHT);
        labelSimbolo2.setHorizontalAlignment(SwingConstants.RIGHT);
        
        this.setJMenuBar(mBar);
        this.add(panelContenedor);
    }

    
    private void newJComponents(){
        mBar = new JMenuBar();
        tipos = new JMenu("Tipos");
        info = new JMenu("Más");
        l1 = new JLabel("Convertir de: ");
        l2 = new JLabel(" a ");
        labelSimbolo1 = new JLabel();
        labelSimbolo2 = new JLabel();
        labelNombre = new JLabel("Conversor Multifuncional");
        labelCreditos = new JLabel("hecho por Vladimir Segura Achi");
        b1 = new JButton("Convertir");
        divisas = new JMenuItem("Divisas", 1);
        temperatura = new JMenuItem("Temperatura", 2);
        medidas = new JMenuItem("Medidas", 3);
        creditos = new JMenuItem("Creditos");
        instrucciones = new JMenuItem("Instrucciones");
        listaDeSimbolos = new ArrayList<>();
        listaDeOpciones = new Vector<>();
        codigos = new ArrayList<>();
        t1 = new JTextField();
        t2 = new JTextField();
        cardLayout = new CardLayout();
        panelContenedor = new JPanel();
        panelConversor = new JPanel(null);
        panelAcercaDe = new JPanel(null);
        panelDeInstrucciones = new JPanel(null);
        unidadBaseBox = new JComboBox<>(listaDeOpciones);
        unidadObjetivoBox = new JComboBox<>(listaDeOpciones);
    }

    private void addJComponents(){
        tipos.add(divisas);
        tipos.add(temperatura);
        tipos.add(medidas);
        info.add(instrucciones);
        info.add(creditos);
        mBar.add(tipos);
        mBar.add(info);
        panelConversor.add(t1);
        panelConversor.add(t2);
        panelConversor.add(l1);
        panelConversor.add(l2);
        panelConversor.add(labelSimbolo1);
        panelConversor.add(labelSimbolo2);
        panelConversor.add(b1);
        panelConversor.add(unidadBaseBox);
        panelConversor.add(unidadObjetivoBox);
        panelAcercaDe.add(labelNombre);
        panelAcercaDe.add(labelCreditos);
        panelContenedor.add(panelDeInstrucciones, "tutorial");
        panelContenedor.add(panelAcercaDe, "creditos");
        panelContenedor.add(panelConversor, "convertidor");
    }

    private void setSizeAndPos(){
        unidadBaseBox.setBounds(130, 30, 170, 30);
        unidadObjetivoBox.setBounds(330, 30, 170, 30);
        l1.setBounds(50, 20, 200, 50);
        l2.setBounds(310, 20, 50, 50);
        labelSimbolo1.setBounds(100, 70, 25, 30);
        labelSimbolo2.setBounds(300, 70, 25, 30);
        t1.setBounds(130, 70, 170, 30);
        t2.setBounds(330, 70, 170, 30);
        b1.setBounds(240, 130, 150, 40);
        labelCreditos.setBounds(130, 130, 250, 40);
        labelNombre.setBounds(130, 90, 250, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();
        
        if (fuente == divisas) {
            cardLayout.show(panelContenedor, "convertidor");
            Divisas.setListas(listaDeOpciones,listaDeSimbolos,codigos);
            conversorSeleccionado = 1;
            resetConversor();
        }
        if(fuente == temperatura){
            cardLayout.show(panelContenedor, "convertidor");
            Temperatura.setListas(listaDeOpciones,listaDeSimbolos,codigos);
            conversorSeleccionado = 2;
            resetConversor();
        }
        if(fuente == medidas){
            cardLayout.show(panelContenedor, "convertidor");
            Medidas.setListas(listaDeOpciones,listaDeSimbolos,codigos);
            conversorSeleccionado = 3;
            resetConversor();
        }
        if(fuente == creditos){
            cardLayout.show(panelContenedor, "creditos");
        }
        if(fuente == instrucciones){
            cardLayout.show(panelContenedor, "tutorial");
        }
        if(fuente == unidadBaseBox){
            labelSimbolo1.setText(listaDeSimbolos.get(unidadBaseBox.getSelectedIndex()));
        }
        if(fuente == unidadObjetivoBox){
            labelSimbolo2.setText(listaDeSimbolos.get(unidadObjetivoBox.getSelectedIndex()));
        }
        
    }

    private void resetConversor(){
        unidadBaseBox.setSelectedIndex(0);
        unidadObjetivoBox.setSelectedIndex(0);
        t1.setText("0");
        t2.setText("0");
    }
}
