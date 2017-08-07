import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


class Notepad extends JFrame{
	JTextArea ta;
	JMenuBar mb;
	JMenu fileMenu;
	JMenu editMenu;
	JMenu aboutMenu;
	JMenu formate;

	JMenuItem openItem;
	JMenuItem newItem;
	JMenuItem saveItem;
	JMenuItem exitItem;

	JMenuItem copy;
	JMenuItem paste;
	JMenuItem cut;
	JMenuItem delete;
	JMenuItem selectall;

	JMenuItem about;
	JMenuItem color;
	JMenuItem font;
	String clipText =new String("");


	String cpd;

	

	public Notepad(String title){
	
		super(title);
		ta = new JTextArea();
		mb = new JMenuBar();
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		aboutMenu = new JMenu("About");
		formate = new JMenu("Format");

		openItem = new JMenuItem("Open",'o');
		newItem = new JMenuItem("New",'n');
		saveItem = new JMenuItem("Save",'s');
		exitItem = new JMenuItem("Exit",'q');

		copy = new JMenuItem("Copy",'c');
		paste = new JMenuItem("Paste",'v');
		cut = new JMenuItem("Cut",'x');
		delete=new JMenuItem("Delete",'d');
		selectall = new JMenuItem("Select all",'a');

		about= new JMenuItem("About Us");
		color=new JMenuItem("Color");
		font = new JMenuItem("Font");
		
		setJMenuBar(mb);
		add(ta);
		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(formate);
		mb.add(aboutMenu);

	
	
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);	

		editMenu.add(copy);
		editMenu.add(paste);
		editMenu.add(cut);
		editMenu.add(delete);
		editMenu.add(selectall);

		aboutMenu.add(about);

		formate.add(color);
		formate.add(font);
		

		openItem.addActionListener(new MyOpenListener());
		newItem.addActionListener(new MyNewListener());
		saveItem.addActionListener(new MySaveListener());
		exitItem.addActionListener(new MyExitListener());

		copy.addActionListener(new MyCopyListener());
		selectall.addActionListener(new MySelectListener());
		paste.addActionListener(new MyPasteListener());
		cut.addActionListener(new MyCutListener());
		delete.addActionListener(new MyDeleteListener());

		about.addActionListener(new MyAboutListener());
		
		color.addActionListener(new MyColorListener());
		font.addActionListener(new MyFontListener());




		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));	
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));




		

	}


	public Notepad(){
		this("Untitled");
	}


	


	class MyOpenListener implements ActionListener{

		public void actionPerformed(ActionEvent ev){

			JFileChooser fc = new JFileChooser();

			if(fc.showOpenDialog(Notepad.this)==JFileChooser.APPROVE_OPTION){

			
				String path = fc.getSelectedFile().getPath();
				try{
				FileInputStream fi =new FileInputStream(path);
								
				int size=fi.available();
				byte[] str = new byte[size];
				fi.read(str);
				ta.setText(new String(str));
				fi.close();
				}catch(IOException e){}

			}
		}
	}




	
	class MySaveListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
		JFileChooser fc = new JFileChooser();
			if(fc.showSaveDialog(Notepad.this)==JFileChooser.APPROVE_OPTION){
				String path = fc.getSelectedFile().getPath();
				try{
				FileOutputStream fi2 = new FileOutputStream(path);
				byte[] b = ta.getText().getBytes();
				fi2.write(b);
				fi2.close();
				}catch(IOException e){}
				
			}	
		
		}		
		
	}
	
	class MyColorListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
		
			JColorChooser ch= new JColorChooser();
			Color co=ch.showDialog(ta,"Color Chooser",Color.BLACK);
			ta.setForeground(co);
						

		}
	}

	class MyFontListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			ta.setFont(new Font("Serif",Font.PLAIN,20));
			
		}
	
	}

	
	class MyNewListener implements ActionListener{

		public void actionPerformed(ActionEvent ev){
			ta.setText("");
		}
	}


	class MyExitListener implements ActionListener{

		public void actionPerformed(ActionEvent ev){
		
			System.exit(0);

		}
	}


	class MyCopyListener implements ActionListener{

		public void actionPerformed(ActionEvent ev){

			cpd = ta.getSelectedText();	
		}

	}


	class MySelectListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			ta.selectAll();

		}
	}

	class MyPasteListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			ta.insert(cpd,ta.getCaretPosition());
			

		}

	}

	class MyCutListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			cpd = ta.getSelectedText();
			ta.replaceSelection("");

		}
	
	}
	
	class MyDeleteListener implements ActionListener{

		public void actionPerformed(ActionEvent ev){
			ta.replaceSelection("");

		}
	}


	class MyAboutListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			JOptionPane.showMessageDialog(null, "This Notepad is Fully Developed By Abdallah Mohammed and all Credtis..");

		}

	}
}

 class NotepadApp{

		public static void main(String[] args){

			Notepad myFrame = new Notepad("Test Notepad");
			myFrame.setSize(640,480);
			myFrame.setVisible(true);
		}

	}
