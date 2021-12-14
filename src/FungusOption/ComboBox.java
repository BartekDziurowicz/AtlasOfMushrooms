package FungusOption;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Connection.Connect;
import Connection.Statements;

public class ComboBox extends JComboBox<Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] taksonomia = {"typ","podtyp","klasa","podklasa","rzad","rodzina","rodzaj"};
	private String where = "";

	protected ComboBox(){
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
	}
	
	protected void setComboBoxStart(int table){
				
				Statements.setStatement("SELECT "+taksonomia[table]+" FROM "+taksonomia[table]+" ORDER BY "+taksonomia[table]+" ASC");
				// SELECT rodzaj.ID_rodzaj FROM rodzaj WHERE rodzaj.ID_rodzina = (SELECT rodzina.ID_rodzina FROM rodzina WHERE rodzina='Boletaceae')
				Connect.getConnection(Statements.getStatement(), true);
				List<String> cblist = new ArrayList<String>();
				try {
					while (Connect.getResult().next()) {
						cblist.add(Connect.getResult().getString(1));
					}
				} catch (SQLException f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
					f.printStackTrace();
				} finally {
					Connect.closeConnection();
					setModel(new DefaultComboBoxModel<Object>(cblist.toArray()));
				}		
	}	
	
	
	protected ActionListener setComboBoxAction(int table, ComboBox nextcb){
		ActionListener orderaction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				where = String.valueOf(getSelectedItem());
				
				Statements.setStatement("Select "+taksonomia[table]+" FROM "+taksonomia[table]+" WHERE "+taksonomia[table]+".ID_"+taksonomia[table-1]+" = (SELECT "+taksonomia[table-1]+".ID_"+taksonomia[table-1]+" FROM "+taksonomia[table-1]+" WHERE "+taksonomia[table-1]+"='"+where+"') ORDER BY "+taksonomia[table]+" ASC");
				// SELECT Podtyp.Podtyp FROM podtyp WHERE Podtyp.ID_typ = (SELECT Typ.ID_typ FROM Typ WHERE Typ='Basidiomycota')
				Connect.getConnection(Statements.getStatement(), true);
				List<String> cblist = new ArrayList<String>();
				try {
					while (Connect.getResult().next()) {
						cblist.add(Connect.getResult().getString(1));
					}
				} catch (SQLException f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
					f.printStackTrace();
				} finally {
					Connect.closeConnection();
					nextcb.setModel(new DefaultComboBoxModel<Object>(cblist.toArray()));
				}
			}				
		};
		return orderaction;
	}
	
	protected ActionListener setComboBoxEnd(Fungus fungus, BasicPanel bp){//(int table, ComboBox prev, Fungus fungus, BasicPanel bp)
		ActionListener orderaction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//where = String.valueOf(prev.getSelectedItem());
				String selected = String.valueOf(getSelectedItem());
				Statements.setStatement("SELECT rodzaj.ID_rodzaj, rodzaj.rodzaj FROM rodzaj WHERE rodzaj.rodzaj='"+selected+"'");
				//SELECT rodzaj.ID_rodzaj, rodzaj.rodzaj FROM rodzaj WHERE rodzaj.rodzaj = 'Boletus';
				//Statements.setStatement("Select "+taksonomia[table]+".ID_rodzaj, Rodzaj.rodzaj FROM "+taksonomia[table]+" WHERE "+taksonomia[table]+".ID_"+taksonomia[table-1]+" = (SELECT "+taksonomia[table-1]+".ID_"+taksonomia[table-1]+" FROM "+taksonomia[table-1]+" WHERE "+taksonomia[table-1]+"='"+where+"')");
				//SELECT rodzaj.ID_rodzaj FROM rodzaj WHERE rodzaj.ID_rodzina = (SELECT rodzina.ID_rodzina FROM rodzina WHERE rodzina.Rodzina ='Boletaceae')
				Connect.getConnection(Statements.getStatement(), true);
				
				try {
					while (Connect.getResult().next()) {
						int id = Connect.getResult().getInt(1);
						String rodzaj = Connect.getResult().getString(2); 
						fungus.setID_rodzaj(id);
						bp.getLatin_first().setText(rodzaj);						
					}
				} catch (SQLException f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
					f.printStackTrace();
				} finally {
					Connect.closeConnection();
					
				}	
			}				
		};
		return orderaction;
	}	


}
