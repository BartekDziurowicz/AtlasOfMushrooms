package mainprogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;

import Connection.Statements;

public class SortButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected SortButton(JMenu menu, String table_name, int i, String tooltip, MainTable mt, CounterPanel cp) {

		setFocusPainted(false);
		ImageIcon iconpanel = new ImageIcon("img/" + table_name + "/" + i + ".png");
		// try {
		// setIcon(new
		// ImageIcon(ImageIO.read(ResourceLoader.load(table_name+"/"+i+".png"))));
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		setIcon(iconpanel);
		setToolTipText(tooltip);
		setName(table_name + "," + i);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setIcon(
						new ImageIcon(iconpanel.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
				menu.setToolTipText(getToolTipText());

				Statements.changeTableDataValue(getName());
				Statements.setSortWhereStatement();
				cp.setPagescounter();
				mt.setTableData();
			}

		});

	}

}
