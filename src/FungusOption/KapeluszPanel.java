package FungusOption;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class KapeluszPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Description kapelusz_opis;
	private CheckBoxPanel kapelusz_typ, kapelusz_garbek, kapelusz_powierzchnia, kapelusz_brzeg;
	
	protected KapeluszPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		kapelusz_typ = new CheckBoxPanel("kapelusz_typ", "Typ:", Integer.MAX_VALUE, 90, true);
		kapelusz_garbek = new CheckBoxPanel("kapelusz_garbek", "Garbek:", Integer.MAX_VALUE, 60, true);
		kapelusz_powierzchnia = new CheckBoxPanel("kapelusz_powierzchnia", "Powierzchnia:", Integer.MAX_VALUE, 90, true);
		kapelusz_brzeg = new CheckBoxPanel("kapelusz_brzeg", "Brzeg:", Integer.MAX_VALUE, 90, true);
		add(kapelusz_typ);
		add(kapelusz_garbek);
		add(kapelusz_powierzchnia);
		add(kapelusz_brzeg);
		Box box = Box.createHorizontalBox();
		box.setBorder(getMyBorder("Opis:"));
		kapelusz_opis = new Description();
		box.add(kapelusz_opis);
		add(box);
	}
	
	private Border getMyBorder(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		return titledborder;		
	}
	
	protected boolean checkData(){
		boolean check = false;
		if (kapelusz_typ.isArraySelected()==false){
			JOptionPane.showMessageDialog(null,"Wybierz typ kapelusza.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (kapelusz_garbek.isArraySelected()==false){
				JOptionPane.showMessageDialog(null,"Wybierz garbek kapelusza.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (kapelusz_powierzchnia.isArraySelected()==false){
					JOptionPane.showMessageDialog(null,"Wybierz powierzchniê kapelusza.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (kapelusz_brzeg.isArraySelected()==false){
						JOptionPane.showMessageDialog(null,"Wybierz brzeg kapelusza.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
					} else {
						if (kapelusz_opis.getInfoText().length()==0){
							JOptionPane.showMessageDialog(null,"Wprowadz opis kapelusza.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
						} else {
							check=true;
						}
					}
				}
			}
		}
		return check;
	}
	
	protected void setFungusData(Fungus f){
		f.setKapelusz_typ(kapelusz_typ.getMultiSelected());
		f.setKapelusz_garbek(kapelusz_garbek.getMultiSelected());
		f.setKapelusz_powierzchnia(kapelusz_powierzchnia.getMultiSelected());
		f.setKapelusz_brzeg(kapelusz_brzeg.getMultiSelected());
		f.setKapelusz_opis(kapelusz_opis.getInfoText());
	}

}
