package Notepad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.stage.FileChooser;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jdk.nashorn.internal.runtime.FindProperty;
public class Notepad extends JFrame implements ActionListener,Printable
{
   JMenuBar mb;
   JMenu file,edit,format,view,help;
   JMenuItem New,Open,Save,Saveas,pagesetup,print,exit;
   JMenuItem undo,cut,copy,paste,delete,find,findnext,replace,g,selectall,time;
   JMenuItem wordwrap,font;
   JMenuItem statusbar,aboutnotepad,viewhelp;
   JTextArea a1;
   String c,path="";
   static about abt;
   static font_chooser fc;
   static find finder;
   public Notepad()
   {
	   setTitle("Notepad");
	   setIconImage(Toolkit.getDefaultToolkit().getImage("b.png"));
	   setSize(500, 500);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
       a1=new JTextArea(10,10);
       a1.setBackground(new Color(180, 180, 180));
       add(new JScrollPane(a1));
       mb=new JMenuBar();
       setJMenuBar(mb);
       file=new JMenu("File");
       edit=new JMenu("Edit");
       format=new JMenu("Format");
       view=new JMenu("View");
       help=new JMenu("Help");
       New=new JMenuItem("New");
       Open=new JMenuItem("Open");
       Save=new JMenuItem("Save");
       Saveas=new JMenuItem("Save As");
       pagesetup=new JMenuItem("Page Setup");
       print=new JMenuItem("Print");
       exit=new JMenuItem("Exit");
       undo=new JMenuItem("Undo");
       cut=new JMenuItem("Cut");
       copy=new JMenuItem("Copy");
       paste=new JMenuItem("Paste");
       delete=new JMenuItem("Delete");
       find=new JMenuItem("Find");
       findnext=new JMenuItem("Find Next");
       replace=new JMenuItem("Replace");
       g=new JMenuItem("Goto");
       selectall=new JMenuItem("Select All");
       time=new JMenuItem("Time");
       wordwrap=new JMenuItem("Word Wrap");
       font=new JMenuItem("Font");
       statusbar=new JMenuItem("Status Bar");
       aboutnotepad=new JMenuItem("About Notepad");
       viewhelp=new JMenuItem("View Help");
       file.add(New);
       file.add(Open);
       file.add(Save);
       file.add(Saveas);
       file.addSeparator();
       file.add(pagesetup);
       file.add(print);
       file.addSeparator();
       file.add(exit);
       edit.add(undo);
       edit.addSeparator();
       edit.add(cut);
       edit.add(copy);
       edit.add(paste);
       edit.add(delete);
       edit.addSeparator();
       edit.add(find);
       edit.add(findnext);
       edit.add(replace);
       edit.add(g);
       edit.addSeparator();
       edit.add(selectall);
       edit.add(time);
       format.add(wordwrap);
       format.add(font);
       view.add(statusbar);
       help.add(viewhelp);
       help.addSeparator();
       help.add(aboutnotepad);
       mb.add(file);
       mb.add(edit);
       mb.add(help);
       mb.add(format);
       mb.add(view);
       New.addActionListener(this);
       Open.addActionListener(this);
       Save.addActionListener(this);
       Saveas.addActionListener(this);
       pagesetup.addActionListener(this);
       print.addActionListener(this);
       exit.addActionListener(this);
       copy.addActionListener(this);
       paste.addActionListener(this);
       cut.addActionListener(this);
       undo.addActionListener(this);
       delete.addActionListener(this);
       find.addActionListener(this);
       findnext.addActionListener(this);
       replace.addActionListener(this);
       selectall.addActionListener(this);
       wordwrap.addActionListener(this);
       aboutnotepad.addActionListener(this);
       font.addActionListener(this);
       abt=new about(this);
       finder=new find(this);
       finder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       
   }
   public void actionPerformed(ActionEvent event)
   {
	   if(event.getSource()==New)
		   file_new();
	   if(event.getSource()==Open)
		   file_open();
	   if(event.getSource()==Save)
		   file_save();
	   if(event.getSource()==Saveas)
		   file_saveas();
	   if(event.getSource()==print)
		   file_print();
	   if(event.getSource()==wordwrap)
		   format_wordwrap();
	   if(event.getSource()==selectall)
		   edit_selectall();
	   if(event.getSource()==time)
		   edit_timedate();
	   if(event.getSource()==delete)
		   edit_delete();
	   if(event.getSource()==paste)
		   edit_paste();
	   if(event.getSource()==copy)
		   edit_copy();
	   if(event.getSource()==cut)
		   edit_cut();
	   if(event.getSource()==exit)
		   file_close();
	   if(event.getSource()==aboutnotepad)  
		   help_about();
	   if(event.getSource()==pagesetup)
		   pagesetup();
	   if(event.getSource()==find)
		   edit_find();
	   if(event.getSource()==findnext)
		   edit_find_next();
	   if(event.getSource()==font)
		   format_font();
	   
   }
   private void format_font() {
	fc=new font_chooser(this);
	
	
}
public void file_new()
   {
	   if (a1.getText().equals("") || a1.getText().equals(c)) 
	   {
		   a1.setText("");
		   c="";
		   path="";
		   setTitle("Untitled-Notepad");
		   
	    }
	   else {
		int b=JOptionPane.showConfirmDialog(null, "The text has been changed \n Do you want to save the changes?");
		if (b==0) 
		{
			file_save();
		}
		else if (b==1) {
			a1.setText("");
			path="";
			setTitle("Untitled-Notepad");
		}
		else if (b==2) {
			return;
		}
	}
}
   public void file_save()
   {
	   System.out.println("Hi");
	   if(path.equals(""))
	   {  file_saveas();
	       return;
	   }
	   try {
		FileWriter f1=new FileWriter(path);
		f1.write(a1.getText());
		c=a1.getText();
		f1.close();
	} catch (IOException e) {
		JOptionPane.showMessageDialog(this, "Failed to save the file","Error",JOptionPane.ERROR_MESSAGE);
	}
	   }
   public void file_saveas()
   {
	   JFileChooser f2=new JFileChooser();
	   f2.setFileSelectionMode(JFileChooser.FILES_ONLY);
	   int r=f2.showSaveDialog(this);
	   if(r==JFileChooser.CANCEL_OPTION)
		   return;
	   File myfile=f2.getSelectedFile();
	   System.out.println(myfile);
	   if (myfile==null || myfile.getName().equals("")) {
		JOptionPane.showMessageDialog(this, "Please enter a file name !","Error",JOptionPane.ERROR_MESSAGE);
		return;
	}
	   if (myfile.exists()) {
		r=JOptionPane.showConfirmDialog(this, "A file with same name already exists ! \n Do you want to overwrite?");
				if(r!=0)
					return;
	}
	   try {
		FileWriter f3=new FileWriter(myfile);
		f3.write(a1.getText());
		c=a1.getText();
		setTitle(myfile.getName()+"-Notepad");
		f3.close();
		
	} catch (IOException e) {
		JOptionPane.showMessageDialog(this, "Failed to save the file","Error",JOptionPane.ERROR_MESSAGE);
	}
   }
   public void file_open()
   {
	   JFileChooser f2=new JFileChooser();
	   f2.setFileSelectionMode(JFileChooser.FILES_ONLY);
	   int r=f2.showOpenDialog(this);
	   if(r==f2.CANCEL_OPTION)
		   return;
	   File myfile=f2.getSelectedFile();
	   if(myfile.getName().equals("") || myfile==null){
		   JOptionPane.showMessageDialog(this, "Select a file!","Error",JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   try {
		BufferedReader input=new BufferedReader(new FileReader(myfile));
		StringBuffer s=new StringBuffer();
		String line;
		while ((line=input.readLine())!=null) {
			s.append(line+"\n");
		}
		a1.setText(s.toString());
		c=a1.getText();
		path=myfile.toString();
		setTitle(myfile.getName()+"-Notepad");
	} catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(null, "File not found"+e);
	}
	   catch (IOException e) {
		JOptionPane.showMessageDialog(null,"IO ERROR :"+e);
	}
   }
   public void pagesetup()
   {
	   PrinterJob job=PrinterJob.getPrinterJob();
	   PrintRequestAttributeSet aset=new HashPrintRequestAttributeSet();
	   PageFormat pf=job.pageDialog(aset);
	   Printable obj = null;
	job.setPrintable(obj, pf);
	boolean ok=job.printDialog(aset);
	if(ok)
	{
		try
		{
			job.print(aset);
		}
		catch(PrinterException e)
		{
			
		}
	}
	   
   }
   public int print(Graphics g,PageFormat pf,int page)throws PrinterException
   {
	   if(page>0)
	   {
		   return NO_SUCH_PAGE;
	   }
	   Graphics2D g2d=(Graphics2D)g;
	   g2d.translate(pf.getImageableX(), pf.getImageableY());
	   g.drawString(a1.getText(), 100,100);
	   return PAGE_EXISTS;
   }
   public void file_print()
   {
	   PrinterJob printer=PrinterJob.getPrinterJob();
	   HashPrintRequestAttributeSet printatr=new HashPrintRequestAttributeSet();
	   Printable obj = null;
	printer.setPrintable(obj);
	if (printer.printDialog(printatr)) {
		try {
			printer.print(printatr);
		} catch (PrinterException e) {
			JOptionPane.showMessageDialog(this,"Failed to print the file :"+e,"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
   }
   public void file_close()
   {
	   if (a1.getText().equals(c)) {
		a1.setText("");
		path="";
		System.exit(0);
	}
	   else if (a1.getText().equals("") && c==null) {
		a1.setText("");
		path="";
		System.exit(0);
	}
	   else {
		int b=JOptionPane.showConfirmDialog(null,"The text has been changed \n Do you want to save the changes?");
		if(b==0)
			file_save();
		else if (b==1) {
			a1.setText("");
			path="";
			setTitle("Untitled-Notepad");
		}
		else if(b==2)
			return;
	}
   }
   public void edit_cut()
   {	   
	   a1.cut();
   }
   public void edit_copy()
   {
	   a1.copy();
	   
   }
   public void edit_paste()
   {
	   a1.paste();
   }
   public void edit_delete()
   {
	   String t=a1.getText();
	   a1.setText(t.substring(0,a1.getSelectionStart())+t.substring(a1.getSelectionEnd()));
   }
   public void edit_selectall()
   {
	   a1.selectAll();
   }
   public void edit_timedate()
   {
	   try {
		int start=a1.getSelectionStart();
		int end=a1.getSelectionEnd();
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy h:m:s");
		String now=sdf.format(cal.getTime());
		String temp1=a1.getText().substring(0,start);
		String temp2=a1.getText().substring(end);
		a1.setText(temp1+""+now+""+temp2);
		a1.select(start+1, start+1+now.length());
	} catch (NullPointerException e) {
	    	
	}
   }
   public void format_wordwrap()
   {
	   if(a1.getLineWrap()==false)
		   a1.setLineWrap(true);
	   else {
		a1.setLineWrap(false);
	}
   }
   public void help_about()
   {
	   abt.window.setVisible(true);
   }
   
   public void edit_find()
   {
	   finder.setVisible(true);
   }
   public void edit_find_next()
   {
	   finder.find_next();
	   
   }
   public void edit_replace()
   {
	   finder.setVisible(true);
   }
   static Notepad ob;
   public static void main(String[] args) throws NullPointerException{
	ob=new Notepad();
	ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ob.setVisible(true);
}
   
}
