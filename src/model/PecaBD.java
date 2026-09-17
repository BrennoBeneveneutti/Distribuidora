package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PecaBD {
	private static final String URL = "jdbc:mysql://localhost:3306/distribuidora";
	private static final String USER = "aluno_cd";
	private static final String SENHA = "aluno_pw";

	private Connection abrir() throws SQLException {
		return DriverManager.getConnection(URL, USER, SENHA);
	}

	public void salvar(Peca p) {

		validar(p);
		if (existeCodigo(p.getCodigo())) {
			throw new IllegalArgumentException("Codigo ja cadastrado.");
		}

		String sql = "INSERT INTO peca (codigo, descricao, marca, preco, quantidade, aplicacao) VALUES (?, ?, ?, ?, ?, ?)";
		
		try (Connection con = abrir(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, p.getCodigo().trim());
			ps.setString(2, p.getDescricao().trim());
			ps.setString(3, p.getMarca().trim());
			ps.setDouble(4, p.getPreco());
			ps.setInt(5, p.getQuantidade());
			ps.setString(6, p.getAplicacao() == null ? "" : p.getAplicacao().trim());
			ps.executeUpdate();
		} catch (SQLException erro) {

			throw new RuntimeException("Erro ao gravar: " + erro.getMessage(), erro);

		}
	}

	private void validar(Peca p) {
		if (p.getCodigo() == null || p.getCodigo().trim().isEmpty()) {
			throw new IllegalArgumentException("Preencha o codigo.");
		}
		if (p.getDescricao() == null || p.getDescricao().trim().isEmpty()) {
			throw new IllegalArgumentException("Preencha a descricao.");
		}
		if (p.getMarca() == null || p.getMarca().trim().isEmpty()) {
			throw new IllegalArgumentException("Preencha a marca.");
		}
		if (p.getPreco() <= 0) {
			throw new IllegalArgumentException("O preco deve ser maior que zero.");
		}
		if (p.getQuantidade() < 0) {
			throw new IllegalArgumentException("A quantidade nao pode ser negativa.");
		}
	}

	public boolean existeCodigo(String codigo) {
		String sql = "SELECT id FROM peca WHERE codigo = ?";
		try (Connection con = abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, codigo.trim());
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next(); 

			}
		} catch (SQLException erro) {

			throw new RuntimeException("Erro ao consultar: " + erro.getMessage(), erro);

		}
	}

	public Peca buscarPorCodigo(String codigo) {
		String sql = "SELECT id, codigo, descricao, marca, preco, quantidade, aplicacao FROM peca WHERE codigo = ?";
		try (Connection con = abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, codigo.trim());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return montar(rs);
				}
			}
		} catch (SQLException erro) {
			throw new RuntimeException("Erro ao buscar: " + erro.getMessage(), erro);
		}
		return null;
	}

	public int contar() {
		String sql = "SELECT COUNT(*) FROM peca";
		try (Connection con = abrir();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1); 

			}
			return 0;

		} catch (SQLException erro) {

			throw new RuntimeException("Erro ao contar: " + erro.getMessage(), erro);

		}
	}

	public List<Peca> listarTodos() {
		return listarPorDescricao(null);
	}


	public List<Peca> listarPorDescricao(String trecho) {
		String sql = "SELECT id, codigo, descricao, marca, preco, quantidade, aplicacao FROM peca";
		boolean filtrar = trecho != null && !trecho.trim().isEmpty();
		if (filtrar) {
			sql += " WHERE descricao LIKE ?";
		}
		sql += " ORDER BY descricao";
		List<Peca> lista = new ArrayList<>();
		try (Connection con = abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
			if (filtrar) {
				ps.setString(1, "%" + trecho.trim() + "%");
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(montar(rs));
				}
			}
		} catch (SQLException erro) {

			throw new RuntimeException("Erro ao listar: " + erro.getMessage(), erro);

		}
		
		return lista;
	}


	private Peca montar(ResultSet rs) throws SQLException {
		Peca p = new Peca();
		p.setId(rs.getInt("id"));
		p.setCodigo(rs.getString("codigo"));
		p.setDescricao(rs.getString("descricao"));
		p.setMarca(rs.getString("marca"));
		p.setPreco(rs.getDouble("preco"));
		p.setQuantidade(rs.getInt("quantidade"));
		p.setAplicacao(rs.getString("aplicacao"));
		return p;
	}

	public void atualizar(Peca p) {

		
		validar(p);
		String sql = "UPDATE peca SET codigo = ?, descricao = ?, marca = ?, preco = ?, quantidade = ?, aplicacao = ? WHERE id = ?";
		try (Connection con = abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, p.getCodigo().trim());
			ps.setString(2, p.getDescricao().trim());
			ps.setString(3, p.getMarca().trim());
			ps.setDouble(4, p.getPreco());
			ps.setInt(5, p.getQuantidade());
			ps.setString(6, p.getAplicacao() == null ? "" : p.getAplicacao().trim());
			ps.setInt(7, p.getId());
			ps.executeUpdate();
		} catch (SQLException erro) {

			throw new RuntimeException("Erro ao atualizar: " + erro.getMessage(), erro);

		}
	}

	public void excluirPorCodigo(String codigo) {
		String sql = "DELETE FROM peca WHERE codigo = ?";
		try (Connection con = abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, codigo.trim());
			ps.executeUpdate();
		} catch (SQLException erro) {

			throw new RuntimeException("Erro ao excluir: " + erro.getMessage(), erro);

		}
	}

	public void darBaixa(String codigo, int quantidade) {
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Informe uma quantidade maior que zero.");
		}
		Peca p = buscarPorCodigo(codigo);
		if (p == null) {
			throw new IllegalArgumentException("Peca nao encontrada.");
		}
		if (p.getQuantidade() < quantidade) {
			throw new IllegalArgumentException(
					"Estoque insuficiente. Disponivel: " + p.getQuantidade() + ".");
		}
		String sql = "UPDATE peca SET quantidade = quantidade - ? WHERE id = ?";
		try (Connection con = abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, quantidade);
			ps.setInt(2, p.getId());
			ps.executeUpdate();
		} catch (SQLException erro) {

			throw new RuntimeException("Erro ao dar baixa: " + erro.getMessage(), erro);

		}
	}

	public static void main(String[] args) {
		PecaBD bd = new PecaBD();
		try (Connection con = bd.abrir()) {
			System.out.println("Conexao OK com " + con.getCatalog());
			System.out.println("Pecas no banco: " + bd.contar());
		} catch (SQLException e) {
			System.out.println("Falha: " + e.getMessage());
		}
	}
}
