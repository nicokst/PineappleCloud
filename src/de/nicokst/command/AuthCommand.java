package de.nicokst.command;

import de.nicokst.entity.Client;

public class AuthCommand extends Command {

	public AuthCommand() {
		super("auth", "Authenticate with username + password");
	}

	@Override
	public void execute(CommandExecutor executor, String[] args) {
		if(executor instanceof Client) {
			Client c = (Client) executor;
			if(!c.isAuthed()) {
				c.auth();
				c.sendMessage("You are now logged in.");
			} else {
				c.sendMessage("You are already logged in");
			}
		} else {
			executor.sendMessage("Consoles can't login.");
		}
	}

}
