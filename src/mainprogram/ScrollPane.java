package mainprogram;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;

public class ScrollPane extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ScrollPane(JTree tree) {
		getViewport().add(tree);
	}

	protected ScrollPane(JTable table) {
		getViewport().add(table);
	}

	protected ScrollPane(InfoPanel infopanel) {
		// setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		// setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		getViewport().add(infopanel);
	}

}
