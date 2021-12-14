package mainprogram;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import Connection.Connect;
import Connection.Statements;

public class User {
	
	private String name, surname, mail, role, companyname;
	private int user_id, role_id;
	
	public User(){
		getUserData();
	}
	
	private void setUserID(int id){
		this.user_id=id;
	}
	
	private void setName(String name){
		this.name=name;
	}
	
	private void setSurname(String surname){
		this.surname=surname;
	}
	
	private void setMail(String mail){
		this.mail=mail;
	}
	
	private void setRole(String role){
		this.role=role;
	}
	
	private void setRoleID(int roleid){
		this.role_id=roleid;
	}
	
	private void setCompanyName(String companyname) {
		this.companyname=companyname;
	}
	
	public String getCompanyName() {
		return companyname;
	}
	
	public int getUserID(){
		return user_id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public String getMail(){
		return mail;
	}
	
	public String getRole(){
		return role;
	}
	
	public int getRoleID(){
		return role_id;
	}
	
	protected void getUserData(){
		Statements.setStatement("SELECT uzytkownicy.*, uzytkownicy_rola.Rola, firma.* FROM uzytkownicy INNER JOIN uzytkownicy_rola ON uzytkownicy.Rola=uzytkownicy_rola.ID_uzytkownicy_rola INNER JOIN firma USING(ID_firma) WHERE uzytkownicy.pesel="+Connect.getLogin());
		Connect.getConnection(Statements.getStatement(), true);
		try {
			while (Connect.getResult().next()){
				setUserID(Connect.getResult().getInt(1));
				setName(Connect.getResult().getString(2));
				setSurname(Connect.getResult().getString(3));
				setMail(Connect.getResult().getString(5));
				setRoleID(Connect.getResult().getInt(6));
				setRole(Connect.getResult().getString(11));
				setCompanyName(Connect.getResult().getString(13));
				}						
		} catch (SQLException e) {			
			JOptionPane.showMessageDialog(null, e.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			Connect.closeConnection();
		}
	}


}
