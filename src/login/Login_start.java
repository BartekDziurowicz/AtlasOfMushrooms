package login;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Connection.Connect;
import Connection.Statements;
import ftp.FTPConnection;
import mainprogram.Start;

public class Login_start extends JFrame implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Login_start ls;
	private Login_ps_field lps = new Login_ps_field();
	private Login_tx_field ltf = new Login_tx_field();
	private Object[] objects = { ltf, lps }, options = { " Zaloguj ", " Anuluj " };
	private JDialog dialog;
	private JOptionPane optionpane;
	private Icon icon = new ImageIcon("img/logo.png");
	private Image image = new ImageIcon("img/logo.png").getImage();

	public void Start() {
		optionpane = new JOptionPane(objects, JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, icon, options,
				options[0]);
		dialog = new JDialog(this, "Logowanie", true);
		dialog.setFocusable(true);
		dialog.setIconImage(image);
		dialog.setContentPane(optionpane);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		optionpane.addPropertyChangeListener(this);
		dialog.setResizable(false);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	public static void main(String[] args) {

		ls = new Login_start();
		ls.Start();

	}

	public static void startDispose() {
		ls.setVisible(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		String property = evt.getPropertyName();
		if (dialog.isVisible() && (evt.getSource() == optionpane) && (property.equals(JOptionPane.VALUE_PROPERTY))) {
			String value = (String) optionpane.getValue();

			switch (value) {
			case " Zaloguj ":
				optionpane.setValue("");
				Connect.setLogin(ltf, lps);
				try {
					FTPConnection.setFTPconnectionData();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Statements.setStatement("SELECT * FROM uzytkownicy where uzytkownicy.PESEL=" + ltf.getText());
				Connect.getConnection(Statements.getStatement(), true);
				checkUser(dialog);				
				break;
			case " Anuluj ":
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}

	private static void checkUser(JDialog dialog) {
		try {
			if (Connect.getResult().next()) {
				new Runnable() {
					@Override
					public void run() {
						dialog.setVisible(false);						
						Start start = new Start();
						start.start();				
						
					}
				}.run();
			} else {
				JOptionPane.showMessageDialog(null, "Brak dostêpu.", "Skontaktuj siê z administratorem.",
						JOptionPane.WARNING_MESSAGE);

			}
		} catch (SQLException e1) {

			JOptionPane.showMessageDialog(null, e1.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();

		} finally {
			dialog.setVisible(false);
			Connect.closeConnection();
		}

	}

}
