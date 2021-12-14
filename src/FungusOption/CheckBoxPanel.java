package FungusOption;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Connection.Connect;
import Connection.Statements;

public class CheckBoxPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox option;
	private List<JCheckBox> checklist = new ArrayList<>();
	private ButtonGroup bg = new ButtonGroup();
	
	protected CheckBoxPanel(String table_name, String title, int width, int hight, boolean single){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		Border titledborder = BorderFactory.createTitledBorder(title);		
		((javax.swing.border.TitledBorder)titledborder).setTitleColor(new Color(240,99,34));
		setBorder(titledborder);
		setData(table_name, single);
		setPreferredSize(new Dimension(width, hight));
		setMaximumSize(new Dimension(width, hight));
		
	}
	
	private void setData(String table_name, boolean single){
		Statements.setStatement("SELECT "+table_name+".* FROM "+table_name);		
		Connect.getConnection(Statements.getStatement(), true);		
		try {
			while (Connect.getResult().next()) {				
				option = new JCheckBox(Connect.getResult().getString(2));
				option.setName(String.valueOf(Connect.getResult().getInt(1)));
				if (single == false){
					bg.add(option);
				}				
				checklist.add(option);
				option.setFont(new Font("Calibri", Font.PLAIN, 12));
				add(option);				
			}
		} catch (SQLException f) {
			JOptionPane.showMessageDialog(null, f.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
			f.printStackTrace();
		} finally {
			Connect.closeConnection();			
		}	
	}
	
	protected List<JCheckBox> getCheckList(){
		return checklist;
	}
	
	protected boolean isArraySelected(){
		boolean selected = false;
		for (int i=0;i<getCheckList().size();i++) {
			JCheckBox cb = (JCheckBox) getCheckList().get(i);
			if (cb.isSelected()){
				selected = true;
			}
		}	
		return selected;
	}
	
	protected int getSingleSelected(){
		int single = 0;
		for (int i=0;i<getCheckList().size();i++) {
			JCheckBox cb = (JCheckBox) getCheckList().get(i);
			if (cb.isSelected()){
				single = Integer.valueOf(cb.getName());
			}
		}
		return single;
	}
	
	protected String getMultiSelected(){
		String multi = "";
		for (int i=0;i<getCheckList().size();i++) {
			JCheckBox cb = (JCheckBox) getCheckList().get(i);
			if (cb.isSelected()){
				multi = multi +","+cb.getName();
			}
		}
		if (multi.length()>0){
			multi = multi.substring(1, multi.length());
		}		
		return multi;
	}

}
