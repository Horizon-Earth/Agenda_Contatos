package com.mycompany.agendacontatos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {
    @Test void cicloCompleto() {
        Agenda agenda = new Agenda();
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
    @Test void rejeitaOperacoesInvalidas() {
        Agenda agenda = new Agenda();
        assertThrows(IllegalArgumentException.class, () -> agenda.adicionarContato(null));
        assertThrows(IllegalArgumentException.class, () -> agenda.buscarContatos(null));
        assertThrows(IllegalArgumentException.class, () ->
            agenda.editarContato(new Contato("X", "1", ""), "Y", "2", ""));
    }
}
