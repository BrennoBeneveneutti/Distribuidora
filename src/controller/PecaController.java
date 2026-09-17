package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Peca;
import model.PecaBD;
import view.JanelaPeca;


public class PecaController implements ActionListener {
	private PecaBD bd;
	private JanelaPeca view;


	private Peca pecaEmEdicao = null;

	public PecaController(PecaBD bd, JanelaPeca view) {
		this.bd = bd;
		this.view = view;

		this.view.getBtnCadastrar().addActionListener(this);
		this.view.getBtnBuscar().addActionListener(this);
		this.view.getBtnAtualizar().addActionListener(this);
		this.view.getBtnExcluir().addActionListener(this);
		this.view.getBtnLimpar().addActionListener(this);
		this.view.getBtnListar().addActionListener(this);
		this.view.getBtnVender().addActionListener(this);
		this.view.getBtnFechar().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == this.view.getBtnCadastrar()) {
			cadastrar();
		} else if (e.getSource() == this.view.getBtnBuscar()) {
			buscar();
		} else if (e.getSource() == this.view.getBtnAtualizar()) {
			atualizar();
		} else if (e.getSource() == this.view.getBtnExcluir()) {
			excluir();
		} else if (e.getSource() == this.view.getBtnLimpar()) {
			this.pecaEmEdicao = null;
			this.view.limparCampos();
		} else if (e.getSource() == this.view.getBtnListar()) {
			listar();
		} else if (e.getSource() == this.view.getBtnVender()) {
			vender();
		} else if (e.getSource() == this.view.getBtnFechar()) {
			if (this.view.confirmarSaida()) {
				this.view.fechar();
			}
		}
	}



	private Peca pecaDosCampos() {
		Peca p = new Peca();
		p.setCodigo(this.view.getTxtCodigo().getText());
		p.setDescricao(this.view.getTxtDescricao().getText());
		p.setMarca(this.view.getTxtMarca().getText());
		p.setAplicacao(this.view.getTxtAplicacao().getText());
		try {
			p.setPreco(Double.parseDouble(this.view.getTxtPreco().getText().trim().replace(",", ".")));
		} catch (NumberFormatException erro) {
			this.view.mostrarErro("Preco invalido. Use numeros, ex.: 49.90");
			return null;
		}
		try {
			p.setQuantidade(Integer.parseInt(this.view.getTxtQuantidade().getText().trim()));
		} catch (NumberFormatException erro) {
			this.view.mostrarErro("Quantidade invalida. Use numeros inteiros.");
			return null;
		}
		return p;
	}

	private void cadastrar() {

		Peca peca = pecaDosCampos();
		if (peca == null) {
			return; 
		}

		try {
			this.bd.salvar(peca);
		} catch (IllegalArgumentException erro) {

			this.view.mostrarErro(erro.getMessage());
			return; 
		}

		this.view.mostrarMensagem("Cadastrado. Total no banco: " + this.bd.contar());
		this.view.limparCampos();
	}

	private void buscar() {
		String codigo = this.view.getTxtCodigo().getText();
		Peca p = this.bd.buscarPorCodigo(codigo);
		if (p == null) {
			this.view.mostrarErro("Peca nao encontrada.");
			return;
		}
		this.pecaEmEdicao = p;
		this.view.mostrarPeca(p);
	}

	private void atualizar() {
		if (this.pecaEmEdicao == null) {
			this.view.mostrarErro("Busque a peca pelo codigo antes de atualizar.");
			return;
		}
		Peca peca = pecaDosCampos();
		if (peca == null) {
			return;
		}
		peca.setId(this.pecaEmEdicao.getId());
		try {
			this.bd.atualizar(peca);
		} catch (IllegalArgumentException erro) {
			this.view.mostrarErro(erro.getMessage());
			return;
		}
		this.pecaEmEdicao = peca;
		this.view.mostrarMensagem("Atualizado.");
	}

	private void excluir() {
		String codigo = this.view.getTxtCodigo().getText();
		if (!this.bd.existeCodigo(codigo)) {
			this.view.mostrarErro("Peca nao encontrada.");
			return;
		}
		if (!this.view.confirmarExclusao()) {
			return;
		}
		this.bd.excluirPorCodigo(codigo);
		this.pecaEmEdicao = null;
		this.view.mostrarMensagem("Excluido.");
		this.view.limparCampos();
	}

	private void listar() {
		java.util.List<Peca> lista = this.bd.listarTodos();
		if (lista.isEmpty()) {
			this.view.mostrarMensagem("Nenhuma peca cadastrada.");
			return;
		}
		this.view.mostrarLista(lista);
	}

	private void vender() {
		String codigo = this.view.getTxtCodigo().getText();
		String digitado = this.view.perguntarQuantidadeVenda();
		if (digitado == null) {
			return; 
		}
		int quantidade;
		try {
			quantidade = Integer.parseInt(digitado.trim());
		} catch (NumberFormatException erro) {
			this.view.mostrarErro("Quantidade invalida.");
			return;
		}
		try {
			this.bd.darBaixa(codigo, quantidade);
		} catch (IllegalArgumentException erro) {
			this.view.mostrarErro(erro.getMessage());
			return;
		}
		Peca p = this.bd.buscarPorCodigo(codigo);
		this.view.mostrarMensagem(
				"Venda registrada. Estoque restante de " + p.getDescricao() + ": " + p.getQuantidade());
	}

	public void iniciarTela() {
		this.view.setVisible(true);
	}
}
