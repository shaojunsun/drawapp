/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawapp.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/**
 * @author shsun
 */
public class DrawMenu implements ActionListener {

    MainFrame mainframe;

    private JMenu fileMenu, editMenu, viewMenu, imageMenu, colorMenu, helpMenu; //first level menus
    private JMenuItem fileMenuNewItem, fileMenuOpenItem, fileMenuSaveItem, fileMenuQuitItem; //file operation
    private JMenuItem editMenuUndoItem, editMenuRedoItem, editMenuCutItem, editMenuCopyItem, editMenuPasteItem; //edit menu
    private JMenuItem jmv0; //view menu
    private JMenuItem jmi0; //image menu
    private JMenuItem jmc0; //color menu
    private JMenuItem jmh0, jmh1; //help menu

    private static final String FILE_EXT = ".png";

    private Color color = Color.black;

    public DrawMenu(MainFrame mainframe) {

        this.mainframe = mainframe;
        JMenuBar jmb = new JMenuBar();

        //first level menu
        fileMenu = new JMenu("File");  //file menu
        editMenu = new JMenu("Edit");  //edit menu
        viewMenu = new JMenu("View");  //view menu
        imageMenu = new JMenu("Image"); //image menu
        colorMenu = new JMenu("Color"); //color menu
        helpMenu = new JMenu("Help");  //help menu

        // children of file menu

        fileMenuNewItem = new JMenuItem("New");
        fileMenuOpenItem = new JMenuItem("Open");
        fileMenuSaveItem = new JMenuItem("Save");
        fileMenuQuitItem = new JMenuItem("Quit");

        fileMenuNewItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        fileMenuOpenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        fileMenuSaveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        fileMenuQuitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        fileMenuNewItem.addActionListener(this);
        fileMenuOpenItem.addActionListener(this);
        fileMenuSaveItem.addActionListener(this);
        fileMenuQuitItem.addActionListener(this);
        //childs of edit menu
        editMenuUndoItem = new JMenuItem("Undo");
        editMenuUndoItem.setEnabled(false);
        editMenuRedoItem = new JMenuItem("Redo");
        editMenuCutItem = new JMenuItem("Cut");
        editMenuCutItem.setEnabled(false);
        editMenuCopyItem = new JMenuItem("Copy");
        editMenuPasteItem = new JMenuItem("Paste");
        editMenuPasteItem.setEnabled(false);


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
        fileMenu.add(fileMenuNewItem);
        fileMenu.add(fileMenuOpenItem);
        fileMenu.add(fileMenuSaveItem);
        fileMenu.add(fileMenuQuitItem);
        editMenu.add(editMenuUndoItem);
        editMenu.add(editMenuRedoItem);
        editMenu.add(editMenuCutItem);
        editMenu.add(editMenuCopyItem);
        editMenu.add(editMenuPasteItem);
        viewMenu.add(jmv0);
        imageMenu.add(jmi0);
        colorMenu.add(jmc0);
        helpMenu.add(jmh0);
        helpMenu.add(jmh1);

        //add to menu
        jmb.add(fileMenu);
        jmb.add(editMenu);
        jmb.add(imageMenu);
        jmb.add(colorMenu);
        jmb.add(helpMenu);
        mainframe.setJMenuBar(jmb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jmc0) {
            Color c = JColorChooser.showDialog(mainframe, "choose color", mainframe.getbkcolor());
            mainframe.setbkcolor(c);
            mainframe.settccolor(c);
        }

        if (e.getSource() == fileMenuNewItem) {

            Graphics gr = mainframe.drawpanel.getGraphics();
            Color curColor = gr.getColor();
            gr.setColor(Color.white);
            gr.fillRect(0, 0, mainframe.drawpanel.getWidth(), mainframe.drawpanel.getHeight());
            gr.setColor(curColor);
        }

        if (e.getSource() == fileMenuOpenItem) {

            final JFileChooser fc = getFileChooser();
            int returnVal = fc.showOpenDialog(mainframe);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

                System.out.println(file.getAbsolutePath());

                try {
                    BufferedImage awtImage = ImageIO.read(file);
                    mainframe.drawpanel.paint(awtImage.getGraphics());

                } catch (IOException e1) {

                    throw new RuntimeException(e1);
                }
            }
        }
        if (e.getSource() == fileMenuSaveItem) {

            final JFileChooser fc = getFileChooser();
            int returnVal = fc.showOpenDialog(mainframe);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

                String fileName = file.getAbsolutePath();
                if (fileName.length() < 4 || !fileName.substring(fileName.length() - 4).toLowerCase().equals(FILE_EXT)) {
                    fileName += FILE_EXT;
                }
                System.out.println(fileName);

                // get bit map
                BufferedImage awtImage = new BufferedImage(mainframe.drawpanel.getWidth(), mainframe.drawpanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = awtImage.createGraphics();
                mainframe.drawpanel.paint(g2);

                // write to the file
                try {
                    ImageIO.write(awtImage, "png", new File(fileName));

                } catch (IOException e1) {

                    throw new RuntimeException(e1);
                }
            }
        }

        if (e.getSource() == fileMenuQuitItem) {

            mainframe.dispose();
        }

    }

    private JFileChooser getFileChooser() {

        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                String fileName = f.getName();
                return fileName.substring(fileName.length() - 4).toLowerCase().equals(FILE_EXT);
            }

            @Override
            public String getDescription() {
                return "*" + FILE_EXT;
            }
        });

        return fc;
    }
}
