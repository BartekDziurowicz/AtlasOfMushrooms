package mainprogram;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JSplitPane;

public class MainVerSplit extends JSplitPane{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected MainVerSplit(){
		
		setOrientation(JSplitPane.VERTICAL_SPLIT);
		setDividerSize(10);
		setOneTouchExpandable(true);
		//setResizeWeight(0.40);		
		//setEnabled(false);
		addComponentListener(new ComponentAdapter(){
		    @Override
		    public void componentResized(ComponentEvent e) {		        
		            setDividerLocation(0.30);
		    }
		});
	}

}
