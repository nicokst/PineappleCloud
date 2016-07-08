package de.nicokst.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	private String hostname, username, password, database;

	public MySQL(String hostname, String username, String password, String database) {
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.database = database;
	}

	public String getDatabase() {
		return database;
	}

	public String getHostname() {
		return hostname;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void connect() {
		try {
			DriverManager.getConnection("jdbc:mysql://" + hostname + ":3306/" + database, username, password);
			System.out.println("[SQL] Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
