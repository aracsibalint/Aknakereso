package nagyhazi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;  

public class Menubar 
{  
	private JMenu menu, submenu;  
	private JMenuItem beallitasok, rekordok, kilepes, konnyu, kozepes, nehez;  
	private  JMenuBar mb;
	
	
	public JMenu getMenu() {
		return menu;
	}

	public void setMenu(JMenu menu) {
		this.menu = menu;
	}

	public JMenu getSubmenu() {
		return submenu;
	}

	public void setSubmenu(JMenu submenu) {
		this.submenu = submenu;
	}

	public JMenuItem getBeallitasok() {
		return beallitasok;
	}

	public void setBeallitasok(JMenuItem beallitasok) {
		this.beallitasok = beallitasok;
	}

	public JMenuItem getRekordok() {
		return rekordok;
	}

	public void setRekordok(JMenuItem rekordok) {
		this.rekordok = rekordok;
	}

	public JMenuItem getKilepes() {
		return kilepes;
	}

	public void setKilepes(JMenuItem kilepes) {
		this.kilepes = kilepes;
	}

	public JMenuItem getKonnyu() {
		return konnyu;
	}

	public void setKonnyu(JMenuItem konnyu) {
		this.konnyu = konnyu;
	}

	public JMenuItem getKozepes() {
		return kozepes;
	}

	public void setKozepes(JMenuItem kozepes) {
		this.kozepes = kozepes;
	}

	public JMenuItem getNehez() {
		return nehez;
	}

	public void setNehez(JMenuItem nehez) {
		this.nehez = nehez;
	}

	public JMenuBar getMb() {
		return mb;
	}

	public void setMb(JMenuBar mb) {
		this.mb = mb;
	}

	
          
          public Menubar(ActionListener listener){  
	          //JFrame f= new JFrame("Menu and MenuItem Example");  
	          mb=new JMenuBar();  
	          menu=new JMenu("Menu");  
	          submenu=new JMenu("Uj Jatek");  
	          beallitasok=new JMenuItem("Egyeni");  
	          rekordok=new JMenuItem("Rekordok");  
	          kilepes=new JMenuItem("Kilepes");  
	          konnyu=new JMenuItem("Konnyu");  
	          kozepes=new JMenuItem("Kozepes");
	          nehez=new JMenuItem("Nehez");
	          beallitasok.addActionListener(listener);
	          rekordok.addActionListener(listener);
	          kilepes.addActionListener(listener);
	          konnyu.addActionListener(listener);
	          kozepes.addActionListener(listener);
	          nehez.addActionListener(listener);
	          menu.add(beallitasok); menu.add(rekordok); menu.add(kilepes);  
	          submenu.add(konnyu); submenu.add(kozepes); submenu.add(nehez);
	          menu.add(submenu);
	          mb.add(menu);  
	          
	          //f.setJMenuBar(mb);  
	          //f.setSize(400,400);  
	          //f.setLayout(null);  
	          //f.setVisible(true);  
	          //super("TicTacToe");
	  		  //f.setResizable(true);
	  		  //f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  		  //add(new TicTacToe(20,20,20), BorderLayout.CENTER);
          }
		/*@Override
		public void actionPerformed(ActionEvent al) {
			if(al.getSource() == beallitasok) {
				System.out.println("beall");
			}
			if(al.getSource() == rekordok) {
				System.out.println("rek");
			}
			if(al.getSource() == kilepes) {
				System.out.println("kil");
				//dispose();
			}
			if(al.getSource() == konnyu) {
				System.out.println("konnyu");
				TicTacToe a = new TicTacToe(20,20,20);
			}
			if(al.getSource() == kozepes) {
				System.out.println("koz");
				new TicTacToe(20,20,20);
				//dispose();
			}
			if(al.getSource() == nehez) {
				System.out.println("neh");
			}
			
		}*/
}  