package ftp;

import java.sql.SQLException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import Connection.Connect;
import Connection.Statements;

public class FTP_login_data {
	private String login;
	private char[] password;
	
	public void setFTPLogin(String login){
		this.login=login;
	}
	
	public String getFTPLogin(){
		return login;
	}
	
	protected void setFTPPassword(String encrypted){
		try {
			//this.password = decrypt(encrypted, "mYZUtY"); //"mYZ#$EpBUt@Y");
			this.password = encrypted.toCharArray();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	protected String getFTPPassword(){
		return String.valueOf(password);
	}
	
	//protected char[] getPasswordChar(){
	//	return password;
	//}
	
	//protected void setPasswordChar(char[] newpass){
	//	password = newpass;
	//}
	
	
	private static char[] decrypt(String strEncrypted,String strKey) throws Exception{
		char[] strData;
		
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
			strData=new String(decrypted).toCharArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	
	public void setFTPData(){
		Statements.setStatement("SELECT FTP FROM uzytkownicy WHERE uzytkownicy.pesel="+Connect.getLogin());
		Connect.getConnection(Statements.getStatement(), true);
		try {
			while (Connect.getResult().next()){
				setFTPLogin(Connect.getLogin());			
				//setFTPPassword(Connect.getResult().getString(1));
				setFTPPassword("BDz.1988");
				}						
		} catch (SQLException e) {		
			if (e.toString().contains("Operation not allowed after ResultSet closed")){
				
			} else {
				JOptionPane.showMessageDialog(null, e.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} finally {
			Connect.closeConnection();
		}
	}
	
}
