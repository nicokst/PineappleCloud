package de.nicokst.bootstrap;

import java.util.Arrays;
import java.util.List;

import de.nicokst.action.Action00Login;
import de.nicokst.command.AuthCommand;
import de.nicokst.command.CloudManager;
import de.nicokst.command.HelpCommand;
import de.nicokst.command.PingCommand;
import de.nicokst.command.StopCommand;
import de.nicokst.config.CloudConfiguration;
import de.nicokst.console.ConsoleThread;
import de.nicokst.db.MySQL;
import de.nicokst.io.CloudServer;

public class Bootstrap {

	public static CloudManager manager;
	public static CloudConfiguration config;
	public static MySQL mysql;
	public static CloudServer server;

	public static void main(String[] args) {
		config = new CloudConfiguration();
		config.init();
		System.out.println("CONFIGURATION INIT...");
		System.out.println("cofig.txt={");
		for (String entry : config.keySet()) {
			System.out.println("\t" + entry + "=" + config.getValue(entry));
		}
		System.out.println("}");
		mysql = new MySQL(config.getValue("sql-hostname"), config.getValue("sql-username"),
				config.getValue("sql-password"), config.getValue("sql-database"));
		mysql.connect();
		if (!mysql.isConnected()) {
			System.out.println("** ERROR -> PineappleCloud requires a SQL-Connection");
			System.exit(0);
		}
		mysql.init();
		int port = Integer.valueOf(config.getValue("port"));
		List<String> params = Arrays.asList(args);
		if (params.contains("--port") && params.size() >= 2) {
			int index = params.indexOf(new String("--port"));
			String value = params.get(index + 1);
			port = Integer.valueOf(value);
		}
		server = new CloudServer(port);
		manager = new CloudManager(server);
		/*
		 * -- COMMANDS --
		 */
		manager.registerCommand("ping", new PingCommand());
		manager.registerCommand("help", new HelpCommand());
		manager.registerCommand("stop", new StopCommand());
		manager.registerCommand("auth", new AuthCommand());
		System.out.println("Registered " + manager.getCommands().size() + " command(s)");
		
		
		/*
		 * -- ACTIONS --
		 */
		
		manager.registerAction(00, new Action00Login());
		
		new Thread(() -> {
			server.start();
		}).start();
		new ConsoleThread().start();
	}

}
