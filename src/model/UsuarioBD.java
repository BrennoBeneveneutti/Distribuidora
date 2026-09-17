package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UsuarioBD {
	private final Path arquivo;

	public UsuarioBD() {
		this.arquivo = Paths.get("dados", "usuarios.txt");
		if (!Files.exists(arquivo)) {
			try {
				Files.createDirectories(arquivo.getParent());
				Files.write(arquivo,
						Arrays.asList("vendedor;1234;VENDEDOR", "cliente;1234;CLIENTE"),
						StandardCharsets.UTF_8);
			} catch (IOException e) {
				throw new RuntimeException("Nao foi possivel criar o arquivo de usuarios: "
						+ e.getMessage());
			}
		}
	}


	private List<String[]> linhas() {
		try {
			List<String[]> todas = new ArrayList<>();
			for (String linha : Files.readAllLines(arquivo, StandardCharsets.UTF_8)) {
				if (!linha.trim().isEmpty()) {
					todas.add(linha.split(";"));
				}
			}
			return todas;
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler os usuarios: " + e.getMessage());
		}
	}


	public String autenticar(String usuario, String senha) {
		for (String[] campos : linhas()) {
			if (campos[0].equals(usuario) && campos[1].equals(senha)) {
				return campos[2];
			}
		}
		return null;
	}

	public boolean existe(String usuario) {
		for (String[] campos : linhas()) {
			if (campos[0].equals(usuario)) {
				return true;
			}
		}
		return false;
	}


	public void cadastrar(String usuario, String senha, String tipo) {
		if (existe(usuario)) {
			throw new IllegalArgumentException("Esse nome de usuario ja esta em uso.");
		}
		try {
			Files.write(arquivo, Arrays.asList(usuario + ";" + senha + ";" + tipo),
					StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao salvar o usuario: " + e.getMessage());
		}
	}
}