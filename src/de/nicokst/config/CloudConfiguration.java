package de.nicokst.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
	
	public boolean contains(String key) {	
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			List<String> keys = new ArrayList<String>();
			while((line = reader.readLine()) != null) {
				keys.add(line.split("=")[0]);
			}
			reader.close();
			return keys.contains(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getValue(String key) {
		String out = "";
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.startsWith(key + "=")) {
					return line.substring(key.length() + 1);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return out;
	}
	
	public List<String> keySet() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			List<String> keys = new ArrayList<String>();
			while((line = reader.readLine()) != null) {
				keys.add(line.split("=")[0]);
			}
			reader.close();
			return keys;
		} catch (Exception e) {
		}
		return null;
	}

}
