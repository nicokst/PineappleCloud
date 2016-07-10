package de.nicokst.command;

public abstract class Command {
	
	private String name, description;
	
	public Command(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public abstract void execute(CommandExecutor executor, String[] args);

}
