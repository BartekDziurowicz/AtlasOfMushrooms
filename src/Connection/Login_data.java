package Connection;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_data {
	
	private String login;
	private char[] password;
	
	protected void setLogin(JTextField tf){
		this.login=tf.getText();
	}
	
	public String getLogin(){
		return login;
	}
	
	protected void setPassword(JPasswordField pf){
		this.password=pf.getPassword();
	}
	
	protected String getPassword(){
		return String.valueOf(password);
	}
	
	protected char[] getPasswordChar(){
		return password;
	}
	
	protected void setPasswordChar(char[] newpass){
		password = newpass;
	}
	
}
