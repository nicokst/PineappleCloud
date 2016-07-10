package de.nicokst.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

	private String hostname, username, password, database;

	private Connection c;

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

	/**
	 * connect to a sql-database
	 */
	public void connect() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://" + hostname + ":3306/" + database, username, password);
			System.out.println("[SQL] Connected");
		} catch (SQLException e) {
			System.out.println("[SQL] Failed to connect, -> " + e.getMessage());
		}
	}

	/**
	 * 
	 * @return connection status
	 */
	public boolean isConnected() {
		return c != null;
	}

	/**
	 * 
	 * @return SQL Connection (if exists)
	 */
	public Connection getConnection() {
		return c;
	}

	/**
	 * close current connection
	 */
	public void close() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Perform a RESULT-QUERY
	 * @param sql query
	 * @return SQL-Statement
	 */
	public ResultSet query(String sql) {
		try {
			return getConnection().createStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Perform an UPDATE-Query
	 * @param sql query
	 */
	public void update(String sql) {
		try {
			getConnection().createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates basic tables for PineappleCloud
	 */
	public void init() {
		update("CREATE TABLE IF NOT EXISTS `auth` (`id` int(11) AUTO_INCREMENT, `uuid` text, `username` text, `password` text, `administrative` int(1), PRIMARY KEY(`id`));");
	}

}
