package com.mycompany.agendacontatos;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    @Test
    void cicloCompleto() throws Exception {
        Agenda agenda = new Agenda(new ContatoDAOEmMemoria());
        Contato pessoa = new Contato("Bryan", "00123", "b@exemplo.com");

        agenda.adicionarContato(pessoa);

        assertSame(pessoa, agenda.buscarContatos("BRY").get(0));

        agenda.editarContato(pessoa, "Ana", "00456", "");

        assertTrue(agenda.buscarContatos("Bryan").isEmpty());
        assertEquals("00456", agenda.buscarContatos("ana").get(0).getTelefone());

        agenda.listarContatos().clear();

        assertEquals(1, agenda.listarContatos().size());

        assertTrue(agenda.removerContato(pessoa));
        assertFalse(agenda.removerContato(pessoa));

        assertTrue(agenda.listarContatos().isEmpty());
    }

    @Test
    void rejeitaOperacoesInvalidas() throws Exception {
        Agenda agenda = new Agenda(new ContatoDAOEmMemoria());

        assertThrows(
                IllegalArgumentException.class,
                () -> agenda.adicionarContato(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> agenda.buscarContatos(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> agenda.editarContato(
                        new Contato("X", "1", ""),
                        "Y",
                        "2",
                        ""
                )
        );
    }

    private static class ContatoDAOEmMemoria extends ContatoDAO {

        private final List<Contato> contatos = new ArrayList<>();

        @Override
        public void salvar(Contato contato) {
            contatos.add(contato);
        }

        @Override
        public List<Contato> listar() {
            return new ArrayList<>(contatos);
        }

        @Override
        public void editar(Contato contato) {
        }

        @Override
        public boolean remover(Contato contato) {
            return contatos.remove(contato);
        }
    }
}
