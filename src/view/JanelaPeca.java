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
import java.util.List;

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

import model.Peca;
import javax.swing.JMenuBar;

public class JanelaPeca extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtMarca;
	private JTextField txtAplicacao;
	private JTextField txtPreco;
	private JTextField txtQuantidade;

	private JButton btnCadastrar;
	private JButton btnAtualizar;
	private JButton btnExcluir;
	private JButton btnBuscar;
	private JButton btnBuscarMarca;
	private JButton btnLimpar;
	private JButton btnListar;
	private JButton btnVender;
	private JButton btnFechar;

	private JTable tabela;
	private DefaultTableModel modeloTabela;

	private final Color COLOR_BG = new Color(245, 247, 250);
	private final Color COLOR_PANEL = new Color(255, 255, 255);
	private final Color COLOR_TEXT = new Color(30, 41, 59);
	private final Color COLOR_PRIMARY = new Color(37, 99, 235);
	private final Color COLOR_DANGER = new Color(220, 38, 38);

	public JanelaPeca() {
		setTitle("Distribuidora de Autopeças - Gerenciamento de Peças");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 600);
		setMinimumSize(new Dimension(750, 500));
		setLocationRelativeTo(null);
		getContentPane().setBackground(COLOR_BG);

		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(COLOR_BG);
		mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

		JPanel formulario = new JPanel(new GridBagLayout());
		formulario.setBackground(COLOR_PANEL);
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
				new EmptyBorder(12, 12, 12, 12)
		));

		Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
		Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);

		// --- LINHA 0: Código ---
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(labelFont);
		lblCodigo.setForeground(COLOR_TEXT);
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCodigo.insets = new Insets(5, 5, 5, 5);
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 0;
		formulario.add(lblCodigo, gbc_lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setFont(fieldFont);
		txtCodigo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
		gbc_txtCodigo.weightx = 1.0;
		gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodigo.insets = new Insets(5, 5, 5, 5);
		gbc_txtCodigo.gridx = 1;
		gbc_txtCodigo.gridy = 0;
		formulario.add(txtCodigo, gbc_txtCodigo);

		// --- LINHA 1: Descrição ---
		JLabel lblNome = new JLabel("Descrição:");
		lblNome.setFont(labelFont);
		lblNome.setForeground(COLOR_TEXT);
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNome.insets = new Insets(5, 5, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		formulario.add(lblNome, gbc_lblNome);

		txtNome = new JTextField();
		txtNome.setFont(fieldFont);
		txtNome.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.weightx = 1.0;
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.insets = new Insets(5, 5, 5, 5);
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 1;
		formulario.add(txtNome, gbc_txtNome);

		// --- LINHA 2: Marca ---
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(labelFont);
		lblMarca.setForeground(COLOR_TEXT);
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMarca.insets = new Insets(5, 5, 5, 5);
		gbc_lblMarca.gridx = 0;
		gbc_lblMarca.gridy = 2;
		formulario.add(lblMarca, gbc_lblMarca);

		txtMarca = new JTextField();
		txtMarca.setFont(fieldFont);
		txtMarca.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		GridBagConstraints gbc_txtMarca = new GridBagConstraints();
		gbc_txtMarca.weightx = 1.0;
		gbc_txtMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMarca.insets = new Insets(5, 5, 5, 5);
		gbc_txtMarca.gridx = 1;
		gbc_txtMarca.gridy = 2;
		formulario.add(txtMarca, gbc_txtMarca);

		// --- LINHA 3: Aplicação ---
		JLabel lblAplicacao = new JLabel("Aplicação:");
		lblAplicacao.setFont(labelFont);
		lblAplicacao.setForeground(COLOR_TEXT);
		GridBagConstraints gbc_lblAplicacao = new GridBagConstraints();
		gbc_lblAplicacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAplicacao.insets = new Insets(5, 5, 5, 5);
		gbc_lblAplicacao.gridx = 0;
		gbc_lblAplicacao.gridy = 3;
		formulario.add(lblAplicacao, gbc_lblAplicacao);

		txtAplicacao = new JTextField();
		txtAplicacao.setFont(fieldFont);
		txtAplicacao.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		GridBagConstraints gbc_txtAplicacao = new GridBagConstraints();
		gbc_txtAplicacao.weightx = 1.0;
		gbc_txtAplicacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAplicacao.insets = new Insets(5, 5, 5, 5);
		gbc_txtAplicacao.gridx = 1;
		gbc_txtAplicacao.gridy = 3;
		formulario.add(txtAplicacao, gbc_txtAplicacao);

		// --- LINHA 4: Preço ---
		JLabel lblPreco = new JLabel("Preço (R$):");
		lblPreco.setFont(labelFont);
		lblPreco.setForeground(COLOR_TEXT);
		GridBagConstraints gbc_lblPreco = new GridBagConstraints();
		gbc_lblPreco.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPreco.insets = new Insets(5, 5, 5, 5);
		gbc_lblPreco.gridx = 0;
		gbc_lblPreco.gridy = 4;
		formulario.add(lblPreco, gbc_lblPreco);

		txtPreco = new JTextField();
		txtPreco.setFont(fieldFont);
		txtPreco.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		GridBagConstraints gbc_txtPreco = new GridBagConstraints();
		gbc_txtPreco.weightx = 1.0;
		gbc_txtPreco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPreco.insets = new Insets(5, 5, 5, 5);
		gbc_txtPreco.gridx = 1;
		gbc_txtPreco.gridy = 4;
		formulario.add(txtPreco, gbc_txtPreco);

		// --- LINHA 5: Quantidade ---
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setFont(labelFont);
		lblQtd.setForeground(COLOR_TEXT);
		GridBagConstraints gbc_lblQtd = new GridBagConstraints();
		gbc_lblQtd.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQtd.insets = new Insets(5, 5, 5, 5);
		gbc_lblQtd.gridx = 0;
		gbc_lblQtd.gridy = 5;
		formulario.add(lblQtd, gbc_lblQtd);

		txtQuantidade = new JTextField();
		txtQuantidade.setFont(fieldFont);
		txtQuantidade.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		GridBagConstraints gbc_txtQuantidade = new GridBagConstraints();
		gbc_txtQuantidade.weightx = 1.0;
		gbc_txtQuantidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantidade.insets = new Insets(5, 5, 5, 5);
		gbc_txtQuantidade.gridx = 1;
		gbc_txtQuantidade.gridy = 5;
		formulario.add(txtQuantidade, gbc_txtQuantidade);

		mainPanel.add(formulario, BorderLayout.NORTH);

		// --- TABELA ---
		String[] colunas = { "Código", "Descrição", "Marca", "Aplicação", "Preço (R$)", "Quantidade" };
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
		btnCadastrar = criarBotao("Cadastrar", COLOR_PRIMARY);
		btnAtualizar = criarBotao("Atualizar", COLOR_TEXT);
		btnBuscar = criarBotao("Buscar Código", COLOR_TEXT);
		btnBuscarMarca = criarBotao("Buscar Marca", COLOR_TEXT);
		btnListar = criarBotao("Listar Tudo", COLOR_TEXT);
		btnLimpar = criarBotao("Limpar", COLOR_TEXT);
		btnVender = criarBotao("Vender", new Color(16, 185, 129));
		btnExcluir = criarBotao("Excluir", COLOR_DANGER);
		btnFechar = criarBotao("Fechar", COLOR_TEXT);

		JPanel barraBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
		barraBotoes.setOpaque(false);

		barraBotoes.add(btnCadastrar);
		barraBotoes.add(btnAtualizar);
		barraBotoes.add(btnBuscar);
		barraBotoes.add(btnBuscarMarca);
		barraBotoes.add(btnListar);
		barraBotoes.add(btnLimpar);
		barraBotoes.add(btnVender);
		barraBotoes.add(btnExcluir);
		barraBotoes.add(btnFechar);

		mainPanel.add(barraBotoes, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
	}

	private JButton criarBotao(String texto, Color corTexto) {
		JButton btn = new JButton(texto);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn.setFocusPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setBackground(new Color(241, 245, 249));
		btn.setForeground(corTexto);
		btn.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 10, 6, 10)
		));
		return btn;
	}

	public void modoCliente() {
		btnCadastrar.setVisible(false);
		btnAtualizar.setVisible(false);
		btnExcluir.setVisible(false);
		btnVender.setVisible(false);
	}

	public JTextField getTxtCodigo() { return txtCodigo; }
	public JTextField getTxtNome() { return txtNome; }
	public JTextField getTxtMarca() { return txtMarca; }
	public JTextField getTxtAplicacao() { return txtAplicacao; }
	public JTextField getTxtPreco() { return txtPreco; }
	public JTextField getTxtQuantidade() { return txtQuantidade; }

	public JButton getBtnCadastrar() { return btnCadastrar; }
	public JButton getBtnAtualizar() { return btnAtualizar; }
	public JButton getBtnExcluir() { return btnExcluir; }
	public JButton getBtnBuscar() { return btnBuscar; }
	public JButton getBtnBuscarMarca() { return btnBuscarMarca; }
	public JButton getBtnLimpar() { return btnLimpar; }
	public JButton getBtnListar() { return btnListar; }
	public JButton getBtnVender() { return btnVender; }
	public JButton getBtnFechar() { return btnFechar; }

	public DefaultTableModel getModeloTabela() { return modeloTabela; }
	public JTable getTabela() { return tabela; }

	public void limparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtMarca.setText("");
		txtAplicacao.setText("");
		txtPreco.setText("");
		txtQuantidade.setText("");
	}

	public void mostrarPeca(Peca p) {
		txtCodigo.setText(p.getCodigo());
		txtNome.setText(p.getDescricao());
		txtMarca.setText(p.getMarca());
		txtAplicacao.setText(p.getAplicacao());
		txtPreco.setText(String.valueOf(p.getPreco()));
		txtQuantidade.setText(String.valueOf(p.getQuantidade()));
	}

	public void limparTabela() {
		modeloTabela.setRowCount(0);
	}

	public void mostrarLista(List<Peca> lista) {
		limparTabela();
		for (Peca p : lista) {
			modeloTabela.addRow(new Object[] {
				p.getCodigo(),
				p.getDescricao(),
				p.getMarca(),
				p.getAplicacao(),
				String.format("%.2f", p.getPreco()),
				p.getQuantidade()
			});
		}
	}

	public boolean confirmarExclusao() {
		int resposta = JOptionPane.showConfirmDialog(
			this, 
			"Tem certeza que deseja excluir esta peça?", 
			"Confirmação de Exclusão", 
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		return resposta == JOptionPane.YES_OPTION;
	}

	public boolean confirmarSaida() {
		int resposta = JOptionPane.showConfirmDialog(
			this, 
			"Deseja realmente fechar a aplicação?", 
			"Sair", 
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		return resposta == JOptionPane.YES_OPTION;
	}

	public String perguntarQuantidadeVenda() {
		return JOptionPane.showInputDialog(
			this, 
			"Informe a quantidade que deseja vender:", 
			"Registrar Venda", 
			JOptionPane.QUESTION_MESSAGE
		);
	}

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