package mainprogram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import Connection.Connect;
import Connection.Statements;
import net.proteanit.sql.DbUtils;

public class MenuBar extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected MenuBar(){
		
	}
	
	protected MenuBar(MainTable mt,String name, String[] table_names, CounterPanel cp){	
		setBackground(new Color(238,238,238));
		Border titledborder = BorderFactory.createTitledBorder(name);
		((javax.swing.border.TitledBorder)titledborder).setTitleFont(new Font("Calibri", Font.PLAIN,11));
		setBorder(new CompoundBorder(
			    titledborder,
			    BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		setBorder(titledborder);		
		if(table_names.length<2){
			setPreferredSize(new Dimension(60,46));
		} else {			
			setSize(table_names.length*22, 46);
		}
		
		for(int i=0;i<table_names.length;i++){
			JTable datatable = new JTable();
			getData(table_names[i], datatable);
			JMenu next = new Menu(mt,table_names[i], datatable, cp);			
			add(next);
		}
	}
	
	private void getData(String table_name, JTable datatable){
		Statements.setStatement("SELECT "+table_name+".* FROM "+table_name+";");
		Connect.getConnection(Statements.getStatement(), true);
		try {			
			datatable.setModel(DbUtils.resultSetToTableModel(Connect.getResult()));			
		} finally {			
			Statements.setTableData(datatable, table_name);			
			Connect.closeConnection();			
		}
	}

}
