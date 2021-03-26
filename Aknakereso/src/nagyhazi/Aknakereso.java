package nagyhazi;
import javax.swing.JPanel;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.swing.SwingUtilities;
//import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JFrame;
//import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.time.*;
import java.lang.String.*;


public class Aknakereso extends JFrame implements MouseListener, ActionListener{
	int oszlop;
	int sor;
	int akna;
	JPanel p=new JPanel();
	Mezo mezok[][];
	private static final long serialVersionUID = 1L;
	Menubar menu;
	long kezdes = (long) 0;		//kezdes ideje
	double ido = 0.0;		//gyoztes ido (gyozelem ideje - kezdes ideje)
	String nev = new String();
	Jatekos jatekos = new Jatekos();
	Eredmenyek r = new Eredmenyek();
	
	
	private void eredmenyekKiir(String s) {
	    JTextArea textArea = new JTextArea(25, 25);
	    textArea.setText(s);
	    textArea.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    JOptionPane.showMessageDialog(this, scrollPane, "Eredmenyek", 1);
	}
	
	boolean konnyu_e() {
		if(sor == 9 && oszlop == 9 && akna == 10) {
			return true;
		}
		return false;
	}
	
	boolean kozepes_e() {
		if(sor == 16 && oszlop == 16 && akna == 40) {
			return true;
		}
		return false;
	}
	
	boolean nehez_e() {
		if(sor == 16 && oszlop == 30 && akna == 99) {
			return true;
		}
		return false;
	}
	
	public void akna_elhelyez(int akna) {		//random elhelyezi az aknákat
		for (int i = 0; i < akna; i++) {        
	        int a = (int)(Math.random()*sor);
	        int b = (int)(Math.random()*oszlop);
	        if(mezok[a][b].isAkna_e()){
	            i-=1;
	        }else{
	            mezok[a][b].setAkna_e();
	        }
	    }
	}
	
	public void szomszedos_akna_kitolt() {
		for (int j = 0; j < mezok.length; j++) {
	        for (int i = 0; i < mezok[j].length; i++) {  //mezok mindegyik parameterenek erteket ad
	            if(mezok[j][i].isAkna_e()){
	                if(j==0 && i==0){   //bal felul
	                    mezok[j][i+1].plusSzomszedos_akna();
	                    mezok[j+1][i+1].plusSzomszedos_akna();
	                    mezok[j+1][i].plusSzomszedos_akna();
	                }else if(j==sor-1 && i==0){      //bal alul
	                    mezok[j][i+1].plusSzomszedos_akna();
	                    mezok[j-1][i+1].plusSzomszedos_akna();
	                    mezok[j-1][i].plusSzomszedos_akna();
	                }else if(j==sor-1 && i==oszlop-1){  //jobb alul
	                    mezok[j][i-1].plusSzomszedos_akna();
	                    mezok[j-1][i-1].plusSzomszedos_akna();
	                    mezok[j-1][i].plusSzomszedos_akna();
	                }else if(j==0 && i==oszlop-1){    //jobb felul
	                    mezok[j][i-1].plusSzomszedos_akna();
	                    mezok[j+1][i-1].plusSzomszedos_akna();
	                    mezok[j+1][i].plusSzomszedos_akna();
	                }else if(j==0){     //felso sor
	                    mezok[j][i+1].plusSzomszedos_akna();
	                    mezok[j][i-1].plusSzomszedos_akna();
	                    mezok[j+1][i+1].plusSzomszedos_akna();
	                    mezok[j+1][i].plusSzomszedos_akna();
	                    mezok[j+1][i-1].plusSzomszedos_akna();
	                }else if(j==sor-1){   //also sor
	                    mezok[j][i+1].plusSzomszedos_akna();
	                    mezok[j][i-1].plusSzomszedos_akna();
	                    mezok[j-1][i+1].plusSzomszedos_akna();
	                    mezok[j-1][i].plusSzomszedos_akna();
	                    mezok[j-1][i-1].plusSzomszedos_akna();
	                }else if(i==0){     //bal oldal
	                    mezok[j][i+1].plusSzomszedos_akna();
	                    mezok[j-1][i+1].plusSzomszedos_akna();
	                    mezok[j-1][i].plusSzomszedos_akna();
	                    mezok[j+1][i].plusSzomszedos_akna();
	                    mezok[j+1][i+1].plusSzomszedos_akna();
	                }else if(i==oszlop-1){    //jobb oldal
	                    mezok[j][i-1].plusSzomszedos_akna();
	                    mezok[j-1][i-1].plusSzomszedos_akna();
	                    mezok[j-1][i].plusSzomszedos_akna();
	                    mezok[j+1][i].plusSzomszedos_akna();
	                    mezok[j+1][i-1].plusSzomszedos_akna();
	                }else{      //közepen
	                    mezok[j][i+1].plusSzomszedos_akna();
	                    mezok[j][i-1].plusSzomszedos_akna();
	                    mezok[j-1][i-1].plusSzomszedos_akna();
	                    mezok[j-1][i+1].plusSzomszedos_akna();
	                    mezok[j-1][i].plusSzomszedos_akna();
	                    mezok[j+1][i].plusSzomszedos_akna();
	                    mezok[j+1][i-1].plusSzomszedos_akna();
	                    mezok[j+1][i+1].plusSzomszedos_akna();
	                }
	            }
	        }
	    }
	}
	
