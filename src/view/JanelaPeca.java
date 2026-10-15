package view;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class JanelaPeca extends JFrame {

    private static final long serialVersionUID = 1L;

    // Atributos dos itens de menu (para o Controller ou eventos)
    private JMenuItem miLimpar;
    private JMenuItem miSair;
    private JMenuItem miManual;
    private JMenuItem miSobre;

    public JanelaPeca() {
        setTitle("Cadastro de Peças");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ... O seu código de montagem do painel/componentes continua aqui ...

        // Instala a barra de menus no topo da janela
        setJMenuBar(criarMenu());
    }

    // Método que monta o JMenuBar no padrão do roteiro do IFSC
    private JMenuBar criarMenu() {
        JMenuBar barra = new JMenuBar();

        // --- Menu Arquivo ---
        JMenu menuArquivo = new JMenu("Arquivo");
        menuArquivo.setMnemonic(KeyEvent.VK_A); // Alt+A abre o menu

        miLimpar = new JMenuItem("Limpar campos");
        miLimpar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK)); // Ctrl+L
        menuArquivo.add(miLimpar);

        menuArquivo.addSeparator(); // Linha divisória

        miSair = new JMenuItem("Sair");
        miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK)); // Ctrl+Q
        miSair.addActionListener(e -> dispose()); // Encerra/fecha
        menuArquivo.add(miSair);

        // --- Menu Ajuda ---
        JMenu menuAjuda = new JMenu("Ajuda");
        menuAjuda.setMnemonic(KeyEvent.VK_J); // Alt+J abre o menu

        miManual = new JMenuItem("Manual de uso");
        miManual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0)); // Tecla F1
        miManual.addActionListener(e -> mostrarManual()); // Abre o manual
        menuAjuda.add(miManual);

        miSobre = new JMenuItem("Sobre");
        miSobre.addActionListener(e -> mostrarSobre()); // Abre a janela Sobre
        menuAjuda.add(miSobre);

        // Adiciona os menus na barra
        barra.add(menuArquivo);
        barra.add(menuAjuda);

        return barra;
    }

    // --- Métodos que abrem os JDialogs modais centralizados ---

    public void mostrarSobre() {
        new JanelaSobre(this).setVisible(true);
    }

    public void mostrarManual() {
        new JanelaManual(this).setVisible(true);
    }

    // --- Getters dos itens de menu (caso use no Controller) ---

    public JMenuItem getMiLimpar() { return miLimpar; }
    public JMenuItem getMiSair() { return miSair; }
    public JMenuItem getMiManual() { return miManual; }
    public JMenuItem getMiSobre() { return miSobre; }
}