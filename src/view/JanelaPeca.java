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

		GridBagConstraints g = new GridBagConstraints();
		g.insets = new Insets(5, 5, 5, 5);
		g.fill = GridBagConstraints.HORIZONTAL;

		Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
		Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);

		// Código
		g.gridx = 0; g.gridy = 0; g.weightx = 0;
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(labelFont);
		lblCodigo.setForeground(COLOR_TEXT);
		formulario.add(lblCodigo, g);

		g.gridx = 1; g.weightx = 1;
		txtCodigo = new JTextField();
		txtCodigo.setFont(fieldFont);
		txtCodigo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtCodigo, g);

		// Nome / Descrição
		g.gridx = 0; g.gridy = 1; g.weightx = 0;
		JLabel lblNome = new JLabel("Descrição:");
		lblNome.setFont(labelFont);
		lblNome.setForeground(COLOR_TEXT);
		formulario.add(lblNome, g);

		g.gridx = 1; g.weightx = 1;
		txtNome = new JTextField();
		txtNome.setFont(fieldFont);
		txtNome.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtNome, g);

		// Marca
		g.gridx = 0; g.gridy = 2; g.weightx = 0;
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(labelFont);
		lblMarca.setForeground(COLOR_TEXT);
		formulario.add(lblMarca, g);

		g.gridx = 1; g.weightx = 1;
		txtMarca = new JTextField();
		txtMarca.setFont(fieldFont);
		txtMarca.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtMarca, g);

		// Aplicação
		g.gridx = 0; g.gridy = 3; g.weightx = 0;
		JLabel lblAplicacao = new JLabel("Aplicação:");
		lblAplicacao.setFont(labelFont);
		lblAplicacao.setForeground(COLOR_TEXT);
		formulario.add(lblAplicacao, g);

		g.gridx = 1; g.weightx = 1;
		txtAplicacao = new JTextField();
		txtAplicacao.setFont(fieldFont);
		txtAplicacao.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtAplicacao, g);

		// Preço
		g.gridx = 0; g.gridy = 4; g.weightx = 0;
		JLabel lblPreco = new JLabel("Preço (R$):");
		lblPreco.setFont(labelFont);
		lblPreco.setForeground(COLOR_TEXT);
		formulario.add(lblPreco, g);

		g.gridx = 1; g.weightx = 1;
		txtPreco = new JTextField();
		txtPreco.setFont(fieldFont);
		txtPreco.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtPreco, g);

		// Quantidade
		g.gridx = 0; g.gridy = 5; g.weightx = 0;
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setFont(labelFont);
		lblQtd.setForeground(COLOR_TEXT);
		formulario.add(lblQtd, g);

		g.gridx = 1; g.weightx = 1;
		txtQuantidade = new JTextField();
		txtQuantidade.setFont(fieldFont);
		txtQuantidade.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtQuantidade, g);

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