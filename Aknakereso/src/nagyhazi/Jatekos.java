package nagyhazi;
import java.util.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "jatekos")
//@XmlAccessorType (XmlAccessType.FIELD)
@XmlAccessorType (XmlAccessType.NONE)
public class Jatekos implements Comparable{
	//@XmlElement(name="nev")
    private String Jnev;
	//@XmlElement(name="ido")
    private double Jido;
	//@XmlElement(name="nehezseg")
    private int Jnehezseg; //	1: konnyu 2: kozepes 3: nehez

    public String getNev() {
        return Jnev;
    }
    
    public double getIdo() {
        return Jido;
        
    }
    
    public int getNehezseg() {
        return Jnehezseg;
    }
    
    @XmlElement
    public void setNev(String nev) {
        this.Jnev = nev;
    }
    @XmlElement
    public void setIdo(double ido) {
        this.Jido = ido;
    }
    
    @XmlElement
    public void setNehezseg(int nehezseg) {
    	this.Jnehezseg = nehezseg;;
    }
    
    
    
    
    
    @Override
    public String toString() {
        return "     nev: " + Jnev + "  ido: " + Jido + "\n" ;
    }
    
    @Override
    public int compareTo(Object o) {
       return  (this.getIdo() < ((Jatekos) o).getIdo() ? -1 : (this.getIdo() == ((Jatekos) o).getIdo() ? 0 : 1));
   }
}