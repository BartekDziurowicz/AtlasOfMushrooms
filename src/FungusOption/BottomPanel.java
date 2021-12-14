package FungusOption;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton add, cancel;
	private JPanel leftpanel;
	
	protected BottomPanel(FungusFrame ff, ActionListener adding){
		leftpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//FileChooser photo = new FileChooser("Dodaj zdjêcie");	
		//leftpanel.add(photo);	
		JPanel rightpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		setLayout(new GridLayout(1,2));
		cancel = getButton(cancel, "Anuluj");
		cancel.addActionListener(cancelAction(ff));
		add = getButton(add, "Dodaj");
		add.addActionListener(adding);
		rightpanel.add(cancel);
		rightpanel.add(add);
		this.add(leftpanel);
		this.add(rightpanel);		
	}
	
	protected void setAddPhotoButton(FileChooser fc){
		leftpanel.add(fc);
	}
	
	private JButton getButton(JButton button, String name){
		button = new JButton(name);
		return button;
	}
	
	private ActionListener cancelAction(FungusFrame ff){
		ActionListener cancel = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowEvent closingEvent = new WindowEvent(ff, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
			}
		};
		return cancel;
	}	
	

}
