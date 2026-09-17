package main;

import controller.LoginController;
import view.JanelaLogin;


public class Main {
	public static void main(String[] args) {
		JanelaLogin login = new JanelaLogin();
		new LoginController(login);
		login.setVisible(true);
	}
}