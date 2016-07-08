package de.nicokst.bootstrap;

import java.util.Arrays;
import java.util.List;

import de.nicokst.command.CloudManager;
import de.nicokst.command.PingCommand;
import de.nicokst.console.ConsoleThread;
import de.nicokst.io.CloudServer;

public class Bootstrap {
	
	public static CloudManager manager;

	public static void main(String[] args) {
//		String version = System.getProperty("java.version").split("_")[1];
//		try {
//			if (Integer.valueOf(version) < 92) {
				int port = 4484;
				List<String> params = Arrays.asList(args);
				if(params.contains("--port") && params.size() >= 2) {
					int index = params.indexOf(new String("--port"));
					String value = params.get(index + 1);
					port = Integer.valueOf(value);
				}
				CloudServer server = new CloudServer(port);
				manager = new CloudManager(server);
				manager.registerCommand("ping", new PingCommand());
				System.out.println("Registered " + manager.getCommands().size() + " command(s)");
				new Thread(() -> {
					server.start();
				}).start();
				new ConsoleThread().start();
//			} else {
//				System.out.println(
//						"** ERROR -> YOUR JAVA VERSION IS OUT OF DATE, THIS REQUIRES AT LEAST JAVA VERSION 8_92");
//				System.exit(0);
//			}
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
	}

}
