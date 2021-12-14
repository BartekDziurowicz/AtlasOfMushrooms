package FungusOption;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.DocumentFilter;

public class Description extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea info;
	
	protected Description(){		
		this.getViewport().add(getInfo());
	}
	
	
	private JTextArea getInfo(){
		info = new JTextArea("");		
		DefaultStyledDocument doc = new DefaultStyledDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(2500));
		info.setDocument(doc);		
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		return info;
	}
	
	protected String getInfoText(){
		String desc = info.getText();
		return desc;
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
