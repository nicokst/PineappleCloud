package de.nicokst.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CloudConfiguration {

	private File f = new File("config.txt");

	public void init() {
		if (!f.exists()) {
			try {
				f.createNewFile();
				PrintWriter pw = new PrintWriter(new FileWriter(f), true);
				pw.println("sql-hostname=localhost");
				pw.println("sql-username=username");
				pw.println("sql-password=password");
				pw.println("sql-database=database");
				pw.println("port=4484");
				pw.println("greeting=Hey %s!");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
