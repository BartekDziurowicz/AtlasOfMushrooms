package mainprogram;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Mainframe extends JFrame implements WindowListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	protected Mainframe(){		
		setTitle("Atlas grzybów");
		setMinimumSize(new Dimension(800, 600));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(getSize());
		setMaximumSize(new Dimension(1600,1200));		
		setLocationRelativeTo(null);	
		Image icon = new ImageIcon("img/logo.png").getImage();
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
		
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Object[] options = {"   Tak   ", "   Nie   "};
		int exit = JOptionPane.showOptionDialog(this, "Czy na pewno chcesz zamkn¹æ program?", "Wyjœcie", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		switch(exit){
			case 0: System.exit(0); break;
			default: break;
		}		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub		
	}		

}
