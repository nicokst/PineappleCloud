package de.nicokst.io;

import java.util.List;
import java.util.UUID;

import de.nicokst.entity.Client;

public abstract class Server {

	private int port;

	public Server(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public abstract void start();

	public abstract void stop();

	public abstract void broadcast(String broadcast);

	public abstract void kickall(String message);

	public abstract void kickall();

	public abstract List<Client> getClients();

	public abstract Client matchClient(String uuid);

	public abstract Client matchClient(UUID uuid);
	

}
