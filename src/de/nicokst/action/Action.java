package de.nicokst.action;

import de.nicokst.entity.Client;

public abstract class Action {
	
	private int id;
	
	public Action(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public abstract void execute(Client client);
	
}
