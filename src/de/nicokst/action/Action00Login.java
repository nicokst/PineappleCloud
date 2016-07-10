package de.nicokst.action;

import de.nicokst.bootstrap.Bootstrap;
import de.nicokst.entity.Client;

public class Action00Login extends Action {

	public Action00Login() {
		super(00);
	}

	@Override
	public void execute(Client client) {
		new String();
		client.sendMessage(String.format(Bootstrap.config.getValue("greeting"), client.getUnique().toString()));
		if(!client.isAuthed()) {
			client.sendMessage("You aren't logged in, use 'auth <password>' to authenticate");
		}
	}

}
