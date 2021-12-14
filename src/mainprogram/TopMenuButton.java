package mainprogram;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import Connection.Statements;

public class TopMenuButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected TopMenuButton(String icon, String name, boolean enable) {
		setIcon(new ImageIcon("img/icons/" + icon + ".png"));
		setFocusPainted(false);
		setContentAreaFilled(false);
		setEnabled(enable);
		setName(name);
		setPreferredSize(new Dimension(23, 23));
	}

	protected TopMenuButton(MainTable table, String icon) {
		setIcon(new ImageIcon("img/icons/" + icon + ".png"));
		setFocusPainted(false);
		setContentAreaFilled(false);
		setPreferredSize(new Dimension(23, 23));
	}

	protected TopMenuButton(JTextField textfield, MainTable table, CounterPanel cp) {
		setIcon(new ImageIcon("img/icons/searchicon.png"));
		setFocusPainted(false);
		setContentAreaFilled(false);
		setPreferredSize(new Dimension(23, 23));
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statements.setSortSearchText(textfield.getText());
				cp.setPagescounter();
				table.setTableData();
			}
		});
	}

	protected ActionListener getOrder(TopMenuButton ord) {
		ActionListener orderaction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statements.setOrder(getName());
				setEnabled(false);
				ord.setEnabled(true);
			}
		};
		return orderaction;
	}

}
