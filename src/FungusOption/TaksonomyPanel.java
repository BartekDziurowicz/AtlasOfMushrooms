package FungusOption;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class TaksonomyPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComboBox typ, podtyp, klasa, podklasa, rzad, rodzina, rodzaj;
	private JLabel name;
	
	protected TaksonomyPanel(Fungus f, BasicPanel bp){
		setBorder(getMyBorder("Taksonomia"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));		
		newTaksonomy(f, bp);
	}
	
	private Border getMyBorder(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		return titledborder;		
	}
	
	private JLabel setLabel(String tekst){
		name=new JLabel(tekst, SwingConstants.LEFT);
		name.setMinimumSize(new Dimension(1000, 30));		
		return name;
	}
	
	private void newTaksonomy(Fungus f, BasicPanel bp){
		add(setLabel(" "));
		add(setLabel("Typ:"));
		typ = new ComboBox();
		add(typ);
		add(setLabel(" "));
		add(setLabel("Podtyp:"));
		podtyp = new ComboBox();
		add(podtyp);
		add(setLabel(" "));
		add(setLabel("Klasa:"));
		klasa = new ComboBox();
		add(klasa);
		add(setLabel(" "));
		add(setLabel("Podklasa:"));
		podklasa = new ComboBox();
		add(podklasa);
		add(setLabel(" "));
		add(setLabel("Rz¹d:"));
		rzad = new ComboBox();
		add(rzad);
		add(setLabel(" "));
		add(setLabel("Rodzina:"));
		rodzina = new ComboBox();
		add(rodzina);
		add(setLabel(" "));
		add(setLabel("Rodzaj:"));
		rodzaj = new ComboBox();
		add(rodzaj);
		add(setLabel(" "));
		
		typ.setComboBoxStart(0);
		typ.addActionListener(typ.setComboBoxAction(1, podtyp));		
		podtyp.addActionListener(podtyp.setComboBoxAction(2, klasa));
		klasa.addActionListener(klasa.setComboBoxAction(3, podklasa));
		podklasa.addActionListener(podklasa.setComboBoxAction(4, rzad));
		rzad.addActionListener(rzad.setComboBoxAction(5, rodzina));
		rodzina.addActionListener(rodzina.setComboBoxAction(6, rodzaj));
		rodzaj.addActionListener(rodzaj.setComboBoxEnd(f, bp));	
	}


}
