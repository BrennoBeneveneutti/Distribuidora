package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.UsuarioBD;
import view.JanelaLogin;
import view.JanelaPeca;


public class LoginController implements ActionListener {

	private JanelaLogin login;
	private UsuarioBD bd;

	public LoginController(JanelaLogin login) {
		this.login = login;
		this.bd = new UsuarioBD();
		this.login.getBtnEntrar().addActionListener(this);
		this.login.getBtnCadastrar().addActionListener(this);
		this.login.getBtnSair().addActionListener(this);

		this.login.getTxtSenha().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.login.getBtnSair()) {
			this.login.fechar();
			return;
		}
		if (e.getSource() == this.login.getBtnCadastrar()) {
			cadastrar();
			return;
		}
		entrar();
	}

	private void entrar() {
		String usuario = this.login.getTxtUsuario().getText().trim();
		String senha = new String(this.login.getTxtSenha().getPassword());

		String tipo = bd.autenticar(usuario, senha);
		if (tipo == null) {
			this.login.mostrarErro("Usuario ou senha invalidos.");
			return;
		}

		this.login.fechar();
		abrirSistema(tipo, usuario);
	}

	private void cadastrar() {
		String usuario = this.login.getTxtUsuario().getText().trim();
		String senha = new String(this.login.getTxtSenha().getPassword());
		String tipo = ((String) this.login.getCbTipo().getSelectedItem()).toUpperCase();

		if (usuario.isEmpty() || senha.isEmpty()) {
			this.login.mostrarErro("Preencha usuario e senha para se cadastrar.");
			return;
		}

		try {
			this.bd.cadastrar(usuario, senha, tipo);
		} catch (IllegalArgumentException erro) {
			this.login.mostrarErro(erro.getMessage());
			return;
		}
		this.login.mostrarMensagem("Cadastrado como " + tipo.toLowerCase()
				+ ". Agora faca login clicando em Entrar.");
	}

	private void abrirSistema(String tipo, String usuario) {
		JanelaPeca view = new JanelaPeca();
		if (tipo.equals("CLIENTE")) {
			view.modoCliente();
		}
		view.setTitle(view.getTitle() + " - " + tipo.toLowerCase() + ": " + usuario);
		new PecaController(new model.PecaBD(), view).iniciarTela();
	}
}