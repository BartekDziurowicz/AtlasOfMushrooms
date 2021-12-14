package mainprogram;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import Connection.Connect;
import Connection.Statements;
import net.proteanit.sql.DbUtils;

public class MainTable extends JTable implements ListSelectionListener, MouseListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MainTable() {
		setDefaultEditor(Object.class, null);
		setFocusable(false);
		getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAutoCreateRowSorter(false);
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(true);
		getSelectionModel().addListSelectionListener(this);
		addMouseListener(this);
		setTableHeader();
	}

	private void setTableHeader() {
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int col = getTableHeader().columnAtPoint(e.getPoint());
				if (getTableHeader().getCursor().getType() == Cursor.E_RESIZE_CURSOR)
					e.consume();
				else {
					if (getColumnName(col).contains("Nazwa")) {
						setColumnSelectionAllowed(true);
						setRowSelectionAllowed(false);
						clearSelection();
						setColumnSelectionInterval(col, col);
						Statements.setColumnName(getColumnName(col));
					}

				}
			}
		});
	}

	public void setTableData() {// (JScrollPane sc){
		Statements.setStatement(
				"SELECT grzyb.ID_grzyb, CONCAT(rodzaj.Rodzaj,' ',grzyb.nazwa_lacinska) as `Nazwa lacinska`, grzyb.`Nazwa polska`, owocnik_typ.`owocnik typ` as `Owocnik`, hymenofor_typ.`Hymenofor typ` as `Hymenofor` FROM rodzaj inner join grzyb using(ID_rodzaj) inner join owocnik_typ ON grzyb.owocnik_typ=owocnik_typ.ID_owocnik_typ INNER JOIN hymenofor_typ ON grzyb.hymenofor_typ=hymenofor_typ.ID_hymenofor_typ"
						+ Statements.getSortWhereStatement() + Statements.getSortSearchText() + " ORDER BY `"
						+ Statements.getColumnName() + "` " + Statements.getOrder() + " LIMIT " + Statements.getLimit()
						+ " OFFSET " + Statements.getOffset());
		Connect.getConnection(Statements.getStatement(), true);
		try {
			setModel(DbUtils.resultSetToTableModel(Connect.getResult()));
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// sc.getVerticalScrollBar().setValue(0);
					// sc.getHorizontalScrollBar().setValue(0);
				}
			});
			setColumnSelectionAllowed(false);
			setRowSelectionAllowed(true);
			// if (getRowCount()>0){
			// setRowSelectionInterval(0, 0);
			// }
			getColumnModel().getColumn(0).setWidth(0);
			getColumnModel().getColumn(0).setMinWidth(0);
			getColumnModel().getColumn(0).setMaxWidth(0);

		} finally {
			Connect.closeConnection();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(true);
		Statements.setIDGrzyb((Integer) getValueAt(getSelectedRow(), 0));
		// Statements.setColumnName("");
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
