package top.fzqblog.po.vo;

public class State {
	private boolean opened;
	
	private boolean selected;
	
	public State() {

	}
	
	public State(boolean opened, boolean selected) {
		this.opened = opened;
		this.selected = selected;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
