package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * VIEW - Tela de Login modernizada com suporte a modo escuro/claro suave.
 */
public class JanelaLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JComboBox<String> cbTipo;
	private JButton btnEntrar;
	private JButton btnCadastrar;
	private JButton btnSair;

	// Cores do tema
	private final Color COLOR_BG = new Color(245, 247, 250);
	private final Color COLOR_PANEL = new Color(255, 255, 255);
	private final Color COLOR_PRIMARY = new Color(37, 99, 235);
	private final Color COLOR_TEXT = new Color(30, 41, 59);
	private final Color COLOR_MUTED = new Color(100, 116, 139);

	public JanelaLogin() {
		setTitle("Distribuidora de Autopeças - Autenticação");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 340);
		setMinimumSize(new Dimension(380, 320));
		setLocationRelativeTo(null);
		getContentPane().setBackground(COLOR_BG);

		// Painel Principal com margens generosas
		JPanel mainPanel = new JPanel(new BorderLayout(0, 15));
		mainPanel.setBackground(COLOR_BG);
		mainPanel.setBorder(new EmptyBorder(20, 25, 20, 25));

		// CABEÇALHO (Título estilizado)
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setOpaque(false);
		
		JLabel lblTitle = new JLabel("Acesso ao Sistema", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setForeground(COLOR_TEXT);
		
		JLabel lblSubtitle = new JLabel("Informe suas credenciais para continuar", SwingConstants.CENTER);
		lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSubtitle.setForeground(COLOR_MUTED);

		headerPanel.add(lblTitle, BorderLayout.NORTH);
		headerPanel.add(lblSubtitle, BorderLayout.SOUTH);
		mainPanel.add(headerPanel, BorderLayout.NORTH);

		// FORMULÁRIO (GridBagLayout)
		JPanel formulario = new JPanel(new GridBagLayout());
		formulario.setBackground(COLOR_PANEL);
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
				new EmptyBorder(15, 15, 15, 15)
		));

		GridBagConstraints g = new GridBagConstraints();
		g.insets = new Insets(6, 6, 6, 6);
		g.fill = GridBagConstraints.HORIZONTAL;

		Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
		
		txtUsuario = estilizarTextField(new JTextField());
		txtSenha = estilizarPasswordField(new JPasswordField());
		cbTipo = new JComboBox<>(new String[] { "cliente", "vendedor" });
		cbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbTipo.setBackground(Color.WHITE);

		// Linha 0: Usuário
		g.gridx = 0; g.gridy = 0; g.weightx = 0;
		JLabel lblUser = new JLabel("Usuário:");
		lblUser.setFont(labelFont);
		lblUser.setForeground(COLOR_TEXT);
		formulario.add(lblUser, g);

		g.gridx = 1; g.weightx = 1;
		formulario.add(txtUsuario, g);

		// Linha 1: Senha
		g.gridx = 0; g.gridy = 1; g.weightx = 0;
		JLabel lblPass = new JLabel("Senha:");
		lblPass.setFont(labelFont);
		lblPass.setForeground(COLOR_TEXT);
		formulario.add(lblPass, g);

		g.gridx = 1; g.weightx = 1;
		formulario.add(txtSenha, g);

		// Linha 2: Tipo de Acesso
		g.gridx = 0; g.gridy = 2; g.weightx = 0;
		JLabel lblTipo = new JLabel("Tipo (p/ cadastro):");
		lblTipo.setFont(labelFont);
		lblTipo.setForeground(COLOR_TEXT);
		formulario.add(lblTipo, g);

		g.gridx = 1; g.weightx = 1;
		formulario.add(cbTipo, g);

		mainPanel.add(formulario, BorderLayout.CENTER);

		// BARRAS DE BOTÕES (Rodapé)
		btnEntrar = criarBotao("Entrar", COLOR_PRIMARY, Color.WHITE);
		btnCadastrar = criarBotao("Cadastrar", new Color(241, 245, 249), COLOR_TEXT);
		btnSair = criarBotao("Sair", new Color(254, 226, 226), new Color(220, 38, 38));

		JPanel barra = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
		barra.setOpaque(false);
		barra.add(btnCadastrar);
		barra.add(btnSair);
		barra.add(btnEntrar);

		mainPanel.add(barra, BorderLayout.SOUTH);
		add(mainPanel);
	}

	private JTextField estilizarTextField(JTextField field) {
		field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		field.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 8, 6, 8)
		));
		return field;
	}

	private JPasswordField estilizarPasswordField(JPasswordField field) {
		field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		field.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 8, 6, 8)
		));
		return field;
	}

	private JButton criarBotao(String texto, Color bg, Color fg) {
		JButton btn = new JButton(texto);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn.setBackground(bg);
		btn.setForeground(fg);
		btn.setFocusPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(bg.darker(), 1),
				BorderFactory.createEmptyBorder(8, 14, 8, 14)
		));
		return btn;
	}

	public JTextField getTxtUsuario() { return txtUsuario; }
	public JPasswordField getTxtSenha() { return txtSenha; }
	public JComboBox<String> getCbTipo() { return cbTipo; }
	public JButton getBtnEntrar() { return btnEntrar; }
	public JButton getBtnCadastrar() { return btnCadastrar; }
	public JButton getBtnSair() { return btnSair; }

	public void mostrarMensagem(String texto) {
		JOptionPane.showMessageDialog(this, texto, "Informação", JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostrarErro(String texto) {
		JOptionPane.showMessageDialog(this, texto, "Atenção", JOptionPane.WARNING_MESSAGE);
	}

	public void fechar() {
		dispose();
	}
}