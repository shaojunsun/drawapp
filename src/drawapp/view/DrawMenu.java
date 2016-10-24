/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawapp.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * @author shsun
 */
public class DrawMenu implements ActionListener {

    MainFrame mainframe;

    private JMenu jmf, jme, jmv, jmi, jmc, jmh; //first level menus
    private JMenuItem jmf0, jmf1, jmf2, jmf3; //file operation
    private JMenuItem jme0, jme1, jme2, jme3, jme4; //edit menu
    private JMenuItem jmv0; //view menu
    private JMenuItem jmi0; //image menu
    private JMenuItem jmc0; //color menu
    private JMenuItem jmh0, jmh1; //help menu

    private Color color = Color.black;

    public DrawMenu(MainFrame mainframe) {

        this.mainframe = mainframe;
        JMenuBar jmb = new JMenuBar();

        //first level menu
        jmf = new JMenu("File");  //file menu
        jme = new JMenu("Edit");  //edit menu
        jmv = new JMenu("View");  //view menu
        jmi = new JMenu("Image"); //image menu
        jmc = new JMenu("Color"); //color menu
        jmh = new JMenu("Help");  //help menu

        // childs of file menu

        jmf0 = new JMenuItem("New");
        jmf1 = new JMenuItem("Open");
        jmf2 = new JMenuItem("Save");
        jmf3 = new JMenuItem("Quit");

        jmf0.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        jmf1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        jmf2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        jmf3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        jmf0.addActionListener(this);
        jmf1.addActionListener(this);
        jmf2.addActionListener(this);
        jmf3.addActionListener(this);
        //childs of edit menu
        jme0 = new JMenuItem("Undo");
        jme0.setEnabled(false);
        jme1 = new JMenuItem("Repeat");
        jme2 = new JMenuItem("Cut");
        jme2.setEnabled(false);
        jme3 = new JMenuItem("Copy");
        jme4 = new JMenuItem("Paster");
        jme4.setEnabled(false);


        // childs of view menu
        jmv0 = new JMenuItem("Tools");
        // childs of image menu
        jmi0 = new JMenuItem("Rotate");
        // childs of color menu
        jmc0 = new JMenuItem("Color Pallet");
        jmc0.addActionListener(this);
        //jmc0.addActionListener(this);
        // helps
        jmh0 = new JMenuItem("Help");
        jmh1 = new JMenuItem("About");
        //add childs to its parents
        jmf.add(jmf0);
        jmf.add(jmf1);
        jmf.add(jmf2);
        jmf.add(jmf3);
        jme.add(jme0);
        jme.add(jme1);
        jme.add(jme2);
        jme.add(jme3);
        jme.add(jme4);
        jmv.add(jmv0);
        jmi.add(jmi0);
        jmc.add(jmc0);
        jmh.add(jmh0);
        jmh.add(jmh1);

        //add to menu
        jmb.add(jmf);
        jmb.add(jme);
        jmb.add(jmi);
        jmb.add(jmc);
        jmb.add(jmh);
        mainframe.setJMenuBar(jmb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jmc0) {
            Color c = JColorChooser.showDialog(mainframe, "choose color", mainframe.getbkcolor());
            mainframe.setbkcolor(c);
            mainframe.settccolor(c);
        }

        if (e.getSource() == jmf0) {
            System.out.println("++++++");
            //Color color = Color.WHITE;
            DrawArea drawpanel = new DrawArea();
            drawpanel.setBackground(Color.WHITE);
        }
        if (e.getSource() == jmf1) {


        }
        if (e.getSource() == jmf2) {


        }

        if (e.getSource() == jmf3) {


        }

    }
}
