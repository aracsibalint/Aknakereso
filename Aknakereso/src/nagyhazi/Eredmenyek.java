package nagyhazi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.*;

@XmlRootElement(name = "eredmenyek")
@XmlAccessorType (XmlAccessType.FIELD)
public class Eredmenyek /*extends TreeSet<E> */{
	@XmlElement(name = "eredmeny")
	private List<Jatekos> eredmenyek = new ArrayList<Jatekos>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public boolean add(Jatekos mt) {
	         super.add(mt);
	         Collections.sort(eredmenyek);
	         return true;
	    }
	};

	public List<Jatekos> getEredmenyek() {
        return eredmenyek;
    }
	
	public void setEredmenyek(List<Jatekos> eredmenyek) {
		this.eredmenyek = eredmenyek;
    }
	
	public void unMarshaling() throws JAXBException 
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Eredmenyek.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    if(new File("konnyu.xml").isFile()) {
	    	Eredmenyek eredmeny = (Eredmenyek) jaxbUnmarshaller.unmarshal( new File("eredmenyek.xml") );
		    eredmenyek = eredmeny.getEredmenyek();
	    }
	}
	
	public void add(Jatekos jatekos) {
		eredmenyek.add(jatekos);
	}
	
	public Eredmenyek() {
		
	}
	
	public void marshalling() throws JAXBException
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Eredmenyek.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     
	    //Marshal the employees list in console
	    jaxbMarshaller.marshal(this, System.out);
	     
	    //Marshal the employees list in file
	    jaxbMarshaller.marshal(this, new File("eredmenyek.xml"));

	}
	
	public String Kiir(int nehezseg) { // konnyu: 1 kozepes: 2 nehez: 3
		String s = new String();
		int db = 1;
		for(int i = 0;i<eredmenyek.size(); i++) {
			if(eredmenyek.get(i).getNehezseg() == nehezseg) {
				//System.out.println(eredmenyek.get(i));
				s += db + ": " + eredmenyek.get(i);
				db++;
			}
		}
		return s;
	}
	
	/*public String kozepesKiir() {
		String s = new String();
		int db = 1;
		for(int i = 0;i<eredmenyek.size(); i++) {
			if(eredmenyek.get(i).getNehezseg() == 2) {
				//System.out.println(eredmenyek.get(i));
				s += db + ": " + eredmenyek.get(i);
				db++;
			}
		}
		return s;
	}
	
	public String nehezKiir() {
		String s = new String();
		int db = 1;
		for(int i = 0;i<eredmenyek.size(); i++) {
			if(eredmenyek.get(i).getNehezseg() == 3) {
				//System.out.println(eredmenyek.get(i));
				s += db + ": " + eredmenyek.get(i);
				db++;
			}
		}
		return s;
	}*/
	
	
}