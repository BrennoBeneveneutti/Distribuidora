package main;

import controller.LoginController;
import view.JanelaLogin;

/**
 * Ponto de entrada: abre PRIMEIRO a tela de login. So depois do login
 * o LoginController abre a JanelaPeca (completa ou modo cliente).
 */
public class Main {
	public static void main(String[] args) {
		JanelaLogin login = new JanelaLogin();
		new LoginController(login);
		login.setVisible(true);
	}
}