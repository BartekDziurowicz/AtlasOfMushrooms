package mainprogram;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Footer extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Footer(User user){
		
		setLayout(new GridLayout(1,3));
		setBorder(BorderFactory.createEmptyBorder(1, 10, 2, 10));
		add(getLabel(user.getName()+" "+user.getSurname()+" ("+user.getRole()+")", JLabel.LEFT));
		add(getLabel("", JLabel.CENTER));
		add(getLabel(user.getCompanyName(),JLabel.RIGHT));
		
	}
	
	private JLabel getLabel(String info, int horizontalAlignment){
		JLabel label = new JLabel(info, horizontalAlignment);
		label.setFont(new Font("Arial", Font.PLAIN, 9));
		return label;
	}

}
