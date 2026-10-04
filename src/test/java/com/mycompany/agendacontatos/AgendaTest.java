package com.mycompany.agendacontatos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    @Test
    void rejeitaOperacoesInvalidas() throws Exception {
        Agenda agenda = new Agenda();

        assertThrows(IllegalArgumentException.class,
                () -> agenda.adicionarContato(null));

        assertThrows(IllegalArgumentException.class,
                () -> agenda.buscarContatos(null));

        assertThrows(IllegalArgumentException.class,
                () -> agenda.editarContato(null, "Y", "2", ""));
    }
}
