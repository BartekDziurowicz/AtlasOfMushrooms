package mainprogram;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class ChangePasswordField extends JPasswordField implements KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	char[] charpass = new char[0];
	
	public ChangePasswordField(){
		setTransferHandler(null);
		setEchoChar('*');
		addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (charpass.length==20) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Maksymalna iloœæ znaków w haœle wynosi 20.", "Przekroczono iloœæ znaków",
            	    JOptionPane.WARNING_MESSAGE, null);
        } 
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
 		
	}
	
	public char[] getCharPass(){
		return charpass;
	}

}

