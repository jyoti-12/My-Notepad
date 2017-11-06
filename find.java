package Notepad;

import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class find extends JFrame implements ActionListener
{
    int startIndex=0;
    Label l1,l2;
    TextField tf,tr;
    JButton find_btn,find_next,replace,replace_all,cancel;
    Notepad samp;
    public find(Notepad mynote)
    {
    	super("Find/Replace");
    	samp=mynote;
    	l1=new Label("Find What:");
    	l2=new Label("Replace With:");
    	tf=new TextField(40);
    	tr=new TextField(40);
    	find_btn=new JButton("Find");
    	find_next=new JButton("Find Next");
    	replace=new JButton("Replace");
    	replace_all=new JButton("Replace All");
    	cancel=new JButton("Cancel");
    	setLayout(null);
    	int label_w=80,label_h=20,tf_w=120;
    	l1.setBounds(10,10,label_w,label_h);
    	add(l1);
    	tf.setBounds(10+label_w,10,tf_w,20);
    	add(tf);
    	l2.setBounds(10,10+label_h,10+label_w,label_h);
    	add(l2);
    	tr.setBounds(10+label_w,10+label_h,10+tf_w,20);
    	add(tr);
    	find_btn.setBounds(220,10, 100,20);
    	add(find_btn);
    	find_btn.addActionListener(this);
    	find_next.setBounds(220,30,100,20);
    	add(find_next);
    	find_next.addActionListener(this);
    	replace.setBounds(220,50,100,20);
    	add(replace);
    	replace.addActionListener(this);
    	replace_all.setBounds(220,70,100,20);
    	add(replace_all);
    	replace_all.addActionListener(this);
    	cancel.setBounds(220,90,100,20);
    	add(cancel);
    	cancel.addActionListener(this);
    	int h=150,w=340;
    	setSize(w, h);
    	java.awt.Point centre=GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        setLocation(centre.x-w/2, centre.y-h/2);
        setVisible(false);
    }
    public void actionPerformed(ActionEvent event)
    {
    	if(event.getSource()==find_btn)
    		find();
    	else if (event.getSource()==find_next) {
			find_next();
		}
    	else if (event.getSource()==replace) {
    		
    		replace();
		}
    	else if (event.getSource()==replace_all) {
			replace_all();
		}
    	else if (event.getSource()==cancel) {
			this.setVisible(false);
		}
    }
    public void find()
    {
    	int select_start=samp.a1.getText().indexOf(tf.getText());
    	if (select_start==-1) {
			startIndex=0;
			JOptionPane.showMessageDialog(null, "Could not find "+tf.getText()+"!");
			return;
		}
    	if (select_start==samp.a1.getText().lastIndexOf(tf.getText())) {
			startIndex=0;
		}
    	int select_end=select_start+tf.getText().length();
    	startIndex=select_end+1;
    	samp.a1.select(select_start, select_end);
    }
    public void find_next()
    {
    	String selection=samp.a1.getSelectedText();
    	try {
			selection.equals("");
		} catch (NullPointerException e) {
			selection=tf.getText();
			try {
				selection.equals("");
			} catch (NullPointerException e2) {
				selection=JOptionPane.showInputDialog("Find:");
				tf.setText(selection);
			}
		}
    	try {
			int select_start=samp.a1.getText().indexOf(selection, startIndex);
			int select_end=select_start+selection.length();
			samp.a1.select(select_start, select_end);
			startIndex=select_end+1;
			if(select_start==samp.a1.getText().lastIndexOf(selection))
				startIndex=0;
		} catch (NullPointerException e) {
			
		}
    }
    public void replace()
    {
    	try {
			find();
			samp.a1.replaceSelection(JOptionPane.showInputDialog("Replace with :"));
		} catch (NullPointerException e) {
			System.out.println("Null Pointer Exception:"+e);
		}
    }
    public void replace_all()
    {
    	samp.a1.setText(samp.a1.getText().replaceAll(tf.getText(), JOptionPane.showInputDialog("Replace with :")));
    }
    
}
