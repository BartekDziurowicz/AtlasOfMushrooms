package FungusOption;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.DefaultStyledDocument;

public class BasicPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField latin_first, latin_second, polish_name;
	private Description owocnik_opis;
	private CheckBoxPanel owocnik;

	protected BasicPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));		
		add(getLatinBox());
		add(getPolish_Name());
		owocnik = new CheckBoxPanel("owocnik_typ", "Owocnik:", Integer.MAX_VALUE, 60, false);
		add(owocnik);		
		Box box = Box.createHorizontalBox();
		box.setBorder(getMyBorder("Owocnik opis:"));
		owocnik_opis = new Description();
		box.add(owocnik_opis);
		add(box);
	}
	
	protected JTextField getLatin_first(){
		return latin_first;
	}
	
	private Box getLatinBox(){		
		Box box = Box.createHorizontalBox();
		box.setBorder(getMyBorder("Nazwa ³aciñska:"));				
		latin_first = new JTextField("");
		latin_first.setMaximumSize(new Dimension(150, latin_first.getMinimumSize().height));
		latin_first.setPreferredSize(new Dimension(150, latin_first.getMinimumSize().height));
		latin_first.setDisabledTextColor(Color.GRAY);
		latin_first.setEnabled(false);		
		box.add(latin_first);
		latin_second = new JTextField();
		latin_second.setMaximumSize(new Dimension(Integer.MAX_VALUE, latin_second.getMinimumSize().height));
		DefaultStyledDocument doc = new DefaultStyledDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(40));
		latin_second.setDocument(doc);
		latin_second.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {		
				 if (!Character.isLetter(e.getKeyChar())) {
					  e.consume();
			        }
			}
		});
		latin_second.setTransferHandler(null);
		box.add(latin_second);
		return box;
	}
	
	private Box getPolish_Name(){
		Box box = Box.createHorizontalBox();
		polish_name = new JTextField("");
		DefaultStyledDocument doc = new DefaultStyledDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(50));
		polish_name.setDocument(doc);
		box.setBorder(getMyBorder("Nazwa polska:"));
		polish_name.setMaximumSize(new Dimension(Integer.MAX_VALUE, polish_name.getMinimumSize().height));
		box.add(polish_name);
		return box;
	}
	
	private Border getMyBorder(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		return titledborder;		
	}
	
	protected boolean checkData(){
		boolean check = false;
		if (latin_first.getText().length()==0){
			JOptionPane.showMessageDialog(null,"Wybierz rodzaj.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (latin_second.getText().length()==0){
				JOptionPane.showMessageDialog(null,"Wprowadz nazwê ³aciñsk¹.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (owocnik.isArraySelected()==false) { 
					JOptionPane.showMessageDialog(null,"Wybierz typ owocnika.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (owocnik_opis.getInfoText().length()==0) {
						JOptionPane.showMessageDialog(null,"Wprowadz opis owocnika.","Niepoprawne dane.", JOptionPane.INFORMATION_MESSAGE);
					} else {
						check = true;
					}
				}
			}
		}
		return check;
	}
	
	protected void setFungusData(Fungus f){
		f.setRodzaj(latin_first.getText());
		f.setNazwa_lacinska(latin_second.getText());
		f.setNazwa_polska(polish_name.getText());
		f.setOwocnik_typ(owocnik.getSingleSelected());
		f.setOwocnik_opis(owocnik_opis.getInfoText());
	}
	
}
