package de.nicokst.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.nicokst.entity.Client;

public class CloudServer extends Server {

	private ServerSocket server;
	private boolean running;

	private List<Client> clients;

	public CloudServer(int port) {
		super(port);
		clients = new ArrayList<Client>();
	}

	@Override
	public void start() {
		try {
			server = new ServerSocket(getPort());
			running = true;
			System.out.println("Server is now listening on 0.0.0.0:" + getPort());
			while (running) {
				Socket client = server.accept();
				System.out.println(
						">> NEW CONNECTION (" + client.getLocalAddress().getHostName() + ":" + client.getPort() + ")");
				Client c = new Client(client);
				clients.add(c);
				c.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		kickall("DISCONNECT_MESSAGE;Server closed");
		try {
			Thread.sleep(100);
			server.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void broadcast(String broadcast) {
		for (Client c : getClients()) {
			c.sendMessage("TEXT_MESSAGE;" + broadcast);
		}
	}

	@Override
	public void kickall(String message) {
		for (Client c : getClients()) {
			c.disconnect(message);
		}
	}

	@Override
	public void kickall() {
		for (Client c : getClients()) {
			c.disconnect();
		}
	}

	@Override
	public List<Client> getClients() {
		return clients;
	}

	@Override
	public Client matchClient(String uuid) {
		Client c = null;

		for (Client client : getClients()) {
			if (client.getUnique().toString().equalsIgnoreCase(uuid)) {
				c = client;
			}
		}

		return c;
	}

	@Override
	public Client matchClient(UUID uuid) {
		Client c = null;

		for (Client client : getClients()) {
			if (client.getUnique().equals(uuid)) {
				c = client;
			}
		}

		return c;
	}

}
