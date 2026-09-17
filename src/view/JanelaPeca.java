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


		g.gridx = 0; g.gridy = 0; g.weightx = 0;
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(labelFont);
		lblCodigo.setForeground(COLOR_TEXT);
		formulario.add(lblCodigo, (GridBagConstraints) g.clone());

		g.gridx = 1; g.weightx = 1;
		txtCodigo = new JTextField();
		txtCodigo.setFont(fieldFont);
		txtCodigo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtCodigo, (GridBagConstraints) g.clone());

		g.gridx = 0; g.gridy = 1; g.weightx = 0;
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(labelFont);
		lblNome.setForeground(COLOR_TEXT);
		formulario.add(lblNome, (GridBagConstraints) g.clone());

		g.gridx = 1; g.weightx = 1;
		txtNome = new JTextField();
		txtNome.setFont(fieldFont);
		txtNome.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtNome, (GridBagConstraints) g.clone());


		g.gridx = 0; g.gridy = 2; g.weightx = 0;
		JLabel lblPreco = new JLabel("Preço (R$):");
		lblPreco.setFont(labelFont);
		lblPreco.setForeground(COLOR_TEXT);
		formulario.add(lblPreco, (GridBagConstraints) g.clone());

		g.gridx = 1; g.weightx = 1;
		txtPreco = new JTextField();
		txtPreco.setFont(fieldFont);
		txtPreco.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtPreco, (GridBagConstraints) g.clone());

		
		g.gridx = 0; g.gridy = 3; g.weightx = 0;
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setFont(labelFont);
		lblQtd.setForeground(COLOR_TEXT);
		formulario.add(lblQtd, (GridBagConstraints) g.clone());

		g.gridx = 1; g.weightx = 1;
		txtQuantidade = new JTextField();
		txtQuantidade.setFont(fieldFont);
		txtQuantidade.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)
		));
		formulario.add(txtQuantidade, (GridBagConstraints) g.clone());

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
		Font btnFont = new Font("Segoe UI", Font.BOLD, 12);
		Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(btnFont);
		btnCadastrar.setFocusPainted(false);
		btnCadastrar.setCursor(handCursor);
		btnCadastrar.setBackground(new Color(241, 245, 249));
		btnCadastrar.setForeground(COLOR_PRIMARY);
		btnCadastrar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)
		));

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(btnFont);
		btnAtualizar.setFocusPainted(false);
		btnAtualizar.setCursor(handCursor);
		btnAtualizar.setBackground(new Color(241, 245, 249));
		btnAtualizar.setForeground(COLOR_TEXT);
		btnAtualizar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)
		));

		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(btnFont);
		btnExcluir.setFocusPainted(false);
		btnExcluir.setCursor(handCursor);
		btnExcluir.setBackground(new Color(241, 245, 249));
		btnExcluir.setForeground(COLOR_DANGER);
		btnExcluir.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)
		));

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(btnFont);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setCursor(handCursor);
		btnBuscar.setBackground(new Color(241, 245, 249));
		btnBuscar.setForeground(COLOR_TEXT);
		btnBuscar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)
		));

		btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(btnFont);
		btnLimpar.setFocusPainted(false);
		btnLimpar.setCursor(handCursor);
		btnLimpar.setBackground(new Color(241, 245, 249));
		btnLimpar.setForeground(COLOR_TEXT);
		btnLimpar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)
		));

		btnListar = new JButton("Listar Tudo");
		btnListar.setFont(btnFont);
		btnListar.setFocusPainted(false);
		btnListar.setCursor(handCursor);
		btnListar.setBackground(new Color(241, 245, 249));
		btnListar.setForeground(COLOR_TEXT);
		btnListar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)
		));

		btnVender = new JButton("Vender");
		btnVender.setFont(btnFont);
		btnVender.setFocusPainted(false);
		btnVender.setCursor(handCursor);
		btnVender.setBackground(new Color(241, 245, 249));
		btnVender.setForeground(new Color(16, 185, 129));
		btnVender.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)
		));

		btnFechar = new JButton("Fechar");
		btnFechar.setFont(btnFont);
		btnFechar.setFocusPainted(false);
		btnFechar.setCursor(handCursor);
		btnFechar.setBackground(new Color(241, 245, 249));
		btnFechar.setForeground(COLOR_TEXT);
		btnFechar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)
		));

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
		getContentPane().add(mainPanel);
	}

	public void modoCliente() {
		btnCadastrar.setVisible(false);
		btnAtualizar.setVisible(false);
		btnExcluir.setVisible(false);
		btnVender.setVisible(false);
	}

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