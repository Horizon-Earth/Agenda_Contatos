package com.mycompany.agendacontatos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private final ContatoDAO contatoDAO;

    public Agenda() {
        this(new ContatoDAO());
    }

    Agenda(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }

    public void adicionarContato(Contato contato) throws SQLException {
        if (contato == null) {
            throw new IllegalArgumentException("O contato não pode ser nulo.");
        }

        contatoDAO.salvar(contato);
    }

    public List<Contato> listarContatos() throws SQLException {
        return contatoDAO.listar();
    }

    public boolean removerContato(Contato contato) throws SQLException {
        if (contato == null) {
            return false;
        }

        return contatoDAO.remover(contato);
    }

    public List<Contato> buscarContatos(String texto) throws SQLException {
        if (texto == null) {
            throw new IllegalArgumentException("Informe o texto da busca.");
        }

        List<Contato> encontrados = new ArrayList<>();
        String busca = texto.trim().toLowerCase(java.util.Locale.ROOT);

        for (Contato contato : contatoDAO.listar()) {
            if (contato.getNome() != null &&
                    contato.getNome().toLowerCase(java.util.Locale.ROOT).contains(busca)) {
                encontrados.add(contato);
            }
        }

        return encontrados;
    }

    public void editarContato(
            Contato contato,
            String nome,
            String telefone,
            String email) throws SQLException {

        if (contato == null) {
            throw new IllegalArgumentException("O contato não pode ser nulo.");
        }

        contato.setNome(nome);
        contato.setTelefone(telefone);
        contato.setEmail(email);

        contatoDAO.editar(contato);
    }
}
