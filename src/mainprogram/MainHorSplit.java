package mainprogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class MainHorSplit extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean firststart = true;

	protected MainHorSplit() {

		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setDividerSize(10);
		setOneTouchExpandable(true);
		setEnabled(false);
		BasicSplitPaneUI ui = (BasicSplitPaneUI) getUI();
		BasicSplitPaneDivider divider = ui.getDivider();

		JButton uparrow = (JButton) divider.getComponent(1);
		uparrow.setEnabled(false);
		JButton downarrow = (JButton) divider.getComponent(0);
		uparrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				uparrow.setEnabled(false);
				downarrow.setEnabled(true);
			}
		});
		downarrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uparrow.setEnabled(true);
				downarrow.setEnabled(false);
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (firststart == true) {
					setDividerLocation(0.22);
					firststart = false;
				}
			}
		});

	}

}
