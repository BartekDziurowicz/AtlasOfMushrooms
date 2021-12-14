package FungusOption;

import javax.swing.JOptionPane;

import Connection.Connect;
import Connection.Statements;
import ftp.FTPConnection;
import mainprogram.MainTable;

public class DeletePosition {
	
	public DeletePosition(MainTable mt){
		
		if (mt.isRowSelected(mt.getSelectedRow())){
			Object[] options = {"   Tak   ", "   Nie   "};
			int exit = JOptionPane.showOptionDialog(mt, "Czy chcesz usun¹æ wybran¹?", "Usuwanie pozycji", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			switch(exit){
				case 0: deleteListener(mt); 
				break;
				default: break;
			}		
		} else {
			JOptionPane.showMessageDialog(mt, "Wybierz pozycjê do usuniêcia.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void deleteListener(MainTable mt){
		int position = (Integer) mt.getValueAt(mt.getSelectedRow(), 0);
		Statements.setStatement("DELETE FROM grzyb WHERE ID_grzyb="+position);
		Connect.getConnection(Statements.getStatement(), false);	
		Connect.closeConnection();
		//String[] names = mt.getValueAt(mt.getSelectedRow(), 1).toString().split(" ");
		//StringBuilder sb = new StringBuilder("");
		//for(int i=0;i<names.length;i++){
		//	sb.append(names[i]+"_");
		//}
		//if( sb.length() > 0 ){
		//	sb.deleteCharAt(sb.length() - 1 );
		//}            
		//System.out.println(sb);
		String name = mt.getValueAt(mt.getSelectedRow(), 1).toString();
		FTPConnection.deletePhoto(name.toString());
		mt.setTableData();
	}
	
}