	public void vereseg() {
		for (int j = 0; j < mezok.length; j++) {
	        for (int i = 0; i < mezok[j].length; i++) {
	        	if(mezok[j][i].isAkna_e()) {
	        		mezok[j][i].setBackground(Color.RED);
	        		mezok[j][i].setValaszthato(false);
	        	}
	        	mezok[j][i].setEnabled(false);
	        	mezok[j][i].setValaszthato(false);
	        }
		}
	}
	
	public boolean gyozelem() {		//TODO popup window uj jatek nev beiras eredmenyek mentese
		if(valaszthato_db()==akna) {			//ha már csak aknák nem lettek választva
			for (int j = 0; j < mezok.length; j++) {
		        for (int i = 0; i < mezok[j].length; i++) {
		        	if(mezok[j][i].isAkna_e()) {
		        		mezok[j][i].setValaszthato(false);
		        		mezok[j][i].setBackground(Color.GREEN);
		        	}
		        	mezok[j][i].setEnabled(false);
		        }
			}
			return true;
		}
		return false;
	}
	
	public int valaszthato_db() {		//gyozelem ellenorzesehez kell
		int db = 0;
		for (int j = 0; j < mezok.length; j++) {
	        for (int i = 0; i < mezok[j].length; i++) {
	        	if(mezok[j][i].isValaszthato()){
	                db++;
	            }
	        }
		}
		return db;
	}
	
	
	public void felderit(int sor_szama, int oszlop_szama){     //biztosítja a felderites()-t az egész pályán
	    //korabbi megoldas, nem tul szep, jol mukodik
		/*felderites(sor_szama, oszlop_szama);
	    int db=1;
	    while(db>0){
	        db=0;
	        for (int j = 0; j < mezok.length; j++) {
	            for (int i = 0; i < mezok[j].length; i++) {
	                if (mezok[j][i].isValaszthato()==false && mezok[j][i].getSzomszedos_akna()==0 && mezok[j][i].isKiszedve()==false){
	                    felderites(j, i);
	                    db++;
	                }
	            }
	        }
	    }*/
	    
		
		//uj megoldas, rekurzioval (Flood fill algoritmus, nem kell segedfuggveny)
		if (oszlop_szama < 0 || oszlop_szama >= this.oszlop || sor_szama < 0 || sor_szama >= this.sor) 
	        return; 
	    if (mezok[sor_szama][oszlop_szama].isKiszedve() == true || mezok[sor_szama][oszlop_szama].getSzomszedos_akna()!=0) 
	        return; 
	   
	    if(mezok[sor_szama][oszlop_szama].getSzomszedos_akna()==0) {
	    	mezok[sor_szama][oszlop_szama].setValaszthato(false);
	    	mezok[sor_szama][oszlop_szama].setKiszedve();
	    	
	    	if(sor_szama==0 && oszlop_szama==0){   //bal felul
	            mezok[sor_szama][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama].setValaszthato(false);
	        }else if(sor_szama==sor-1 && oszlop_szama==0){      //bal alul
	            mezok[sor_szama][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama].setValaszthato(false);
	        }else if(sor_szama==sor-1 && oszlop_szama==oszlop-1){  //jobb alul
	            mezok[sor_szama][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama].setValaszthato(false);
	        }else if(sor_szama==0 && oszlop_szama==oszlop-1){    //jobb felul
	            mezok[sor_szama][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama].setValaszthato(false);
	        }else if(sor_szama==0){     //felso sor
	            mezok[sor_szama][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama-1].setValaszthato(false);
	        }else if(sor_szama==sor-1){   //also sor
	            mezok[sor_szama][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama-1].setValaszthato(false);
	        }else if(oszlop_szama==0){     //bal oldal
	            mezok[sor_szama][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama+1].setValaszthato(false);
	        }else if(oszlop_szama==oszlop-1){    //jobb oldal
	            mezok[sor_szama][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama-1].setValaszthato(false);
	        }else{      //közepen	            
	            mezok[sor_szama][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama+1].setValaszthato(false);
	            mezok[sor_szama-1][oszlop_szama].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama-1].setValaszthato(false);
	            mezok[sor_szama+1][oszlop_szama+1].setValaszthato(false);
	        }
	    }
	    felderit(sor_szama+1, oszlop_szama); 
	    felderit(sor_szama+1, oszlop_szama+1);
	    felderit(sor_szama+1, oszlop_szama-1);
	    felderit(sor_szama-1, oszlop_szama);
	    felderit(sor_szama-1, oszlop_szama-1);
	    felderit(sor_szama-1, oszlop_szama+1);
	    felderit(sor_szama, oszlop_szama+1); 
	    felderit(sor_szama, oszlop_szama-1); 
	}
		
	public Aknakereso(int sor, int oszlop, int akna){	//max 60*100
		super("Aknakereso");
		this.sor = sor;
		this.oszlop = oszlop;
		if(this.sor > 60) {this.sor = 60; sor = 60;}				//maximum ertekek megadasa
		if(this.oszlop > 100) {this.oszlop = 100; oszlop = 100;}	//minimum az egyeni megadasanal biztositva
		this.akna = akna;
		this.mezok = new Mezo[this.sor][this.oszlop];
		menu = new Menubar(this);
		
		setJMenuBar(menu.getMb());  
        setVisible(true);      
		setSize(oszlop*16,sor*16+40);	
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(sor, oszlop));
		for (int j = 0; j < mezok.length; j++) {
	        for (int i = 0; i < mezok[j].length; i++) {
	        	mezok[j][i]=new Mezo(this);
	        	//mezok[j][i].addMouseListener(this);
	        	mezok[j][i].setFont(new Font("serif", Font.BOLD, 16));
        		mezok[j][i].setMargin(new Insets(0,0,0,0));
				p.add(mezok[j][i]);
	        }
	    }	
		akna_elhelyez(akna);
		szomszedos_akna_kitolt();	
		add(p);	
		setVisible(true);
	}
	
	public void kilep() {
		dispose();
	}	

	@Override
	public void mousePressed(MouseEvent e) {	//klikk
		if(SwingUtilities.isLeftMouseButton(e)) {		//balklikk
			for (int j = 0; j < mezok.length; j++) {
		        for (int i = 0; i < mezok[j].length; i++) {
		        	if(e.getSource() == mezok[j][i]) {
		        		if(mezok[j][i].isValaszthato() == true) {
			        		if(kezdes == 0.0) {	//elsõ kattintas (kezdes) - kezdes rendszerideje
			        			kezdes = (System.currentTimeMillis());
			        		}
			        		mezok[j][i].setBackground(null);
			        		felderit(j, i);
			        		mezok[j][i].setValaszthato(false);
							if(mezok[j][i].getSzomszedos_akna() != 0)
								mezok[j][i].setText(Integer.toString(mezok[j][i].getSzomszedos_akna()));
								mezok[j][i].setEnabled(false);
							if(mezok[j][i].isAkna_e())
								vereseg();
							if(gyozelem()) {
								long vege = (long) 0.00;			
								vege = (System.currentTimeMillis());
								ido = ((double)vege-kezdes)/1000;
								nev = (String)JOptionPane.showInputDialog(p,"Add meg a neved ha el szeretned menteni az idot:", "Eredmeny mentese", JOptionPane.PLAIN_MESSAGE, null , null, null);
								//TODO Eredmeny mentese
								if(nev != null) {		//nem cancel
									jatekos.setIdo(ido);
									if(nev.isEmpty()) {
										jatekos.setNev("Nem adott meg nevet");
									}else {
										jatekos.setNev(nev);
									}
									if(konnyu_e()) {
										jatekos.setNehezseg(1);
									}
									if(kozepes_e()) {
										jatekos.setNehezseg(2);
									}
									if(nehez_e()) {
										jatekos.setNehezseg(3);
									}
									//kiolvasas xml-bol
									JAXBContext jaxbContext;
									try {
										jaxbContext = JAXBContext.newInstance(Eredmenyek.class);
										Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
									    	r = (Eredmenyek) jaxbUnmarshaller.unmarshal( new File("eredmenyek.xml") );
									}catch (JAXBException exc) {
										// TODO Auto-generated catch block
										exc.printStackTrace();
									}
									//kiolvasas vege (r-be beolvasva)
									
									//r.setEredmenyek(new ArrayList<Jatekos>());
									if(jatekos.getNehezseg() > 0) {		//Ha konnyu, kozepes vagy nehez jatekot jatszott (nem egyeni)
										r.getEredmenyek().add(jatekos);
										try {
											r.marshalling();									    
										} catch (JAXBException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									//System.out.println(r.getEredmenyek());
								}else {		//cancel vagy X
									
								}
							}
		        		}
					}
		        }
		    }
			for (int j = 0; j < mezok.length; j++) {		// felderites hatasanak kijelzese a palyan
		        for (int i = 0; i < mezok[j].length; i++) {
		        	if(mezok[j][i].isValaszthato() == false) {
						if(mezok[j][i].getSzomszedos_akna() != 0) {
							mezok[j][i].setText(Integer.toString(mezok[j][i].getSzomszedos_akna()));
						}else {
							//mezok[j][i].setBackground(Color.gray);
						}
						mezok[j][i].setEnabled(false);
		        	}
		        }
			}
		}	//balklikk vege
		
		if(SwingUtilities.isRightMouseButton(e)) {
			for (int j = 0; j < mezok.length; j++) {
		        for (int i = 0; i < mezok[j].length; i++) {
		        	if(e.getSource() == mezok[j][i]) {
						if(mezok[j][i].isValaszthato() == true) {
							mezok[j][i].setZaszlo();
							if(mezok[j][i].isZaszlo() == true) {
								mezok[j][i].setBackground(Color.black);
				        	}else {
				        		mezok[j][i].setBackground(null);
				        	}
						}
					}
		        }
		    }
		}	//jobbklikk vege
	}	//klikk vege

	@Override
	public void actionPerformed(ActionEvent al) {
		// TODO Auto-generated method stub
		if(al.getSource() == menu.getBeallitasok()) {
			Popup egyeni = new Popup(this, "Asd");
			egyeni.setVisible(true);
			if(egyeni.ervenyes_e()) {
				dispose();
				sor = egyeni.getSor();
				oszlop = egyeni.getOszlop();
				new Aknakereso(egyeni.getSor(), egyeni.getOszlop(), egyeni.getAkna());
				
				System.out.println(oszlop);
			}
		}
		if(al.getSource() == menu.getRekordok()) {
			//kiolvasas xml-bol
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(Eredmenyek.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			    	r = (Eredmenyek) jaxbUnmarshaller.unmarshal( new File("eredmenyek.xml") );
			}catch (JAXBException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
			//kiolvasas vege (r-be beolvasva)
			
			//System.out.println(r.eredmenyek.size());	//nullptr exc
			String s = new String();
			s += "Konnyu:\n" + r.Kiir(1) + "Kozepes:\n" + r.Kiir(2) + "Nehez:\n" + r.Kiir(3);
			eredmenyekKiir(s);
		}
		if(al.getSource() == menu.getKilepes()) {
			kilep();
		}
		if(al.getSource() == menu.getKonnyu()) {
			dispose();
			new Aknakereso(9,9,10);
		}
		if(al.getSource() == menu.getKozepes()) {
			kilep();
			new Aknakereso(16,16,40);
		}
		if(al.getSource() == menu.getNehez()) {
			kilep();
			new Aknakereso(16,30,99);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}