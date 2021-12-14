package Connection;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Statements {
	
	private static String statement;
	private static int idgrzyb = 1, offset=0, limit=100;
	private static String sortwherestatement="", sortsearchtext="", columnname="Nazwa lacinska", fromsearchtextfield="", order="ASC";
	private static JTable datatableall;
	
	public static void setOrder(String ord){
		order = ord;
	}
	
	public static String getOrder(){
		return order;
	}
	
	public static int getLimit(){
		return limit;
	}
	
	public static void setOffset(int o){
		offset=o;
	}
	
	public static int getOffset(){
		return offset;
	}
	
	public static void setColumnName(String name){
		columnname = name;
	}
	
	public static String getColumnName(){
		return columnname;
	}
	
	public static void setSortSearchText(String text){
		fromsearchtextfield = text;
			if (columnname.length()>0){
				if (sortwherestatement.length()==0){
					sortsearchtext = " WHERE ";
				} else {
					sortsearchtext = "AND ";
				}
				
				if (columnname.equals("Nazwa lacinska")){
					sortsearchtext=sortsearchtext + "CONCAT(rodzaj.Rodzaj,' ',grzyb.nazwa_lacinska) LIKE '%"+text+"%' ";
				} else {
					if (columnname.equals("Nazwa polska")){
						sortsearchtext=sortsearchtext+"grzyb.`Nazwa polska` LIKE '%"+text+"%' ";
					} else {
						sortsearchtext="";
					}
				}
			} else {
				sortsearchtext="";
			}
		

	}
	
	public static String getSortSearchText(){
		return sortsearchtext;
	}
	
	public static void setTable(){
		String [] names = {"table_name","value"};
		String [][] data = {};
		datatableall = new JTable(new DefaultTableModel(data, names));		
	}
	
	public static void setTableData(JTable datatable, String table_name){
		DefaultTableModel model = (DefaultTableModel) datatableall.getModel();
		model.addRow(new Object[]{table_name, 0});
	}
	
	public static void changeTableDataValue(String data){
		String[] split = data.split(",");
		int c=0;
		while(!datatableall.getValueAt(c, 0).equals(split[0])){
			c++;
		}
		DefaultTableModel model = (DefaultTableModel) datatableall.getModel();
		model.setValueAt(split[1], c, 1);
	}
	
	public static void test(){
		for (int i=0;i<datatableall.getRowCount();i++){
			System.out.println(datatableall.getValueAt(i, 0).toString()+datatableall.getValueAt(i, 1));
		}
	}
	
	public static JTable getTable(){
		return datatableall;
	}
	
	public static void setStatement(String astatement){
		statement = astatement;
	}
	
	public static String getStatement(){
		return statement;
	}
	
	public static void setIDGrzyb(int aidgrzyb){
		idgrzyb = aidgrzyb;
	}
	
	public static int getIDGrzyb(){
		return idgrzyb;
	}
	
	public static void setSortWhereStatement(){
		sortwherestatement="";		
		for(int i=0;i<datatableall.getRowCount();i++){
			String sorvalue = datatableall.getValueAt(i, 1).toString();
			int sortvalue = Integer.valueOf(sorvalue);
			if (sortwherestatement.length()==0 && sortvalue!=0){
				sortwherestatement = " WHERE "+datatableall.getValueAt(i, 0)+" REGEXP '^"+sortvalue+"$|^"+sortvalue+",|,"+sortvalue+",|^,"+sortvalue+",|,"+sortvalue+"$' ";
			} else {
				if (sortwherestatement.length()>0 && sortvalue!=0){
					sortwherestatement=sortwherestatement+"AND "+datatableall.getValueAt(i, 0)+" REGEXP '^"+sortvalue+"$|^"+sortvalue+",|,"+sortvalue+",|^,"+sortvalue+",|,"+sortvalue+"$' ";                                  
				}
			}
		}
		setSortSearchText(fromsearchtextfield);
	}
	
	public static String getSortWhereStatement(){
		return sortwherestatement;
	}
	
	

}
