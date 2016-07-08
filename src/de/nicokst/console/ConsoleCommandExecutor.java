package de.nicokst.console;

import de.nicokst.command.CommandExecutor;

public class ConsoleCommandExecutor implements CommandExecutor {

	@Override
	public void sendMessage(String message) {
		System.out.println(message);
	}

}
