package com.mycompany.agendacontatos;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private final ArrayList<Contato> contatos = new ArrayList<>();

    public void adicionarContato(Contato contato) {
        if (contato == null) {
            throw new IllegalArgumentException("O contato não pode ser nulo.");
        }
        contatos.add(contato);
    }

    // Retorna uma cópia da lista: adicionar ou remover nela não altera a agenda.
    public List<Contato> listarContatos() {
        return new ArrayList<>(contatos);
    }

    public boolean removerContato(Contato contato) {
        return contatos.remove(contato);
    }

    // Retorna os contatos cujo nome contém o texto, ignorando maiúsculas.
    public List<Contato> buscarContatos(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("Informe o texto da busca.");
        }
        List<Contato> encontrados = new ArrayList<>();
        String busca = texto.trim().toLowerCase(java.util.Locale.ROOT);
        for (Contato contato : contatos) {
            if (contato.getNome() != null &&
                    contato.getNome().toLowerCase(java.util.Locale.ROOT).contains(busca)) {
                encontrados.add(contato);
            }
        }
        return encontrados;
    }

    public void editarContato(Contato contato, String nome, String telefone, String email) {
        if (!contatos.contains(contato)) {
            throw new IllegalArgumentException("Esse contato não está na agenda.");
        }
        contato.setNome(nome);
        contato.setTelefone(telefone);
        contato.setEmail(email);
    }
}
