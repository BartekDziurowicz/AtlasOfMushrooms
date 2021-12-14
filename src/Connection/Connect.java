package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class Connect {

	private static Connection connection;
	private static String driver = "com.mysql.jdbc.Driver", url = "jdbc:mysql://192.168.0.2:3306/atlas_grzybow?useUnicode=true&characterEncoding=UTF-8";//bdzitnas.myqnapcloud.com:3306/:3306
	private static ResultSet result;
	private static PreparedStatement statement;
	private static Login_data login;
	
	public static void setLogin(JTextField tf, JPasswordField pf) {	
		login = new Login_data();
		login.setLogin(tf);
		login.setPassword(pf);
	}
	
	public static Connection getConnection(String astatement, boolean out){
		boolean b = out;
		try{
			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(url, login.getLogin(), login.getPassword());
			statement = connection.prepareStatement(astatement);
			if (b==true){
				result = statement.executeQuery();			
			}
			if (b==false){				
				statement.executeUpdate();				
			}					
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "B³¹d sterownika!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();			
		} catch (CommunicationsException e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Brak po³¹czenia z baz¹ danych!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();						
		} catch (Exception e) {
			if (e.toString().contains("Access denied")){
				JOptionPane.showMessageDialog(null, "Wpisano z³¹ nazwê u¿ytkownika lub has³o.\nSprawdŸ dane i spróbuj ponownie.", "Z³a nazwa u¿ytkownika lub has³o!", JOptionPane.WARNING_MESSAGE);
				
			} else {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Nieoczekiwany b³¹d!", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();	
			}		
		}		
		return connection;
	}
	
	public static void closeConnection(){
		
			try {
				connection.close();			
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "B³¹d zamykania po³¹czenia!", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

	}
	
	public static ResultSet getResult(){
		return result;
	}
	
	public static boolean comparePassword(char[] newpass){
		boolean check = false;
		if(Arrays.equals(newpass, login.getPasswordChar())==true){
			check=true;
		} else {
			check=false;
		}
		return check;
	}
	
	public static void setCharPass(char[] newpass){
		login.setPasswordChar(newpass);
	}
	
	public static String getLogin(){
		return login.getLogin();
	}

	
}
