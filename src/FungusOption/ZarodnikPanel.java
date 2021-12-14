package FungusOption;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ZarodnikPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Description wysyp_zarodnikow_opis;
	private CheckBoxPanel wysyp_zarodnikow;
	
	protected ZarodnikPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		wysyp_zarodnikow = new CheckBoxPanel("wysyp_zarodnikow", "Wysyp zarodników:", Integer.MAX_VALUE, 90, false);		
		add(wysyp_zarodnikow);
		Box box = Box.createHorizontalBox();
		box.setBorder(getMyBorder("Opis:"));
		wysyp_zarodnikow_opis = new Description();
		box.add(wysyp_zarodnikow_opis);
		add(box);
	}
	
	private Border getMyBorder(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		return titledborder;		
	}
	
	protected boolean checkData(){
		boolean check = false;
		if (wysyp_zarodnikow.isArraySelected()==false){
			JOptionPane.showMessageDialog(null,"Wybierz wysyp zarodników.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (wysyp_zarodnikow_opis.getInfoText().length()==0) {
				JOptionPane.showMessageDialog(null,"Wprowadz opis zarodników.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				check=true;
			}
		}
		return check;
	}
	
	protected void setFungusData(Fungus f){
		f.setWysyp_zarodnikow(wysyp_zarodnikow.getSingleSelected());
		f.setWysyp_zarodnikow_opis(wysyp_zarodnikow_opis.getInfoText());		
	}

}
