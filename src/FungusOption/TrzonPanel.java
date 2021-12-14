package FungusOption;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TrzonPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Description trzon_opis;
	private CheckBoxPanel trzon_rodzaj, trzon_kolnierz, trzon_powierzchnia, trzon_bulwa, trzon_przekroj;
	
	protected TrzonPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		trzon_rodzaj = new CheckBoxPanel("trzon_rodzaj", "Rodzaj:", Integer.MAX_VALUE, 90, true);
		trzon_kolnierz = new CheckBoxPanel("trzon_kolnierz", "Ko³nierz:", Integer.MAX_VALUE, 90, true);
		trzon_powierzchnia = new CheckBoxPanel("trzon_powierzchnia", "Powierzchnia:", Integer.MAX_VALUE, 90, true);
		trzon_bulwa = new CheckBoxPanel("trzon_bulwa", "Bulwa:", Integer.MAX_VALUE, 90, true);
		trzon_przekroj = new CheckBoxPanel("trzon_przekroj", "Przekrój:", Integer.MAX_VALUE, 60, true);
		add(trzon_rodzaj);
		add(trzon_kolnierz);
		add(trzon_powierzchnia);
		add(trzon_bulwa);
		add(trzon_przekroj);
		Box box = Box.createHorizontalBox();
		box.setBorder(getMyBorder("Opis:"));
		trzon_opis = new Description();
		box.add(trzon_opis);
		add(box);
	}
	
	private Border getMyBorder(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		return titledborder;		
	}
	
	protected boolean checkData(){
		boolean check = false;
		if (trzon_rodzaj.isArraySelected()==false){
			JOptionPane.showMessageDialog(null,"Wybierz rodzaj trzonu.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (trzon_kolnierz.isArraySelected()==false){
				JOptionPane.showMessageDialog(null,"Wybierz ko³nierz trzonu.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (trzon_powierzchnia.isArraySelected()==false){
					JOptionPane.showMessageDialog(null,"Wybierz powierzchniê trzonu.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if(trzon_bulwa.isArraySelected()==false){
						JOptionPane.showMessageDialog(null,"Wybierz bulwê trzonu.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
					} else {
						if (trzon_przekroj.isArraySelected()==false){
							JOptionPane.showMessageDialog(null,"Wybierz przekrój trzonu.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
						} else {
							if (trzon_opis.getInfoText().length()==0){
								JOptionPane.showMessageDialog(null,"Wprowadz opis trzonu.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
							} else {
								check=true;
							}
						}
					}
				}
			}
		}
		return check;
	}
	
	protected void setFungusData(Fungus f){
		f.setTrzon_rodzaj(trzon_rodzaj.getMultiSelected());
		f.setTrzon_kolnierz(trzon_kolnierz.getMultiSelected());
		f.setTrzon_powierzchnia(trzon_powierzchnia.getMultiSelected());
		f.setTrzon_bulwa(trzon_bulwa.getMultiSelected());
		f.setTrzon_przekroj(trzon_przekroj.getMultiSelected());
		f.setTrzon_opis(trzon_opis.getInfoText());
	}

}
