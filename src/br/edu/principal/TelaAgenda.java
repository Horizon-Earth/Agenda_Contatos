package br.edu.principal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaAgenda extends JFrame {

    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoCelular;

    private JButton botaoAdicionar;
    private JButton botaoAtualizar;
    private JButton botaoExcluir;

    private Agenda agenda = new Agenda();

    public TelaAgenda() {

        botaoAdicionar = new JButton("Adicionar contato");

        botaoAdicionar.addActionListener(e -> {
            String nome = campoNome.getText();
            String email = campoEmail.getText();
            String celular = campoCelular.getText();
            
            agenda.adicionar(nome, celular, email);

            JOptionPane.showMessageDialog( null, "Contato adicionado com sucesso!");

            campoNome.setText("");
            campoEmail.setText("");
            campoCelular.setText("");
        });

        botaoExcluir = new JButton("Excluir contato");

        botaoExcluir.addActionListener(e -> {
            String nome = campoNome.getText();

            boolean excluiu = agenda.excluir(nome);

            if (excluiu) {
                JOptionPane.showMessageDialog(null, "Contato excluido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Contato nao encontrado!");
            }

            campoNome.setText("");
            campoEmail.setText("");
            campoCelular.setText("");
        });

        botaoAtualizar = new JButton("Atualizar contato");
        botaoAtualizar.addActionListener(e -> {
            String nome = campoNome.getText();
            String email = campoEmail.getText();
            String celular = campoCelular.getText();

            boolean atualizou = agenda.atualizar(nome, nome, celular, email);

            if (atualizou) {
                JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Contato nao encontrado!");
            }
            campoNome.setText("");
            campoEmail.setText("");
            campoCelular.setText("");
        });

        
        setTitle("Agenda de Contatos");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel painel = new JPanel();

        JLabel labelNome = new JLabel("Nome: ");
        campoNome = new JTextField(20);

        JLabel labelEmail = new JLabel("Email: ");
        campoEmail = new JTextField(20);

        JLabel labelCelular = new JLabel("Celular: ");
        campoCelular = new JTextField(20);

        painel.add(labelNome);
        painel.add(campoNome);
        
        painel.add(labelEmail);
        painel.add(campoEmail);

        painel.add(labelCelular);
        painel.add(campoCelular);

        painel.add(botaoAdicionar);
        painel.add(botaoExcluir);
        painel.add(botaoAtualizar);

        add(painel);
        setVisible(true);
    }
}