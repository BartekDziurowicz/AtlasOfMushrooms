package FungusOption;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class HymenoforPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Description hymenofor_opis;
	private CheckBoxPanel hymenofor_typ, hymenofor_rodzaj;
	
	protected HymenoforPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		hymenofor_typ = new CheckBoxPanel("hymenofor_typ", "Typ:", Integer.MAX_VALUE, 90, false);
		hymenofor_rodzaj = new CheckBoxPanel("hymenofor_rodzaj", "Rodzaj:", Integer.MAX_VALUE, 90, true);
		hymenofor_opis = new Description();
		add(hymenofor_typ);
		add(hymenofor_rodzaj);
		Box box = Box.createHorizontalBox();
		box.setBorder(getMyBorder("Opis:"));
		hymenofor_opis = new Description();
		box.add(hymenofor_opis);
		add(box);
	}
	
	private Border getMyBorder(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		return titledborder;		
	}
	
	protected boolean checkData(){
		boolean check = false;
		if (hymenofor_typ.isArraySelected()==false) {
			JOptionPane.showMessageDialog(null,"Wybierz typ hymenoforu.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (hymenofor_rodzaj.isArraySelected()==false){
				JOptionPane.showMessageDialog(null,"Wybierz rodzaj hymenoforu.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (hymenofor_opis.getInfoText().length()==0) {
					JOptionPane.showMessageDialog(null,"Wprowadz opis hymenoforu.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					check=true;
				}
			}
		}							
		return check;
	}
	
	protected void setFungusData(Fungus f){
		f.setHymenofor_typ(hymenofor_typ.getSingleSelected());
		f.setHymenofor_rodzaj(hymenofor_rodzaj.getMultiSelected());
		f.setHymenofor_opis(hymenofor_opis.getInfoText());
	}

}
