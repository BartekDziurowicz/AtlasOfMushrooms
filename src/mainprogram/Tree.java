package mainprogram;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import Connection.Connect;
import Connection.Statements;

public class Tree extends JTree implements TreeSelectionListener, TreeWillExpandListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Fungi");
	private String[] tablenames = { "root", "typ", "podtyp", "klasa", "podklasa", "rzad", "rodzina", "rodzaj" };

	protected Tree() {
		DefaultTreeModel dtm = (DefaultTreeModel) getModel();
		dtm.setRoot(root);
		setShowsRootHandles(true);
		setEditable(false);
		setRootVisible(false);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		addTreeWillExpandListener(this);
		addTreeSelectionListener(this);
	}

	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	protected void setTree() {
		Statements.setStatement("Select Typ FROM typ ORDER BY Typ ASC");
		Connect.getConnection(Statements.getStatement(), true);
		try {
			while (Connect.getResult().next()) {
				String name = Connect.getResult().getString(1);

				DefaultMutableTreeNode next = new DefaultMutableTreeNode(name);
				next.add(new DefaultMutableTreeNode("temp"));
				root.add(next);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			Connect.closeConnection();
			do {
				if (root.getLevel() == 0)
					expandPath(new TreePath(root.getPath()));
				root = root.getNextNode();
			} while (root != null);

		}

	}

	public void valueChanged(TreeSelectionEvent e) {

		if (e.getPath().getPathCount() == 9) {
			String latin_name = e.getPath().getLastPathComponent().toString();
			String parent = e.getPath().getParentPath().getLastPathComponent().toString();
			Statements.setStatement("SELECT grzyb.ID_grzyb FROM grzyb WHERE nazwa_lacinska ='" + latin_name
					+ "' AND grzyb.ID_rodzaj = (SELECT rodzaj.ID_rodzaj FROM rodzaj WHERE rodzaj.Rodzaj = '" + parent
					+ "')");
			Connect.getConnection(Statements.getStatement(), true);
			try {
				while (Connect.getResult().next()) {
					int id = Connect.getResult().getInt(1);
					Statements.setIDGrzyb(id);
				}

			} catch (SQLException ee) {
				JOptionPane.showMessageDialog(null, ee.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
				ee.printStackTrace();
			} finally {
				Connect.closeConnection();
			}
		}

	}

	@Override
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		setSelectionPath(event.getPath());
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();
		String name = "", tablename = "", columnname = "";// , columnnameid =
															// "";

		if (!node.isLeaf() && node.getLevel() > 0 && node.getLevel() < 8) {

			if (node.getFirstChild().toString().equals("temp")) {
				node.remove(0);
			}

			int level = node.getLevel();

			if (level > 0 && level < 7) {
				name = node.toString();
				tablename = tablenames[level + 1];
				columnname = "." + tablename;
			} else {
				if (level == 7) {
					name = node.toString();
					tablename = "grzyb";
					columnname = "." + "nazwa_lacinska";
				}
			}

			String tablenameparent = tablenames[level];
			String columnnameidparent = ".ID_" + tablenameparent;

			Statements.setStatement("SELECT " + tablename + columnname + " FROM " + tablename + " WHERE " + tablename
					+ columnnameidparent + "" + " = (SELECT " + tablenameparent + columnnameidparent + " FROM "
					+ tablenameparent + " WHERE " + tablenameparent + "." + tablenameparent + "='" + name
					+ "') ORDER BY " + tablename + columnname + " ASC");
			Connect.getConnection(Statements.getStatement(), true);
			try {
				while (Connect.getResult().next()) {
					String next = Connect.getResult().getString(1);
					if (level > 0 && level < 7) {
						DefaultMutableTreeNode tempnode = new DefaultMutableTreeNode("temp");
						DefaultMutableTreeNode nextnode = new DefaultMutableTreeNode(next);
						nextnode.add(tempnode);
						node.add(nextnode);
					} else {
						if (level == 7) {
							node.add(new DefaultMutableTreeNode(next));
						}
					}
				}

			} catch (SQLException ee) {
				JOptionPane.showMessageDialog(null, ee.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
				ee.printStackTrace();
			} finally {
				Connect.closeConnection();
			}
		}

	}

	@Override
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		setSelectionPath(event.getPath());
	}

}
