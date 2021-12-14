package mainprogram;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Menu extends JMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Menu(String name){
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setText(name);
		this.setOpaque(true);
	}
	
	protected Menu(MainTable mt, String table_name, JTable datatable, CounterPanel cp){
		ImageIcon icon = new ImageIcon(new ImageIcon("img/"+table_name+"/0.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		setIcon(icon);
		//setOpaque(true);
		//setFocusPainted(false);
		//setContentAreaFilled(false);
		//setBorderPainted(false);
		JPanel menupanel = new JPanel();
		GridLayout gl = new GridLayout();
		int h = 0;
		if ((datatable.getRowCount()+1)%3==0){
			h = (datatable.getRowCount()+1)/3;
		} else {
			h = datatable.getRowCount()/3+1;
		}
		gl = new GridLayout(h,3);
		menupanel.setLayout(gl);
		SortButton all = new SortButton(this, table_name, 0, "wszystkie", mt, cp);
		setToolTipText("wszystkie");
		menupanel.add(all);
		for(int i=0;i<datatable.getRowCount();i++){
			int c = (int) datatable.getValueAt(i, 0);
			SortButton sb = new SortButton(this, table_name,c , datatable.getValueAt(i, 1).toString(), mt, cp);
			menupanel.add(sb);
		}
		JMenuItem mi = new JMenuItem();
		mi.setPreferredSize(new Dimension(99,h*33));
		mi.add(menupanel);
		add(mi);
	}
	
	
	

}
