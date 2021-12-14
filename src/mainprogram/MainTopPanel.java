package mainprogram;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MainTopPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel topleft, topright, bottomleft, bottomright;
	private Box box, boxtop, boxbottom;

	protected MainTopPanel() {
		setLayout(new GridLayout());
		box = new Box(BoxLayout.Y_AXIS);
		boxtop = new Box(BoxLayout.X_AXIS);
		boxbottom = new Box(BoxLayout.X_AXIS);
		boxtop.setAlignmentY(LEFT_ALIGNMENT);
		boxbottom.setAlignmentY(RIGHT_ALIGNMENT);
		box.add(boxtop);
		box.add(boxbottom);
		boxtop.add(setTopLeft());
		boxtop.add(setTopRight());
		boxbottom.add(setBottomLeft());
		boxbottom.add(setBottomRight());
		add(box);
	}

	private JPanel setTopLeft() {
		topleft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		return topleft;
	}

	private JPanel setBottomLeft() {
		bottomleft = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
		return bottomleft;
	}

	private JPanel setBottomRight() {
		bottomright = new JPanel(new FlowLayout(FlowLayout.RIGHT, 1, 1));
		return bottomright;
	}

	private JPanel setTopRight() {
		topright = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		return topright;
	}

	protected void addToTopLeft(Component comp) {
		topleft.add(comp);
	}

	protected void addToBottomLeft(Component comp) {
		bottomleft.add(comp);
	}

	protected void addToTopRight(Component comp) {
		topright.add(comp);
	}

	protected void addToBottomRight(Component comp) {
		bottomright.add(comp);
	}

}
