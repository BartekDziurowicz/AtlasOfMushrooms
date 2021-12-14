package FungusOption;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class WartoscPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Description wartosc_opis;
	private CheckBoxPanel ochrona, wartosc;
	
	protected WartoscPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ochrona = new CheckBoxPanel("ochrona", "Ochrona:", Integer.MAX_VALUE, 60, false);
		wartosc = new CheckBoxPanel("wartosc", "Wartoœæ:", Integer.MAX_VALUE, 60, false);
		add(ochrona);
		add(wartosc);
		Box box = Box.createHorizontalBox();
		box.setBorder(getMyBorder("Opis:"));
		wartosc_opis = new Description();
		box.add(wartosc_opis);
		add(box);
	}
	
	private Border getMyBorder(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		return titledborder;		
	}
	
	protected boolean checkData(){
		boolean check = false;
		if (ochrona.isArraySelected()==false){
			JOptionPane.showMessageDialog(null,"Wybierz rodzaj ochrony.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (wartosc.isArraySelected()==false){
				JOptionPane.showMessageDialog(null,"Wybierz wartoœæ.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (wartosc_opis.getInfoText().length()==0){
					JOptionPane.showMessageDialog(null,"Wprowadz opis wartoœci.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					check=true;
				}
			}
		}
		return check;
	}
	
	protected void setFungusData(Fungus f){
		f.setOchrona(ochrona.getSingleSelected());
		f.setWartosc(wartosc.getSingleSelected());
		f.setWartosc_opis(wartosc_opis.getInfoText());
	}

}
