package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class Login_ps_field extends JPasswordField implements FocusListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Login_ps_field(){
		setText("Wpisz has³o");
		setTransferHandler(null);
		setBounds(5, 45, 215, 25);
		setForeground(Color.LIGHT_GRAY);
		setHorizontalAlignment(SwingConstants.RIGHT);
		setFont(new Font("Calibri",Font.ITALIC,14));
		setEchoChar((char)0);		
		addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (getEchoChar() == 0){
			setText("");
			setEchoChar('*');}
		setForeground(Color.BLACK);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(new Font("Calbiri",Font.PLAIN,14));
		setBackground(Color.WHITE);	
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (getPassword().length == 0){
			setText("Wpisz has³o"); 
			setEchoChar((char)0);
			setForeground(Color.LIGHT_GRAY);
			setHorizontalAlignment(SwingConstants.RIGHT);
			setFont(new Font("Calibri",Font.ITALIC,14));}
		setBackground(Color.WHITE);		
	}

}
