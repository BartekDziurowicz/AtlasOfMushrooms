package mainprogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Connection.Connect;
import Connection.Statements;

public class CounterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TopMenuButton left, right;
	private JLabel counterlabel;
	private int leftpagenumber, rightpagenumber, rowscount;

	protected CounterPanel(MainTable table) {
		left = new TopMenuButton(table, "lefticon");
		right = new TopMenuButton(table, "righticon");
		left.setName("left");
		right.setName("right");
		left.addActionListener(pagingAction(table, left));
		right.addActionListener(pagingAction(table, right));
		counterlabel = new JLabel();
		add(left);
		add(counterlabel);
		add(right);
		setPagescounter();
		// setTextLabel();
	}

	protected void setPagescounter() {
		Statements.setStatement(
				"SELECT COUNT(1) FROM (SELECT grzyb.ID_grzyb, CONCAT(rodzaj.Rodzaj,' ',grzyb.nazwa_lacinska) as `Nazwa lacinska`, grzyb.`Nazwa polska`, owocnik_typ.`owocnik typ` as `Owocnik`, hymenofor_typ.`Hymenofor typ` as `Hymenofor` FROM rodzaj inner join grzyb using(ID_rodzaj) inner join owocnik_typ ON grzyb.owocnik_typ=owocnik_typ.ID_owocnik_typ INNER JOIN hymenofor_typ ON grzyb.hymenofor_typ=hymenofor_typ.ID_hymenofor_typ"
						+ Statements.getSortWhereStatement() + Statements.getSortSearchText() + ") AS `rows`;");
		Connect.getConnection(Statements.getStatement(), true);
		try {
			while (Connect.getResult().next()) {
				setRowsCount(Connect.getResult().getInt(1));
			}
			setRightPageNumber();
			setLeftPageNumber(1);
			setTextLabel();
			Statements.setOffset((getLeftPageNumber() - 1) * Statements.getLimit());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			Connect.closeConnection();
		}
	}

	private TopMenuButton getLeftButton() {
		return left;
	}

	private TopMenuButton getLRightButton() {
		return right;
	}

	private void setTextLabel() {
		counterlabel.setText(getLeftPageNumber() + "/" + getRightPageNumber());

		if (getLeftPageNumber() < 2) {
			getLeftButton().setEnabled(false);
		}
		if (getRightPageNumber() == 1) {
			getLRightButton().setEnabled(false);
		} else {
			getLRightButton().setEnabled(true);
		}
	}

	private void setRowsCount(int rows) {
		rowscount = rows;
	}

	private int getRowsCount() {
		return rowscount;
	}

	private void setLeftPageNumber(int i) {
		leftpagenumber = i;
	}

	private int getLeftPageNumber() {
		return leftpagenumber;
	}

	private void setRightPageNumber() {
		if (getRowsCount() == 0) {
			rightpagenumber = 1;
		} else {
			if (getRowsCount() % Statements.getLimit() > 0) {
				rightpagenumber = getRowsCount() / Statements.getLimit() + 1;
			} else {
				rightpagenumber = getRowsCount() / Statements.getLimit();
			}
		}
	}

	private int getRightPageNumber() {
		return rightpagenumber;
	}

	// private void setOffset() {
	// Statements.setOffset((getLeftPageNumber() - 1) * Statements.getLimit());
	// }

	private ActionListener pagingAction(MainTable mt, TopMenuButton tmb) {
		ActionListener sortingaction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tmb.getName().equals("right")) {
					setLeftPageNumber(getLeftPageNumber() + 1);
				} else {
					setLeftPageNumber(getLeftPageNumber() - 1);
				}
				setTextLabel();
				Statements.setOffset((getLeftPageNumber() - 1) * Statements.getLimit());
				mt.setTableData();
				if (getLeftPageNumber() > 1) {
					left.setEnabled(true);
				} else {
					left.setEnabled(false);
				}
				if (getLeftPageNumber() < getRightPageNumber()) {
					right.setEnabled(true);
				} else {
					right.setEnabled(false);
				}
				System.out.println();
				Statements.getOffset();
			}
		};
		return sortingaction;
	}
}
