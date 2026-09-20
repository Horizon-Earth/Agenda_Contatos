package br.edu.principal;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    public void adicionar(
            String nome,
            String celular,
            String email) {

        Contato contato = new Contato(
                nome,
                celular,
                email
        );
        contatos.add(contato);
    }

    public void listar() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado!");
            return;
        }

        for (Contato contato : contatos) {

            System.out.println("--------------------------");
            System.out.println("Nome: " + contato.getNome());

            System.out.println("Celular: " + contato.getCelular());

            System.out.println("Email: " + contato.getEmail());
            System.out.println("--------------------------");

        }
    }

    public Contato pesquisar(String nomeBusca) {

        for (Contato contato : contatos) {

            if (contato.getNome().equalsIgnoreCase(nomeBusca)) {
                return contato;
            }
        }

        return null;
    }

    public boolean atualizar(
            String nomeProcurado,
            String novoNome,
            String novoCelular,
            String novoEmail) {

        Contato contato = pesquisar(nomeProcurado);

        if (contato == null) {

            return false;
        }

        contato.setNome(novoNome);
        contato.setCelular(novoCelular);
        contato.setEmail(novoEmail);

        return true;
    }

    public boolean excluir(String nome) {

        Contato contato = pesquisar(nome);

        if (contato == null) {
            return false;
        }
        contatos.remove(contato);

        return true;
        
    }
}