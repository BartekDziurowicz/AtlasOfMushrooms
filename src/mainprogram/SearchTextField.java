package mainprogram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.DocumentFilter;

import Connection.Statements;

public class SearchTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private KeyListener kl;

	public SearchTextField(CounterPanel cp, MainTable mt) {
		Color mybluebr = new Color(51, 153, 255);
		setHorizontalAlignment(SwingConstants.RIGHT);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(mybluebr, 1),
				BorderFactory.createEmptyBorder(2, 6, 2, 6)));
		setPreferredSize(new Dimension(150, 23));
		DefaultStyledDocument doc = new DefaultStyledDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(50));
		setDocument(doc);
		enterSearch(cp,mt);
		addFocusListener(new FocusListener(){
			
			@Override
			public void focusGained(FocusEvent e) {
				
				addKeyListener(kl);
			}

			@Override
			public void focusLost(FocusEvent e) {
				removeKeyListener(kl);
			}
			
		});
		
	}
	
	private void enterSearch(CounterPanel cp, MainTable mt){
		kl = new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode () == KeyEvent.VK_ENTER){
					Statements.setSortSearchText(getText());
					cp.setPagescounter();
					mt.setTableData();
				}	
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
}



class DocumentSizeFilter extends DocumentFilter {
	  int len;
	  public DocumentSizeFilter(int max_Chars) {
	    len = max_Chars;
	  }
	  public void insertString(FilterBypass fb, int offset, String str,
	      AttributeSet a) throws BadLocationException {		  
	    if ((fb.getDocument().getLength() + str.length()) <= len){
	      super.insertString(fb, offset, str, a);	     
	    }
	  }
	  public void replace(FilterBypass fb, int offset, int length, String str,
	      AttributeSet a) throws BadLocationException {		  
	    if ((fb.getDocument().getLength() + str.length() - length) <= len){
	      super.replace(fb, offset, length, str, a);	      
	    }
	  }
	}
