package de.nicokst.action;

public abstract class Action {
	
	private int id;
	
	public Action(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
