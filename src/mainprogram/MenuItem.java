package mainprogram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Connection.Connect;
import Connection.Statements;
import login.Login_start;

public class MenuItem extends JMenuItem{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected MenuItem(String icon, String name){		
		ImageIcon i = new ImageIcon("img/icons/"+icon+".png");
		setIcon(i);
		setText(name);
		setFont(new Font("Arial", Font.PLAIN, 11));
	}
	
	protected MenuItem(String icon, String name, MainTable maintable){
		ImageIcon i = new ImageIcon("img/icons/"+icon+".png");
		setIcon(i);
		setText(name);
		setFont(new Font("Arial", Font.PLAIN, 11));
	}
	
	protected ActionListener changePass(Mainframe mf){
		ActionListener changepass = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel oldlabel = new JLabel("Wpisz stare has³o:");
				ChangePasswordField oldfield = new ChangePasswordField();				
				JLabel newlabelone = new JLabel("Wpisz nowe has³o:");
				ChangePasswordField newone = new ChangePasswordField();		
				JLabel newlabeltwo = new JLabel("PotwierdŸ nowe has³o:");
				ChangePasswordField newtwo = new ChangePasswordField();
				JLabel infolabel = new JLabel();
				infolabel.setForeground(Color.RED);
				Object[] objects = {oldlabel, oldfield, newlabelone, newone, newlabeltwo, newtwo, infolabel};
				Object[] options = {" PotwierdŸ ", " Anuluj "};
				JOptionPane optionpane = new JOptionPane(objects, JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[1]);
				JDialog dialog = new JDialog(mf, "Zmiana has³a", true);
				dialog.setContentPane(optionpane);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				optionpane.addPropertyChangeListener(new PropertyChangeListener(){

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						String property = evt.getPropertyName();
						if(dialog.isVisible() && (evt.getSource()==optionpane) && (property.equals(JOptionPane.VALUE_PROPERTY))){
							String value = (String) optionpane.getValue();
							switch(value){
							case " PotwierdŸ ": 
								setNewPassword(dialog, optionpane, oldfield, newone, newtwo, infolabel);								
								break;
							case " Anuluj ": dialog.dispose(); break;
							default: break;
							}							
						}
					}
					
				});				
				dialog.pack();
				dialog.setLocationRelativeTo(mf);
				dialog.setVisible(true);			
			}				
		};
		return changepass;
	}
	
	private static void setNewPassword(JDialog dialog, JOptionPane optionpane, ChangePasswordField oldpass, ChangePasswordField newpass, ChangePasswordField newconfirm, JLabel infolabel){		
		Color color = new Color(255,160,122);	
		
		if (oldpass.getPassword().length>0){			
			oldpass.setBackground(Color.WHITE);
			if (Connect.comparePassword(oldpass.getPassword())){
				oldpass.setBackground(Color.WHITE);
				if (newpass.getPassword().length>4){
					newpass.setBackground(Color.WHITE);
					if (newconfirm.getPassword().length>0){
						newconfirm.setBackground(Color.WHITE);
						if(Arrays.equals(newconfirm.getCharPass(), newpass.getCharPass())==true){
							newconfirm.setBackground(Color.WHITE);					
							
							Statements.setStatement("SET PASSWORD = PASSWORD('"+new String(newconfirm.getPassword())+"')");
							
							try{
								Connect.getConnection(Statements.getStatement(), true);
							} finally {
								Connect.closeConnection();
							}
														
							Connect.setCharPass(newconfirm.getCharPass());
							JOptionPane.showMessageDialog(dialog, "Has³o zosta³o zmienione.", "Zmiana has³a", JOptionPane.INFORMATION_MESSAGE);
							dialog.dispose();
						} else {
							newconfirm.setBackground(color);
							infolabel.setText("Potwierdzenie has³a nie powiod³o siê.");				
							dialog.pack();
							optionpane.setValue("");
						}
					} else {
						newconfirm.setBackground(color);
						infolabel.setText("Potwierdz nowe has³o.");				
						dialog.pack();
						optionpane.setValue("");
					}
				} else {
					newpass.setBackground(color);
					infolabel.setText("Has³o musi siê sk³adaæ z min. 5 znaków.");				
					dialog.pack();
					optionpane.setValue("");
				}
			} else {
				oldpass.setBackground(color);
				infolabel.setText("Wprowadzone has³o jest niepoprawne.");			
				dialog.pack();
				optionpane.setValue("");
			}
		} else {
			oldpass.setBackground(color);
			infolabel.setText("Wprowadzone has³o jest niepoprawne.");			
			dialog.pack();
			optionpane.setValue("");
		}		
	}
	
	protected ActionListener logOut(Mainframe mf){
		ActionListener logout = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mf.dispose();
				Login_start start = new Login_start();
				start.Start();
			}	
		};
		return logout;
	}
	
	protected ActionListener close(Mainframe mf){
		ActionListener close = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowEvent closingEvent = new WindowEvent(mf, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
			}
		};
		return close;
	}
	
	
	
}
