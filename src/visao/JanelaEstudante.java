package visao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.EstudanteDAO;
import modelo.Estudante;

public class JanelaEstudante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCurso;
	private JTextField txtNota;
	private JTextField txtEmail;
	private JTextField txtBusca;
	private JTable tabela;
	private JLabel lblNome;
	private JLabel lblCurso;
	private JLabel lblNota;
	private JLabel lblEmail;
	private JLabel lblBusca;
	private JLabel lblStatus;

	private DefaultTableModel modelo;
	private final EstudanteDAO dao = new EstudanteDAO();
	private int idSelecionado = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaEstudante frame = new JanelaEstudante();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaEstudante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(28, 20, 46, 14);
		contentPane.add(lblNome);

		lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(28, 45, 46, 14);
		contentPane.add(lblCurso);

		lblNota = new JLabel("Nota:");
		lblNota.setBounds(28, 70, 46, 14);
		contentPane.add(lblNota);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(28, 95, 46, 14);
		contentPane.add(lblEmail);

		lblBusca = new JLabel("Buscar:");
		lblBusca.setBounds(28, 120, 46, 14);
		contentPane.add(lblBusca);

		txtNome = new JTextField();
		txtNome.setBounds(84, 17, 100, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtCurso = new JTextField();
		txtCurso.setColumns(10);
		txtCurso.setBounds(84, 42, 100, 20);
		contentPane.add(txtCurso);

		txtNota = new JTextField();
		txtNota.setColumns(10);
		txtNota.setBounds(84, 67, 100, 20);
		contentPane.add(txtNota);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(84, 92, 100, 20);
		contentPane.add(txtEmail);

		txtBusca = new JTextField();
		txtBusca.setColumns(10);
		txtBusca.setBounds(84, 117, 100, 20);
		contentPane.add(txtBusca);

		lblStatus = new JLabel("");
		lblStatus.setBounds(10, 290, 444, 14);
		contentPane.add(lblStatus);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(200, 16, 100, 23);
		contentPane.add(btnCadastrar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setBounds(310, 16, 100, 23);
		contentPane.add(btnAlterar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(310, 41, 100, 23);
		contentPane.add(btnLimpar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(200, 41, 100, 23);
		contentPane.add(btnExcluir);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(310, 116, 100, 23);
		contentPane.add(btnBuscar);

		JButton btnListar = new JButton("Listar todos");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setText("");
				listar();
			}
		});
		btnListar.setBounds(200, 116, 100, 23);
		contentPane.add(btnListar);

		JButton btnResumo = new JButton("Resumo");
		btnResumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirResumo();
			}
		});
		btnResumo.setBounds(200, 66, 210, 23);
		contentPane.add(btnResumo);

		tabela = new JTable();
		tabela.setBounds(28, 150, 410, 120);
		contentPane.add(tabela);

		modelo = new DefaultTableModel(new String[] { "ID", "Nome", "Curso", "Nota", "Email" }, 0);
		tabela.setModel(modelo);
		tabela.setRowHeight(22);
		tabela.setDefaultEditor(Object.class, null);

		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					carregarSelecionado();
				}
			}
		});

		listar();
	}

	private void listar() {
		try {
			preencherTabela(dao.listar());
		} catch (SQLException ex) {
			erro("Erro ao listar", ex);
		}
	}

	private void buscar() {
		try {
			preencherTabela(dao.buscarPorNome(txtBusca.getText().trim()));
		} catch (SQLException ex) {
			erro("Erro ao buscar", ex);
		}
	}

	private void preencherTabela(List<Estudante> lista) {
		modelo.setRowCount(0);
		for (Estudante e : lista) {
			modelo.addRow(new Object[] {
				e.getId(), e.getNome(), e.getCurso(), e.getNota(), e.getEmail() });
		}
		lblStatus.setText(lista.size() + " estudante(s) na tabela.");
	}

	private void carregarSelecionado() {
		int linha = tabela.getSelectedRow();
		if (linha < 0) return;

		idSelecionado = (int) modelo.getValueAt(linha, 0);

		// Ler do banco e mais confiavel do que ler da tela porque garante que estamos obtendo os dados
		// exatos e atualizados do registro (incluindo campos nao exibidos na tabela ou alterados por outros acessos),
		// sem depender de formatacoes ou truncamentos visuais do modelo da tabela.
		try {
			Estudante e = dao.buscarPorId(idSelecionado);
			if (e != null) {
				txtNome.setText(e.getNome());
				txtCurso.setText(e.getCurso());
				txtNota.setText(String.valueOf(e.getNota()));
				txtEmail.setText(e.getEmail() != null ? e.getEmail() : "");
				lblStatus.setText("Editando o estudante de id " + idSelecionado + ".");
			}
		} catch (SQLException ex) {
			erro("Erro ao carregar do banco", ex);
		}
	}

	private Estudante lerFormulario() {
		String nome = txtNome.getText().trim();
		String curso = txtCurso.getText().trim();
		String email = txtEmail.getText().trim();

		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha o nome!",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			txtNome.requestFocus();
			return null;
		}

		double nota;
		try {
			nota = Double.parseDouble(txtNota.getText().trim().replace(",", "."));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Nota deve ser um numero!",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			txtNota.requestFocus();
			return null;
		}

		if (nota < 0 || nota > 10) {
			JOptionPane.showMessageDialog(this, "A nota deve estar entre 0 e 10.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			txtNota.requestFocus();
			return null;
		}

		Estudante e = new Estudante(nome, curso, nota);
		e.setEmail(email);
		return e;
	}

	private void cadastrar() {
		Estudante e = lerFormulario();
		if (e == null) return;

		try {
			dao.inserir(e);
			JOptionPane.showMessageDialog(this,
					"Estudante cadastrado com o id " + e.getId() + ".");
			limpar();
			listar();
		} catch (SQLIntegrityConstraintViolationException ex) {
			JOptionPane.showMessageDialog(this, "Já existe um estudante cadastrado com esse nome!",
					"Aviso", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException ex) {
			erro("Erro ao cadastrar", ex);
		}
	}

	private void alterar() {
		if (idSelecionado == 0) {
			JOptionPane.showMessageDialog(this, "Selecione primeiro uma linha da tabela.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		Estudante e = lerFormulario();
		if (e == null) return;
		e.setId(idSelecionado);
		try {
			dao.alterar(e);
			JOptionPane.showMessageDialog(this, "Estudante alterado.");
			limpar();
			listar();
		} catch (SQLIntegrityConstraintViolationException ex) {
			JOptionPane.showMessageDialog(this, "Já existe um estudante cadastrado com esse nome!",
					"Aviso", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException ex) {
			erro("Erro ao alterar", ex);
		}
	}

	private void excluir() {
		if (idSelecionado == 0) {
			JOptionPane.showMessageDialog(this, "Selecione primeiro uma linha da tabela.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int opcao = JOptionPane.showConfirmDialog(this,
				"Excluir o estudante " + txtNome.getText() + "?",
				"Confirmacao", JOptionPane.YES_NO_OPTION);
		if (opcao != JOptionPane.YES_OPTION) return;
		try {
			dao.excluir(idSelecionado);
			JOptionPane.showMessageDialog(this, "Estudante excluido.");
			limpar();
			listar();
		} catch (SQLException ex) {
			erro("Erro ao excluir", ex);
		}
	}

	private void exibirResumo() {
		try {
			String resumo = dao.contarPorCurso();
			JOptionPane.showMessageDialog(this, resumo, "Resumo por Curso", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException ex) {
			erro("Erro ao gerar resumo", ex);
		}
	}

	private void limpar() {
		idSelecionado = 0;
		txtNome.setText("");
		txtCurso.setText("");
		txtNota.setText("");
		txtEmail.setText("");
		tabela.clearSelection();
		txtNome.requestFocus();
		lblStatus.setText("Formulario limpo.");
	}

	private void erro(String contexto, SQLException ex) {
		JOptionPane.showMessageDialog(this,
				contexto + ": " + ex.getMessage(),
				"Erro", JOptionPane.ERROR_MESSAGE);
		lblStatus.setText(contexto + ".");
	}

	public JLabel getTxtNome() {
		return lblNome;
	}

	public JLabel getTxtCurso() {
		return lblCurso;
	}

	public JLabel getTxtNota() {
		return lblNota;
	}

	public JLabel getTxtBusca() {
		return lblBusca;
	}
}