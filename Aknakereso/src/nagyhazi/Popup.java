package nagyhazi;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.NumberFormat;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;
import javax.swing.*;

class Popup extends JDialog implements ActionListener, PropertyChangeListener {
	private int oszlop_szam;
    private int sor_szam;
    private int akna_szam;
    private JTextField textField;
    private JTextField textField2;
    private JTextField textField3;
    private JOptionPane optionPane;
    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";
    public boolean ervenyes = false;			// ervenyes sor, oszlop, akna db.szamok lettek megadva
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);

    /**
     * Returns null if the typed string was invalid; otherwise, returns the
     * string as the user entered it.
     */
    
    public boolean ervenyes_e() {
    	return ervenyes;
    }
    
    public int getSor() {
        return sor_szam;
    }

    public int getOszlop() {
        return oszlop_szam;
    }
    
    public int getAkna() {
        return akna_szam;
    }
    /**
     * Creates the reusable dialog.
     */
    public Popup(Frame aFrame, String aWord) {
        super(aFrame, true);

        setTitle("Egyeni");

        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(999);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        textField = new JFormattedTextField(formatter);
        textField2 = new JFormattedTextField(formatter);
        textField3 = new JFormattedTextField(20);
        
        //Create an array of the text and components to be displayed.
        String sor = "Sorok (10-60):";
        String oszlop = "Oszlopok (10-100):";
        String akna = "Aknak szama: ";
        Object[] array = {sor, textField, oszlop,  textField2, akna, textField3};

        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options = {btnString1, btnString2};

        //Create the JOptionPane.
        optionPane = new JOptionPane(array,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                options,
                options[0]);

        //Make this dialog display it.
        setContentPane(optionPane);

        //Handle window closing correctly.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        textField.addActionListener(this);
        textField2.addActionListener(this);
        textField3.addActionListener(this);

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        pack();
    }

    /**
     * This method handles events for the text field.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    /**
     * This method reacts to state changes in the option pane.
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
                && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop)
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }

            //Reset the JOptionPane's value.
            //If you don't do this, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);

            if (btnString1.equals(value)) {

                sor_szam = Integer.parseInt(textField.getText());
                oszlop_szam = Integer.parseInt(textField2.getText());
                String s = textField3.getText().replaceAll(" ", "");
                akna_szam = Integer.parseInt(s);
                System.out.println(s);
                //String s = new String();
                //s.replaceAll(" ","");		//nem mukodik valamiert:(
                /*for(int i = 0; i<textField3.getText().length(); i++) {
                	if(textField3.getText().charAt(i) != '5') {
                		s += textField3.getText().charAt(i);
                	}
                }*/
                //System.out.println(s);
                //akna_szam = Integer.parseInt(s);

                if (sor_szam > 9 && sor_szam < 61 && oszlop_szam > 9 && oszlop_szam < 101 && sor_szam*oszlop_szam>akna_szam && akna_szam>0 && akna_szam < 6000) {
                	ervenyes = true;
                    JOptionPane.showMessageDialog(this, "Correct answer given");
                    exit();
                } else {
                    //text was invalid
                    textField.selectAll();
                    textField2.selectAll();
                    textField3.selectAll();
                    sor_szam = 0;
                    oszlop_szam = 0;
                    textField.requestFocusInWindow();
                    textField2.requestFocusInWindow();
                    //textField3.requestFocusInWindow();
                }
            } else { //user closed dialog or clicked cancel
                JOptionPane.showMessageDialog(this, "It's OK.  "
                        + "We won't force you to type "
                         );
                sor_szam = 0;
                oszlop_szam = 0;
                exit();
            }
        }
    }

    /**
     * This method clears the dialog and hides it.
     */
    public void exit() {
        dispose();
    }
}