package drawapp.view;

/*
 *    Main Frame
 * 
 */

import drawapp.listener.DrawPanelListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

import javax.swing.UIManager;

public class MainFrame extends JFrame {

    //border and content color
    private Color bkcolor = Color.BLACK;
    private Color tccolor = Color.BLACK;

    //inital tool
    private String stool = "pencil";

    // initial panels
    public DrawArea drawpanel;
    public DrawTools toolspanel;
    public DrawMenu drawmenu;
    //public ColorsPanel colorspanel;

    public void ShowFrame() {

        setTitle("DrawingApplication");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Font font = new Font("Dialog", Font.PLAIN, 12);

        //font
        UIManager.put("Button.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);

        //menu
        drawmenu = new DrawMenu(this);

        //draw area
        drawpanel = new DrawArea();
        add(drawpanel, BorderLayout.CENTER);

        //draw tools
        toolspanel = new DrawTools(this);
        add(toolspanel, BorderLayout.WEST);

        setVisible(true);

        //add listeners
        addactionListener();
    }

    //get/set border color
    public Color getbkcolor() {
        return bkcolor;
    }

    public void setbkcolor(Color color) {
        bkcolor = color;
    }

    //get/set content color
    public Color gettccolor() {
        return tccolor;
    }

    public void settccolor(Color color) {
        tccolor = color;
    }

    //get/set tool
    public String getstool() {
        return stool;
    }

    public void setstool(String actioncommand) {
        stool = actioncommand;
    }

    //
    private void addactionListener() {

        //get the graphics of the drawpanel
        Graphics graphics = drawpanel.getGraphics();
        DrawPanelListener dl = new DrawPanelListener(graphics, this);

        //add mouse listners
        drawpanel.addMouseMotionListener(dl);
        drawpanel.addMouseListener(dl);
    }
}