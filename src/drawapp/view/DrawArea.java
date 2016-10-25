/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawapp.view;

/**
 *
 * @author shsun
 */
import java.awt.*;
import javax.swing.*;

public class DrawArea extends JPanel {  
    
    public DrawArea() {

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 600));
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }
}
