package main;

import model.Peca;
import model.PecaBD;

public class TesteBD {
	static void tentar(PecaBD bd, String codigo, String descricao, String marca, double preco, int qtd,
			String aplicacao) {
		Peca p = new Peca();
		p.setCodigo(codigo);
		p.setDescricao(descricao);
		p.setMarca(marca);
		p.setPreco(preco);
		p.setQuantidade(qtd);
		p.setAplicacao(aplicacao);
		try {
			bd.salvar(p);
			System.out.println("Gravado : " + codigo + " / " + descricao + " (total: " + bd.contar() + ")");
		} catch (IllegalArgumentException erro) {
			System.out.println("Recusado: " + codigo + " / " + descricao + " -> " + erro.getMessage());
		}
	}

	public static void main(String[] args) {
		PecaBD bd = new PecaBD();
		tentar(bd, "FLT001", "Filtro de oleo", "Mahle", 24.90, 40, "Motor 1.0/1.6");
		tentar(bd, "PST002", "Pastilha de freio", "Bosch", 89.90, 15, "Dianteira - linha VW");
		tentar(bd, "FLT001", "Filtro de ar", "Mahle", 30.00, 10, "Linha GM"); 
		tentar(bd, "", "Correia dentada", "Continental", 120.00, 5, null);   
		tentar(bd, "COR003", "Correia dentada", "Continental", -5.00, 5, null); 
		tentar(bd, "BAT004", "Bateria 60Ah", "Moura", 399.90, 8, "Uso geral");
		tentar(bd, "VEL005", "Vela de ignicao", "NGK O'Brien", 18.50, 60, "Motor 1.0"); 
		System.out.println("--- no banco agora ---");
		for (Peca p : bd.listarTodos()) {
			System.out.println(" " + p.getCodigo() + " | " + p.getDescricao() + " | " + p.getMarca() + " | "
					+ p.getPreco() + " | " + p.getQuantidade() + " | " + p.getAplicacao());
		}
	}
}
