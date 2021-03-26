package nagyhazi;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Mezo extends JButton /*implements ActionListener*/{
	/**
	 * 
	 */
	private boolean valaszthato = true;
	private boolean akna_e = false;
	private int szomszedos_akna = 0;
	private boolean kiszedve = false;
	private boolean zaszlo = false;
    
    
    public boolean isValaszthato() {
		return valaszthato;
	}


	public void setValaszthato(boolean valaszthato) {
		this.valaszthato = valaszthato;
	}


	public boolean isAkna_e() {
		return akna_e;
	}


	public void setAkna_e() {
		this.akna_e = true;
	}


	public int getSzomszedos_akna() {
		return szomszedos_akna;
	}


	public void plusSzomszedos_akna() {
		this.szomszedos_akna += 1;
	}


	public boolean isKiszedve() {
		return kiszedve;
	}


	public void setKiszedve() {
		this.kiszedve = true;
	}


	public boolean isZaszlo() {
		return zaszlo;
	}


	public void setZaszlo() {
		this.zaszlo = !this.zaszlo;
	}

    
    
	private static final long serialVersionUID = 1L;

	
	public Mezo(MouseListener ml){
		this.addMouseListener(ml);
	}
}