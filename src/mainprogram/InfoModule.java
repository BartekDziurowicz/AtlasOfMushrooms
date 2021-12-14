package mainprogram;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class InfoModule extends JEditorPane{ //jtextarea
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InfoModule(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);	
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		setBorder(new CompoundBorder(
			    titledborder,
			    BorderFactory.createEmptyBorder(0, 5, 5, 5)));
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setLineSpacing(attribs, 1.0f);		
		setEditable(false);
		setFocusable(false);		
	}
	
	/*public InfoModule(String title){
		
		setWrapStyleWord(true);
		setLineWrap(true);
		setMinimumSize(new Dimension(10, 80));
		setMaximumSize(new Dimension(10000, 80));
		setEditable(false);
		setFocusable(false);
	}
	
	/*public InfoModule(String title){
		setLayout(new GridLayout(1,1));
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		setBorder(new CompoundBorder(
			    titledborder,
			    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		setMinimumSize(new Dimension(10, 80));
		setMaximumSize(new Dimension(10000, 80));
	}*/

	/*public InfoModule(String title){
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		setBorder(new CompoundBorder(
			    titledborder,
			    BorderFactory.createEmptyBorder(0, 5, 5, 5)));
		setMinimumSize(new Dimension(10, 80));
		setMaximumSize(new Dimension(10000, 80));
		StyledDocument doc = getStyledDocument();
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setLineSpacing(attribs, 1.0f);
		doc.setParagraphAttributes(0, doc.getLength(), attribs, false);
		setContentType("text/html");
		setEditable(false);
		setFocusable(false);
	}*/
	

}

