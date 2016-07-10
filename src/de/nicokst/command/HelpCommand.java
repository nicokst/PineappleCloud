package de.nicokst.command;

import de.nicokst.bootstrap.Bootstrap;
import de.nicokst.entity.Client;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help", "Get a help list of available commands");
	}

	@Override
	public void execute(CommandExecutor executor, String[] args) {
		executor.sendMessage("Executor: " + (executor instanceof Client ? "Client" : "Console"));
		executor.sendMessage("Timestamp: " + System.currentTimeMillis());
		executor.sendMessage("Commands available: " + Bootstrap.manager.getCommands().size());
		executor.sendMessage("-----");
		for(Command c : Bootstrap.manager.getCommands()) {
			executor.sendMessage(c.getName() + "\t" + c.getDescription());
		}
		executor.sendMessage("-- END OF COMMANDS");
	}

}
