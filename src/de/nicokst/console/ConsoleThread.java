package de.nicokst.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import de.nicokst.bootstrap.Bootstrap;

public class ConsoleThread extends Thread {

	@Override
	public void run() {
		while (true) {
			System.out.print(">");
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					parseInput(line);
					System.out.println();
					System.out.print(">");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void parseInput(String input) {
		String command;
		if (input.contains(" ")) {
			command = input.split(" ")[0].toLowerCase();
			if (Bootstrap.manager.isCommand(command)) {
				String[] args = input.substring(command.length() + 1).split(" ");
				Bootstrap.manager.getCommand(command).execute(new ConsoleCommandExecutor(), args);
			} else {
				System.out.println("Unknown command, type 'help' for help.");
			}
		} else {
			command = input.toLowerCase();
			if (Bootstrap.manager.isCommand(command)) {
				String[] args = new String[] {};
				Bootstrap.manager.getCommand(command).execute(new ConsoleCommandExecutor(), args);
			} else {
				System.out.println("Unknown command, type 'help' for help.");
			}
		}
	}

}
