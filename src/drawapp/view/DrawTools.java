package drawapp.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class DrawTools extends JPanel implements ActionListener {
    String[] cmd = {"eraser","oildrum","pencil","brush","spraypaint","textbox","line","rect","oval","roundrect", "filledRect", "filledOval", "filledRoundrect"};
    MainFrame mainframe;
	    
    public DrawTools( MainFrame mainframe){
	    	
	this.mainframe = mainframe;
	    	
	setPreferredSize(new Dimension(60,0));
		
	setLayout(new FlowLayout(FlowLayout.CENTER,0,5));

	ButtonGroup bg = new ButtonGroup();
		
        for (int i = 0; i < cmd.length; i++) {
            //image 
            //System.out.println(cmd[i]);
            ImageIcon i1 = new ImageIcon("img/"+cmd[i]+"-1.png");
            ImageIcon i2 = new ImageIcon("img/"+cmd[i]+"-2.png");
			
            JRadioButton jrb = new JRadioButton();
			
            jrb.setActionCommand(cmd[i]);
            jrb.setToolTipText(cmd[i]);
            jrb.setPreferredSize(new Dimension(25,25));
			
            jrb.setIcon(i1);
            jrb.setRolloverIcon(i2);
            jrb.setPressedIcon(i2);
            jrb.setSelectedIcon(i2);
			
            jrb.setMargin(new Insets(0, -2, 0, 0));

            jrb.addActionListener(this);
		    
            add(jrb);
            bg.add(jrb);	
          }
		
	}
        @Override
        public void actionPerformed(ActionEvent e) {
             mainframe.setstool(e.getActionCommand());
        }            
	
}

