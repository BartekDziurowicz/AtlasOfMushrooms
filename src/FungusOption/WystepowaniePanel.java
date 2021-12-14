package FungusOption;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class WystepowaniePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Description wystepowanie_opis;
	private CheckBoxPanel srodowisko, sposob_zycia, okres_od, okres_do;

	protected WystepowaniePanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		srodowisko = new CheckBoxPanel("srodowisko", "Œrodowisko:", Integer.MAX_VALUE, 60, true);
		sposob_zycia = new CheckBoxPanel("sposob_zycia", "Sposób ¿ycia:", Integer.MAX_VALUE, 60, true);
		okres_od = new CheckBoxPanel("okres", "Okres od:", Integer.MAX_VALUE, 90, false);
		okres_do = new CheckBoxPanel("okres", "Okres do:", Integer.MAX_VALUE, 90, false);
		add(srodowisko);
		add(sposob_zycia);
		add(okres_od);
		add(okres_do);	
		
		Box box = Box.createHorizontalBox();
		box.setBorder(getMyBorder("Opis:"));
		wystepowanie_opis = new Description();
		box.add(wystepowanie_opis);
		add(box);
	}
	
	private Border getMyBorder(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		return titledborder;		
	}
	
	protected boolean checkData(){
		boolean check = false;
		if (srodowisko.isArraySelected()==false){
			JOptionPane.showMessageDialog(null,"Wybierz œrodowisko.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (sposob_zycia.isArraySelected()==false){
				JOptionPane.showMessageDialog(null,"Wybierz sposób ¿ycia.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (okres_od.isArraySelected()==false || okres_do.isArraySelected()==false){
					JOptionPane.showMessageDialog(null,"Wybierz okres.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (wystepowanie_opis.getInfoText().length()==0){
						JOptionPane.showMessageDialog(null,"Wprowadz opis wystêpowania.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
					} else {
						check = true;
					}
				}
			}
		}
		return check;
	}
	
	protected void setFungusData(Fungus f){
		f.setSrodowisko(srodowisko.getMultiSelected());
		f.setSposob_zycia(sposob_zycia.getMultiSelected());
		f.setOkres(okres_od.getSingleSelected(), okres_do.getSingleSelected());
		f.setWystepowanie_opis(wystepowanie_opis.getInfoText());
	}
	

}
