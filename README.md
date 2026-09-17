# Distribuidora de Autopecas (Java Swing + MySQL, padrao MVC)

Sistema de cadastro de pecas para uma distribuidora de autopecas,
seguindo a MESMA arquitetura do projeto MVC de exemplo:

- **Model** (`model/Peca.java`, `model/PecaBD.java`): dados + todo o SQL +
  regras de negocio (codigo unico, preco > 0, estoque nao negativo,
  baixa de estoque com verificacao de saldo). Nao importa `javax.swing`.
- **View** (`view/JanelaPeca.java`): so a tela. Sem `addActionListener`
  e sem regra de negocio. Tem uma tabela (JTable) para listar as pecas.
- **Controller** (`controller/PecaController.java`): um unico
  `actionPerformed` que descobre qual botao foi clicado.
- **Main** (`main/Main.java`): junta as tres camadas. Execute SEMPRE esta classe.
- **TesteBD** (`main/TesteBD.java`): testa o Model no console, sem abrir tela.

## Funcionalidades

| Botao          | O que faz                                                        |
|----------------|------------------------------------------------------------------|
| Cadastrar      | grava a peca (rejeita codigo duplicado, preco <= 0, qtd < 0)     |
| Buscar         | carrega a peca pelo codigo nos campos                            |
| Atualizar      | grava as alteracoes da peca buscada                              |
| Excluir        | remove a peca (com confirmacao)                                  |
| Vender (baixa) | tira N unidades do estoque (rejeita sem saldo)                   |
| Limpar         | esvazia os campos                                                |
| Listar         | abre tabela com todas as pecas                                   |
| Fechar         | sai do sistema (com confirmacao)                                 |

## Como rodar

1. Execute o SQL no MySQL (ex.: `mysql -u root -p < sql/SqlDistribuidora.sql`).
   Opcional: rode tambem `sql/dados_exemplo.sql` para ter pecas de teste.
   O script cria o banco `distribuidora`, o usuario `aluno_cd`/`aluno_pw`
   e a tabela `peca` - tudo igual ao seu arquivo original.
2. Baixe o **MySQL Connector/J** e coloque o jar em `lib/`
   (ajuste o nome no `.classpath`, se diferente).
3. Importe o projeto no Eclipse (File > Open Projects from File System)
   ou compile na mao: `javac -cp "lib/mysql-connector-j.jar;src" -d bin src/*/*.java`
4. Rode `main.Main` para abrir a tela, ou `main.TesteBD` para testar o
   banco pelo console.
