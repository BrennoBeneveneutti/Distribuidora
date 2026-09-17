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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * VIEW - Janela Principal de Peças com visual modernizado.
 */
public class JanelaPeca extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JTextField txtQuantidade;

	private JButton btnCadastrar;
	private JButton btnAtualizar;
	private JButton btnExcluir;
	private JButton btnBuscar;
	private JButton btnLimpar;
	private JButton btnListar;
	private JButton btnVender;
	private JButton btnFechar;

	private JTable tabela;
	private DefaultTableModel modeloTabela;

	// Cores do Tema
	private final Color COLOR_BG = new Color(245, 247, 250);
	private final Color COLOR_PANEL = new Color(255, 255, 255);
	private final Color COLOR_TEXT = new Color(30, 41, 59);
	private final Color COLOR_PRIMARY = new Color(37, 99, 235);
	private final Color COLOR_DANGER = new Color(220, 38, 38);

	public JanelaPeca() {
		setTitle("Distribuidora de Autopeças - Gerenciamento de Peças");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 550);
		setMinimumSize(new Dimension(650, 450));
		setLocationRelativeTo(null);
		getContentPane().setBackground(COLOR_BG);

		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(COLOR_BG);
		mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

		// --- FORMULÁRIO (GridBagLayout) ---
		JPanel formulario = new JPanel(new GridBagLayout());
		formulario.setBackground(COLOR_PANEL);
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
				new EmptyBorder(12, 12, 12, 12)
		));

		GridBagConstraints g = new GridBagConstraints();
		g.insets = new Insets(5, 5, 5, 5);
		g.fill = GridBagConstraints.HORIZONTAL;

		txtCodigo = estilizarTextField(new JTextField());
		txtNome = estilizarTextField(new JTextField());
		txtPreco = estilizarTextField(new JTextField());
		txtQuantidade = estilizarTextField(new JTextField());

		Font labelFont = new Font("Segoe UI", Font.BOLD, 12);

		// Linha 0
		g.gridx = 0; g.gridy = 0; g.weightx = 0;
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(labelFont);
		lblCodigo.setForeground(COLOR_TEXT);
		formulario.add(lblCodigo, g);

		g.gridx = 1; g.weightx = 1;
		formulario.add(txtCodigo, g);

		// Linha 1
		g.gridx = 0; g.gridy = 1; g.weightx = 0;
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(labelFont);
		lblNome.setForeground(COLOR_TEXT);
		formulario.add(lblNome, g);

		g.gridx = 1; g.weightx = 1;
		formulario.add(txtNome, g);

		// Linha 2
		g.gridx = 0; g.gridy = 2; g.weightx = 0;
		JLabel lblPreco = new JLabel("Preço (R$):");
		lblPreco.setFont(labelFont);
		lblPreco.setForeground(COLOR_TEXT);
		formulario.add(lblPreco, g);

		g.gridx = 1; g.weightx = 1;
		formulario.add(txtPreco, g);

		// Linha 3
		g.gridx = 0; g.gridy = 3; g.weightx = 0;
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setFont(labelFont);
		lblQtd.setForeground(COLOR_TEXT);
		formulario.add(lblQtd, g);

		g.gridx = 1; g.weightx = 1;
		formulario.add(txtQuantidade, g);

		mainPanel.add(formulario, BorderLayout.NORTH);

		// --- TABELA ---
		String[] colunas = { "Código", "Nome", "Preço (R$)", "Quantidade" };
		modeloTabela = new DefaultTableModel(colunas, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabela = new JTable(modeloTabela);
		tabela.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tabela.setRowHeight(24);
		tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

		JScrollPane scrollTabela = new JScrollPane(tabela);
		scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240), 1));
		mainPanel.add(scrollTabela, BorderLayout.CENTER);

		// --- BARRA DE BOTÕES ---
		btnCadastrar = new JButton("Cadastrar");
		btnAtualizar = new JButton("Atualizar");
		btnExcluir = new JButton("Excluir");
		btnBuscar = new JButton("Buscar");
		btnLimpar = new JButton("Limpar");
		btnListar = new JButton("Listar Tudo");
		btnVender = new JButton("Vender");
		btnFechar = new JButton("Fechar");

		// Aplicando a estilização padrão em cada botão
		estilizarBotaoPeca(btnCadastrar, COLOR_PRIMARY);
		estilizarBotaoPeca(btnAtualizar, COLOR_TEXT);
		estilizarBotaoPeca(btnExcluir, COLOR_DANGER);
		estilizarBotaoPeca(btnBuscar, COLOR_TEXT);
		estilizarBotaoPeca(btnLimpar, COLOR_TEXT);
		estilizarBotaoPeca(btnListar, COLOR_TEXT);
		estilizarBotaoPeca(btnVender, new Color(16, 185, 129)); // Verde para venda
		estilizarBotaoPeca(btnFechar, COLOR_TEXT);

		JPanel barraBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
		barraBotoes.setOpaque(false);

		barraBotoes.add(btnCadastrar);
		barraBotoes.add(btnAtualizar);
		barraBotoes.add(btnBuscar);
		barraBotoes.add(btnListar);
		barraBotoes.add(btnLimpar);
		barraBotoes.add(btnVender);
		barraBotoes.add(btnExcluir);
		barraBotoes.add(btnFechar);

		mainPanel.add(barraBotoes, BorderLayout.SOUTH);
		add(mainPanel);
	}

	private JTextField estilizarTextField(JTextField field) {
		field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		field.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		return field;
	}

	// Método auxiliar para aplicar o padrão visual nos botões
	private void estilizarBotaoPeca(JButton btn, Color corTexto) {
		btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn.setFocusPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setBackground(new Color(241, 245, 249));
		btn.setForeground(corTexto);
		btn.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)
		));
	}

	// Oculta botões restritos quando logado como Cliente
	public void modoCliente() {
		btnCadastrar.setVisible(false);
		btnAtualizar.setVisible(false);
		btnExcluir.setVisible(false);
		btnVender.setVisible(false);
	}

	// Getters dos componentes
	public JTextField getTxtCodigo() { return txtCodigo; }
	public JTextField getTxtNome() { return txtNome; }
	public JTextField getTxtPreco() { return txtPreco; }
	public JTextField getTxtQuantidade() { return txtQuantidade; }

	public JButton getBtnCadastrar() { return btnCadastrar; }
	public JButton getBtnAtualizar() { return btnAtualizar; }
	public JButton getBtnExcluir() { return btnExcluir; }
	public JButton getBtnBuscar() { return btnBuscar; }
	public JButton getBtnLimpar() { return btnLimpar; }
	public JButton getBtnListar() { return btnListar; }
	public JButton getBtnVender() { return btnVender; }
	public JButton getBtnFechar() { return btnFechar; }

	public DefaultTableModel getModeloTabela() { return modeloTabela; }
	public JTable getTabela() { return tabela; }

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