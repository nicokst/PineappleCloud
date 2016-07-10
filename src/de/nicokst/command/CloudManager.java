package de.nicokst.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.nicokst.action.Action;
import de.nicokst.io.Server;

public class CloudManager {

	public static Map<String, Command> commandmap = new HashMap<String, Command>();
	public static Map<Integer, Action> actionmap = new HashMap<Integer, Action>();

	private Server server;

	public CloudManager(Server server) {
		this.server = server;
	}

	public Server getServer() {
		return server;
	}

	public void registerCommand(String name, Command command) {
		if (commandmap.containsKey(name.toLowerCase())) {
			commandmap.remove(name.toLowerCase());
		}
		commandmap.put(name.toLowerCase(), command);

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

	public void registerAction(int id, Action action) {
		if (actionmap.containsKey(id)) {
			actionmap.remove(id);
		}
		actionmap.put(id, action);
	}

	public Action getAction(int id) {
		return actionmap.get(id);
	}

	public boolean isAction(int id) {
		return actionmap.containsKey(id);
	}

}
