package mainprogram;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import Connection.Connect;
import Connection.Statements;
import net.proteanit.sql.DbUtils;

public class SortMenu extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SortMenu(String name, String table_name){
		
		JTable data = new JTable();	
		setTableData(table_name, data);
		Border titledborder = BorderFactory.createTitledBorder(name);
		((javax.swing.border.TitledBorder)titledborder).setTitleFont(new Font("Calibri", Font.PLAIN,11));
		setBorder(new CompoundBorder(
			    titledborder,
			    BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		setBorder(titledborder);
		setPreferredSize(new Dimension(60,50));
		ImageIcon icon = new ImageIcon(new ImageIcon("img/"+table_name+"/1.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		
		JMenu m = new JMenu();

		m.setIcon(icon);
		
		JMenuItem im = new JMenuItem();
		JPanel icon_panel = new JPanel(new GridLayout(3, data.getRowCount()/3+1));
		
		
		
		im.add(icon_panel);
		
		m.add(im);
		
		this.add(m);
	}
	
	private void setTableData(String table_name, JTable data){
		Statements.setStatement("SELECT "+table_name+".* FROM "+table_name+";");
		Connect.getConnection(Statements.getStatement(), true);
		try {			
			data.setModel(DbUtils.resultSetToTableModel(Connect.getResult()));			
		} finally {
			Connect.closeConnection();
		}
	}
	
	

}
