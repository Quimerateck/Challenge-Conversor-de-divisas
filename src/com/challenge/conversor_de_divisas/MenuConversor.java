package com.challenge.conversor_de_divisas;

import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 * MenuConversor
 */
public class MenuConversor extends JFrame implements ActionListener{

    JTextField t1, t2;
    JMenuItem divisas, temperatura, medidas, acercaDe;

    public MenuConversor() {
        setTitle("Conversor Multifuncional");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JMenuBar mBar = new JMenuBar();
        JMenu tipos = new JMenu("Tipos");
        JMenu mas = new JMenu("Más");
        tipos.setMnemonic('T');
        /*
        tipos.add(divisas);
        tipos.add(temperatura);
        tipos.add(medidas);
        mas.add(acercaDe);
         */
        mBar.add(tipos);
        mBar.add(mas);

        this.setJMenuBar(mBar);
    }



    public static void main(String[] args) {
        MenuConversor menu = new MenuConversor();
        menu.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("x");
    }
}
