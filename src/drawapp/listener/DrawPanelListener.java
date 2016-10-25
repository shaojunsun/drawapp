package drawapp.listener;

import drawapp.view.MainFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawPanelListener implements MouseListener, MouseMotionListener {

    Graphics graphics;
    MainFrame mainframe;

    public static boolean flag;
    int x1, x2, x3, x4;
    int y1, y2, y3, y4;
    int qbx1 = -1;
    int qby1 = -1;

    public DrawPanelListener(Graphics graphics, MainFrame mainframe) {
        this.graphics = graphics;
        this.mainframe = mainframe;
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        String stool = mainframe.getstool();
        Color color = mainframe.getbkcolor();
        switch (stool) {
            case "oildrum":
                mainframe.drawpanel.setBackground(color);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {

        String stool = mainframe.getstool();
        Color color = mainframe.getbkcolor();
        int x = arg0.getX();
        int y = arg0.getY();

        switch (stool) {
            case "eraser":
                graphics.setColor(Color.WHITE);
                graphics.fillRect(x - 5, y - 5, 20, 20);
                break;
            case "pencil":
                graphics.setColor(color);
                if (qbx1 == -1 && qby1 == -1) {
                    graphics.drawLine(x, y, x, y);
                } else {
                    graphics.drawLine(x, y, qbx1, qby1);
                }
                qbx1 = x;
                qby1 = y;
                break;
            case "spraypaint":

            case "brush":
                x2 = arg0.getX();
                y2 = arg0.getY();
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.setColor(color);
                g2d.setStroke(new BasicStroke(8));
                g2d.drawLine(x1, y1, x2, y2);
                x1 = x2;
                y1 = y2;
                g2d.setStroke(new BasicStroke(1));
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        String stool = mainframe.getstool();
        Color color = mainframe.getbkcolor();
        int x = e.getX();
        int y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String stool = mainframe.getstool();
        Color color = mainframe.getbkcolor();
        Color color1 = mainframe.gettccolor();
        switch (stool) {
            case "line":
                x2 = e.getX();
                y2 = e.getY();
                graphics.setColor(color);
                graphics.drawLine(x1, y1, x2, y2);
                break;
            case "rect":
                x2 = e.getX();
                y2 = e.getY();
                graphics.setColor(color);
                graphics.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
                graphics.setColor(color1);
                break;
            case "filledRect":
                x2 = e.getX();
                y2 = e.getY();
                graphics.setColor(color);
                graphics.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
                graphics.setColor(color1);
                break;
            case "oval":
                x2 = e.getX();
                y2 = e.getY();
                ((Graphics2D) graphics).setStroke(new BasicStroke(1));
                graphics.setColor(color);
                graphics.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
                graphics.setColor(color1);
                break;
            case "filledOval":
                x2 = e.getX();
                y2 = e.getY();
                ((Graphics2D) graphics).setStroke(new BasicStroke(1));
                graphics.setColor(color);
                graphics.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
                graphics.setColor(color1);
                break;
            case "roundrect":
                x2 = e.getX();
                y2 = e.getY();
                graphics.setColor(color);
                graphics.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2), 10, 10);
                graphics.setColor(color1);
                break;
            case "filledRoundrect":
                x2 = e.getX();
                y2 = e.getY();
                graphics.setColor(color);
                graphics.fillRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2), 10, 10);
                graphics.setColor(color1);
                break;
            case "pencil":
                qbx1 = -1;
                qby1 = -1;
                break;
            default:
                break;
        }
    }
}


