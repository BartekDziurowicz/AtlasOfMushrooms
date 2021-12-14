package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login_tx_field extends JTextField implements FocusListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Login_tx_field(){
		setText("Wpisz PESEL");
		setTransferHandler(null);
		setBounds(5, 10, 215, 25);
		setForeground(Color.LIGHT_GRAY);
		setHorizontalAlignment(SwingConstants.RIGHT);
		setFont(new Font("Calibri",Font.ITALIC,14));
		addFocusListener(this);
		addKeyListener(this);
	}	

	@Override
	public void focusGained(FocusEvent e) {
		if (getText().equals("Wpisz PESEL")){
			setText("");
			setForeground(Color.BLACK);
			setHorizontalAlignment(SwingConstants.LEFT);
			setFont(new Font("Calbiri",Font.PLAIN,14));}
		setBackground(Color.WHITE);		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (getText().length() == 0){
			setText("Wpisz PESEL");
			setForeground(Color.LIGHT_GRAY);
			setHorizontalAlignment(SwingConstants.RIGHT);
			setFont(new Font("Calibri",Font.ITALIC,14));}
		setBackground(Color.WHITE);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
		 if (!Character.isDigit(e.getKeyChar()) || getText().length()>=11) {
			  e.consume();
	        }
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
