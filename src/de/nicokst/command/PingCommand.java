package de.nicokst.command;

public class PingCommand extends Command {

	public PingCommand() {
		super("ping", "Returns Pong!");
	}

	@Override
	public void execute(CommandExecutor executor, String[] args) {
		executor.sendMessage("Pong!");
	}

}
