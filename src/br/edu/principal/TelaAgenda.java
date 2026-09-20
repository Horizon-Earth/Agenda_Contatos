package br.edu.principal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaAgenda extends JFrame {

    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoCelular;

    private JButton botaoAdicionar;
    private JButton botaoAtualizar;
    private JButton botaoExcluir;

    public TelaAgenda() {

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

        botaoAdicionar = new JButton("Adicionar contato");
        botaoExcluir = new JButton("Excluir contato");
        botaoAtualizar = new JButton("Atualizar contato");

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