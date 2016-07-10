package de.nicokst.command;

import de.nicokst.bootstrap.Bootstrap;
import de.nicokst.tools.Utils;

public class StopCommand extends Command {

	public StopCommand() {
		super("stop", "Close the server");
	}

	@Override
	public void execute(CommandExecutor executor, String[] args) {
		String text = "";
		if(args.length > 0) {
			text = Utils.join(args);
		} else {
			text = "Server was shut down.";
		}
		Bootstrap.server.kickall(text);
		try {
			wait(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Bootstrap.server.stop();
	}

}
