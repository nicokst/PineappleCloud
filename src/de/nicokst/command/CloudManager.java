package de.nicokst.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.nicokst.io.Server;

public class CloudManager {
	
	public static Map<String, Command> commandmap = new HashMap<String, Command>();
	
	private Server server;
	
	public CloudManager(Server server) {
		this.server = server;
	}
	
	public Server getServer() {
		return server;
	}
	
	public void registerCommand(String name, Command command) {
		if(commandmap.containsKey(name.toLowerCase())) {
			commandmap.remove(name.toLowerCase());
		} else {
			commandmap.put(name.toLowerCase(), command);
		}
	}
	
	public Collection<Command> getCommands() {
		return commandmap.values();
	}
	
	public boolean isCommand(String name) {
		return commandmap.containsKey(name.toLowerCase());
	}
	
	public Command getCommand(String name) {
		return commandmap.get(name.toLowerCase());
	}
	
	

}
