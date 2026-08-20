package modelo;

public class Estudante {
	private int id;
	private String nome;
	private String curso;
	private double nota;
	private String email;

	public Estudante() {}

	public Estudante(String nome, String curso, double nota) {
		this(0, nome, curso, nota, null);
	}

	public Estudante(String nome, String curso, double nota, String email) {
		this(0, nome, curso, nota, email);
	}

	public Estudante(int id, String nome, String curso, double nota) {
		this(id, nome, curso, nota, null);
	}

	public Estudante(int id, String nome, String curso, double nota, String email) {
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.nota = nota;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Estudante[id=%d, nome=%s, curso=%s, nota=%.1f, email=%s]", id, nome, curso, nota, email);
	}
}