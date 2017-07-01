package ClientGui;

import javafx.scene.control.TabPane;
import projectsalmon.Semester;

public class TabManager
{
	private final static TabManager instance = new TabManager();
	public static TabManager getInstance(){return instance;}
	
	private TabPane container;
	private TabPane subContainer;
	private Object latestSelection;
	private boolean editable;
	private Semester currentSemester;
	
	public void setCurrentSemester(Semester current){this.currentSemester = current;}
	public Semester getCurrentSemester(){return this.currentSemester;}
	
	public void setSubContainer(TabPane tabPane){this.subContainer = tabPane;}
	public TabPane getSubContainer(){return subContainer;}
	
	public void setEditable(boolean editable){this.editable = editable;}
	public boolean getEditable(){return editable;}
	
	public void setLatestSelection(Object selection){latestSelection = selection;}
	public Object getLatestSelection(){return latestSelection;}
	
	public void setContainer(TabPane container){this.container = container;}
	public TabPane getContainer(){return container;}
}
