package de.nicokst.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

import de.nicokst.action.ActionType;
import de.nicokst.bootstrap.Bootstrap;
import de.nicokst.command.CommandExecutor;

public class Client extends Thread implements Entity, CommandExecutor {

	private Socket socket;

	private PrintWriter writer;
	private BufferedReader reader;

	private UUID uuid;

	private boolean authed;

	public Client(Socket socket) {
		this.socket = socket;
		matchUUID();
		authed = false;
	}

	public UUID getUnique() {
		return uuid;
	}

	private void matchUUID() {
		uuid = UUID.randomUUID();
	}

	public void run() {
		try {

			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			Bootstrap.manager.getAction(00).execute(this);

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("[" + getUnique() + "] " + line);
				parseInput(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void sendMessage(String message) {
		getWriter().println(ActionType.TEXT_MESSAGE.toString() + ";" + message);
	}

	public void disconnect(String message) {
		getWriter().println(ActionType.DISCONNECT_MESSAGE.toString() + ";" + message);
	}

	public void disconnect() {
		getWriter().println(ActionType.DISCONNECT);
	}

	private void parseInput(String input) {
		input = input.trim();
		String command = "";
		String[] args = new String[] {};
		if (input.contains(" ")) {
			command = input.split(" ")[0].toLowerCase();
			args = input.substring(command.length() + 1).split(" ");
		} else {
			command = input.toLowerCase().trim();
		}
		if (Bootstrap.manager.isCommand(command)) {
			Bootstrap.manager.getCommand(command).execute(this, args);
		} else {
			sendMessage("Unknown command, type 'help' for help");
		}
	}

	public boolean isAuthed() {
		return authed;
	}

	public void auth() {
		authed = true;
	}

}
