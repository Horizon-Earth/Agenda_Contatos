package br.edu.principal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TelaAgenda extends JFrame {

    private final Agenda agenda;

    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoCelular;

    private JButton botaoAdicionar;
    private JButton botaoAtualizar;
    private JButton botaoExcluir;
    private JButton botaoLimpar;

    private JTable tabelaContatos;
    private DefaultTableModel modeloTabela;
    private String nomeOriginal;

    public TelaAgenda(Agenda agenda) {
        this.agenda = agenda;
        criarComponentes();
        configurarLayout();
        configurarEventos();
        configurarJanela();
        atualizarTabela();
    }

    private void criarComponentes() {
        campoNome = new JTextField(20);
        campoEmail = new JTextField(20);
        campoCelular = new JTextField(20);

        botaoAdicionar = new JButton("Adicionar");
        botaoAtualizar = new JButton("Atualizar");
        botaoExcluir = new JButton("Excluir");
        botaoLimpar = new JButton("Limpar");
        botaoAtualizar.setEnabled(false);
        botaoExcluir.setEnabled(false);

        modeloTabela = new DefaultTableModel(
                new Object[]{"Nome", "Celular", "E-mail"}, 0) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };
        
        tabelaContatos = new JTable(modeloTabela);
        tabelaContatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void configurarLayout() {
        JPanel formulario = new JPanel(new GridLayout(3, 2, 8, 8));
        formulario.setBorder(BorderFactory.createTitledBorder("Dados do contato"));
        formulario.add(new JLabel("Nome:"));
        formulario.add(campoNome);
        formulario.add(new JLabel("Celular:"));
        formulario.add(campoCelular);
        formulario.add(new JLabel("E-mail:"));
        formulario.add(campoEmail);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botoes.add(botaoAdicionar);
        botoes.add(botaoAtualizar);
        botoes.add(botaoExcluir);
        botoes.add(botaoLimpar);

        JScrollPane areaTabela = new JScrollPane(tabelaContatos);
        areaTabela.setBorder(BorderFactory.createTitledBorder("Contatos cadastrados"));

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelPrincipal.add(formulario, BorderLayout.NORTH);
        painelPrincipal.add(areaTabela, BorderLayout.CENTER);
        painelPrincipal.add(botoes, BorderLayout.SOUTH);
        add(painelPrincipal);
    }

    private void configurarEventos() {
        botaoAdicionar.addActionListener(e -> adicionarContato());
        botaoAtualizar.addActionListener(e -> atualizarContato());
        botaoExcluir.addActionListener(e -> excluirContato());
        botaoLimpar.addActionListener(e -> limparCampos());
        tabelaContatos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                carregarContatoSelecionado();
            }
        });
    }

    private void configurarJanela() {
        setTitle("Agenda de Contatos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void adicionarContato() {
        if (!camposValidos()) {
            return;
        }

        String nome = campoNome.getText().trim();
        if (agenda.pesquisar(nome) != null) {
            mostrarAviso("Já existe um contato com esse nome.");
            return;
        }

        agenda.adicionar(nome, campoCelular.getText().trim(),
                campoEmail.getText().trim());
        atualizarTabela();
        limparCampos();
        JOptionPane.showMessageDialog(this, "Contato adicionado com sucesso!");
    }

    private void atualizarContato() {
        if (nomeOriginal == null) {
            mostrarAviso("Selecione um contato na tabela.");
            return;
        }
        if (!camposValidos()) {
            return;
        }

        String novoNome = campoNome.getText().trim();
        Contato contatoComMesmoNome = agenda.pesquisar(novoNome);
        if (contatoComMesmoNome != null
                && !novoNome.equalsIgnoreCase(nomeOriginal)) {
            mostrarAviso("Já existe um contato com esse nome.");
            return;
        }

        boolean atualizou = agenda.atualizar(nomeOriginal, novoNome,
                campoCelular.getText().trim(), campoEmail.getText().trim());
        if (atualizou) {
            atualizarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(this, "Contato atualizado com sucesso!");
        } else {
            mostrarAviso("Contato não encontrado.");
        }
    }

    private void excluirContato() {
        if (nomeOriginal == null) {
            mostrarAviso("Selecione um contato na tabela.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir " + nomeOriginal + "?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        if (agenda.excluir(nomeOriginal)) {
            atualizarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(this, "Contato excluído com sucesso!");
        }
    }

    private boolean camposValidos() {
        String nome = campoNome.getText().trim();
        String celular = campoCelular.getText().trim();
        String email = campoEmail.getText().trim();

        if (nome.isEmpty() || celular.isEmpty() || email.isEmpty()) {
            mostrarAviso("Preencha todos os campos.");
            return false;
        }
        if (!email.contains("@") || !email.contains(".")) {
            mostrarAviso("Digite um e-mail válido.");
            return false;
        }
        return true;
    }

    private void carregarContatoSelecionado() {
        int linha = tabelaContatos.getSelectedRow();
        if (linha == -1) {
            return;
        }

        nomeOriginal = modeloTabela.getValueAt(linha, 0).toString();
        campoNome.setText(nomeOriginal);
        campoCelular.setText(modeloTabela.getValueAt(linha, 1).toString());
        campoEmail.setText(modeloTabela.getValueAt(linha, 2).toString());
        botaoAdicionar.setEnabled(false);
        botaoAtualizar.setEnabled(true);
        botaoExcluir.setEnabled(true);
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Contato contato : agenda.getContatos()) {
            modeloTabela.addRow(new Object[]{
                contato.getNome(), contato.getCelular(), contato.getEmail()
            });
        }
    }

    private void limparCampos() {
        campoNome.setText("");
        campoCelular.setText("");
        campoEmail.setText("");
        tabelaContatos.clearSelection();
        nomeOriginal = null;
        botaoAdicionar.setEnabled(true);
        botaoAtualizar.setEnabled(false);
        botaoExcluir.setEnabled(false);
        campoNome.requestFocusInWindow();
    }

    private void mostrarAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Atenção",
                JOptionPane.WARNING_MESSAGE);
    }
}
