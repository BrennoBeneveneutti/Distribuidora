import java.sql.*;

public class Distribuidora {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/distribuidora";
		String user = "aluno_cd";
		String senha = "aluno_pw";
		
		try (Connection con = DriverManager.getConnection(url, user, senha)) {
			System.out.println("Conectado a: " + con.getMetaData().getDatabaseProductName());
			
			// 1. Comando INSERT adaptado para a tabela 'clientes'
			String sqlIns = "INSERT INTO clientes (nome, dt_nascimento, telefone, CPF, email) VALUES (?, ?, ?, ?, ?)";
			
			try (PreparedStatement ps = con.prepareStatement(sqlIns)) {
				ps.setString(1, "Diego Alves");
				ps.setDate(2, Date.valueOf("1995-05-10")); // Formato AAAA-MM-DD para o tipo DATE do MySQL
				ps.setString(3, "47999998888");
				ps.setString(4, "12345678901");
				ps.setString(5, "diego.alves@gmail.com");
				
				ps.executeUpdate();
				System.out.println("Cliente inserido com sucesso.");
			}
			
			// 2. Comando SELECT adaptado com os novos campos
			String sqlSel = "SELECT id, nome, dt_nascimento, telefone, CPF, email FROM clientes ORDER BY nome ASC";
			
			try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sqlSel)) {
				System.out.println("\n--- Lista de Clientes ---");
				while (rs.next()) {
					System.out.printf("%d | %s | %s | %s | %s | %s%n", 
							rs.getInt("id"), 
							rs.getString("nome"),
							rs.getDate("dt_nascimento"), // Recupera como objeto Date do SQL
							rs.getString("telefone"),
							rs.getString("CPF"),
							rs.getString("email"));
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro de banco: " + e.getMessage());
		}
	}
}
