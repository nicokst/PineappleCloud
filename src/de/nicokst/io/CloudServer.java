package de.nicokst.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.UUID;

import de.nicokst.entity.Client;

public class CloudServer extends Server {

	private ServerSocket server;
	private boolean running;

	public CloudServer(int port) {
		super(port);
	}

	@Override
	public void start() {
		try {
			server = new ServerSocket(getPort());
			running = true;
			System.out.println("Server is now listening on 0.0.0.0:" + getPort());
			while (running) {
				Socket client = server.accept();
				System.out.println("A new client connected (" + client.getLocalAddress().getHostName() + ":"
						+ client.getPort() + ")");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcast(String broadcast) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kickall(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kickall() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Client> getClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client matchClient(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client matchClient(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
