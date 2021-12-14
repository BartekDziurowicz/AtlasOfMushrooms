package FungusOption;

import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mainprogram.Mainframe;

public class FungusFrame extends JFrame implements WindowListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FungusFrame(Mainframe mf){
		setTitle("Dodawanie grzyba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(660,600);
		setResizable(false);
		setLocationRelativeTo(mf);
		Image icon = new ImageIcon("img/icons/inserticon.png").getImage();
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Object[] options = {"   Tak   ", "   Nie   "};
		int exit = JOptionPane.showOptionDialog(this, "Czy na pewno chcesz przerwaæ dodawanie pozycji?\nWprowadzone zmiany nie bêd¹ zapisane.", "Anuluj dodawanie.", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		switch(exit){
			case 0: dispose(); break;
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
