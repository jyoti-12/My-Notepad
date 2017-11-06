package Notepad;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.javafx.scene.paint.GradientUtils.Point;

public class about 
{
    static JFrame window=new JFrame("About Notepad");
    Notepad sample;
    JButton b;
    public  about(Notepad ref)
    {
    	sample=ref;
    	JPanel p1=new JPanel();
    	p1.setLayout(new FlowLayout());
    	String about="<html>"+"<body>"+"Created By.... <br>"+"Jyotirmay Sahoo<br>"+"LIT<br>"+"Mechanical Engineering<br>"+"IIT KHARAGPUR<br><br><br>"
    	+"Contact: 9556523727<br>"+"E-Mail: jyotirmaysahoo2016@gmail.com<br>"+"Version: 1.8 jdk<br>"+"Built Date: "+new java.util.Date()
    	+"<br><br><br>"+"</body"+"</html";
    	JLabel l=new JLabel();
    	l.setText(about);
    	p1.add(l);
    	int h=350,w=340;
    	window.setSize(w,h);
    	java.awt.Point centre=GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    	window.setLocation(centre.x-w/2,centre.y/2);
    	window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	window.setVisible(false);
    	window.add(p1);
    	
    }
}
